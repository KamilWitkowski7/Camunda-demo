package pl.xyz.demo.eventaggregator.processstate;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.xyz.demo.eventaggregator.fields.Fields;
import pl.xyz.demo.eventaggregator.rawevent.RawEventRepository;
import pl.xyz.demo.eventaggregator.rawevent.RawEvent;
import pl.xyz.demo.eventaggregator.extractors.EventVariableExtractorUtil;
import pl.xyz.demo.eventaggregator.updates.AbstractAggregationEventHandler;
import pl.xyz.demo.eventaggregator.updates.OutOfOrderUpdateEvent;
import pl.xyz.demo.eventaggregator.updates.UpdateReceivedEvent;
import pl.xyz.demo.eventaggregator.fields.FieldsUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessStateHandler extends AbstractAggregationEventHandler<ProcessState, String> {
    private final ProcessStateRepository stateRepository;
    private final RawEventRepository eventRepository;
    private Map<String, BiConsumer<ProcessState, RawEvent>> applyMethods =
            new ImmutableMap.Builder<String, BiConsumer<ProcessState, RawEvent>>()
                    .put("HistoricProcessInstanceEventEntity-start", this::applyProcessStartEvent)
                    .put("HistoricActivityInstanceEventEntity-start", this::applyActivityStartEvent)
                    .put("HistoricActivityInstanceEventEntity-end", this::applyActivityEndEvent)
                    .put("HistoricProcessInstanceEventEntity-end", this::applyProcessEndEvent)
                    .put("HistoricVariableUpdateEventEntity-create", this::applyVariableUpdateEvent)
                    .put("HistoricVariableUpdateEventEntity-update", this::applyVariableUpdateEvent)
                    .build();


    @Override
    protected void onUpdateReceived(UpdateReceivedEvent event) {
        ProcessState processState = getOrCreate(event.getProcessInstanceId());
        apply(processState, event.getEvent());
        stateRepository.save(processState);
    }

    @Override
    protected void onOutOfOrderUpdateReceived(OutOfOrderUpdateEvent event) {
        ProcessState processState = getOrCreate(event.getProcessInstanceId());
        processState.reset();

        List<RawEvent> events = eventRepository.findByProcessInstanceIdOrderByEndTimeAscEventIdAsc(event.getProcessInstanceId());
        events.forEach(rawEvent -> apply(processState, rawEvent));
        stateRepository.save(processState);
    }

    @Override
    protected void apply(ProcessState processState, RawEvent event) {
        applyMethods.getOrDefault(event.getHistoryEventType(), this::unknownEvent).accept(processState, event);
    }

    private void applyProcessStartEvent(ProcessState processState, RawEvent event) {
        String executionId = FieldsUtil.getString(event.getContent(), Fields.EXECUTION_ID);
        String processDefinitionId = FieldsUtil.getString(event.getContent(), Fields.PROCESS_DEFINITION_ID);
        String processDefinitionKey = FieldsUtil.getString(event.getContent(), Fields.PROCESS_DEFINITION_KEY);
        String businessKey = FieldsUtil.getString(event.getContent(), Fields.BUSINESS_KEY);
        Long startTime = FieldsUtil.getLong(event.getContent(), Fields.START_TIME);
        String startActivityId = FieldsUtil.getString(event.getContent(), Fields.START_ACTIVITY_ID);

        processState.fillProcessInfo(executionId, processDefinitionId, processDefinitionKey, businessKey, startActivityId, startTime);
    }

    private void applyActivityStartEvent(ProcessState processState, RawEvent event) {
        String activityInstanceId = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_INSTANCE_ID);
        String activityId = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_ID);
        String activityName = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_NAME);
        String activityType = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_TYPE);
        Long startTime = FieldsUtil.getLong(event.getContent(), Fields.START_TIME);

        processState.startActivity(activityInstanceId, activityId, activityName, activityType, startTime);
    }

    private void applyActivityEndEvent(ProcessState processState, RawEvent event) {
        String activityInstanceId = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_INSTANCE_ID);
        processState.handleActivityEndEvent(activityInstanceId);
    }

    private void applyProcessEndEvent(ProcessState processState, RawEvent event) {
        Long endTime = FieldsUtil.getLong(event.getContent(), Fields.END_TIME);
        String endActivityId = FieldsUtil.getString(event.getContent(), Fields.END_ACTIVITY_ID);
        processState.finish(endTime, endActivityId);
    }

    private void applyVariableUpdateEvent(ProcessState processState, RawEvent event) {
        String activityInstanceId = FieldsUtil.getString(event.getContent(), Fields.ACTIVITY_INSTANCE_ID);
        String scopeActivityInstanceId = FieldsUtil.getString(event.getContent(), Fields.SCOPE_ACTIVITY_INSTANCE_ID);
        String variableName = FieldsUtil.getString(event.getContent(), Fields.VARIABLE_NAME);

        Optional<Map.Entry<String, Object>> valueEntry = EventVariableExtractorUtil.extractValue(event.getContent());
        Object value = valueEntry.map(entry -> entry.getValue()).orElse(null);

        if(StringUtils.equals(activityInstanceId, scopeActivityInstanceId)) {
            processState.updateTaskVariable(activityInstanceId, variableName, value);
        } else {
            processState.updateProcessVariable(variableName, value);
        }
    }

    private void unknownEvent(ProcessState processState, RawEvent event) {
        log.error(String.format("unknown event %s", event.getHistoryEventType()));
    }

    @Override
    protected ProcessState getOrCreate(String processInstanceId) {
        return stateRepository.findById(processInstanceId)
                .orElseGet(() -> new ProcessState(processInstanceId));
    }
}

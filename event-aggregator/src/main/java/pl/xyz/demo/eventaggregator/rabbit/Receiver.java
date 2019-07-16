package pl.xyz.demo.eventaggregator.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.xyz.demo.eventaggregator.fields.Fields;
import pl.xyz.demo.eventaggregator.rawevent.RawEventRepository;
import pl.xyz.demo.eventaggregator.rawevent.RawEvent;
import pl.xyz.demo.eventaggregator.processtrace.ProcessEventTracer;
import pl.xyz.demo.eventaggregator.updates.OutOfOrderUpdateEvent;
import pl.xyz.demo.eventaggregator.updates.UpdateReceivedEvent;
import pl.xyz.demo.eventaggregator.fields.FieldsUtil;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class Receiver {
    private final RawEventRepository eventRepository;
    private final ProcessEventTracer eventTracer;
    private final ApplicationEventPublisher eventPublisher;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Receiver(RawEventRepository eventRepository, ProcessEventTracer eventTracer, ApplicationEventPublisher eventPublisher) {
        this.eventRepository = eventRepository;
        this.eventTracer = eventTracer;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void receiveMessage(String message) {
        try {
            Map<String, Object> content = objectMapper.readValue(message, Map.class);
            log.info("Received {} {}", content.get(Fields.HISTORY_EVENT_TYPE.toString()), message);
            String processInstanceId = FieldsUtil.getString(content, Fields.PROCESS_INSTANCE_ID);
            String eventId = FieldsUtil.getString(content, Fields.ID);
            Long endTime = FieldsUtil.getLong(content, Fields.END_TIME);
            if (endTime == null) {
                endTime = FieldsUtil.getLong(content, Fields.START_TIME);
            }
            if (endTime == null) {
                endTime = FieldsUtil.getLong(content, Fields.TIMESTAMP);
            }

            RawEvent rawEvent = new RawEvent(
                    eventId,
                    processInstanceId,
                    FieldsUtil.getString(content, Fields.HISTORY_EVENT_TYPE),
                    FieldsUtil.getLong(content, Fields.START_TIME),
                    endTime,
                    content);

            eventRepository.save(rawEvent);

            if (eventTracer.isAfterLast(processInstanceId, endTime, eventId)) {
                eventPublisher.publishEvent(new UpdateReceivedEvent(this, processInstanceId, rawEvent));
            } else {
                eventPublisher.publishEvent(new OutOfOrderUpdateEvent(this, processInstanceId,
                        eventRepository.findByProcessInstanceIdOrderByEndTimeAscEventIdAsc(processInstanceId)));
            }

            eventTracer.updateLastEvent(processInstanceId, endTime, eventId);
        } catch (IOException e) {
            log.error("Can't process event", e);
        }
    }
}

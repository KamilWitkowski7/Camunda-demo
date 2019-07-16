package pl.xyz.demo.camunda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import groovy.util.logging.Slf4j;

import org.camunda.bpm.engine.impl.history.event.*;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

import pl.xyz.demo.camunda.queue.HistoryEventPublisher;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
@Slf4j
public class CustomHistoryEventHandler implements HistoryEventHandler, ApplicationListener<PayloadApplicationEvent<?>> {
    private final Logger logger = Logger.getLogger(CustomHistoryEventHandler.class.getName());
    private final ObjectMapper mapper = new ObjectMapper();
    private HistoryEventPublisher publisher;
    private final Set<String> supportedEvents = Set.of(
            HistoricProcessInstanceEventEntity.class.getSimpleName().concat("-start"),
            HistoricProcessInstanceEventEntity.class.getSimpleName().concat("-update"),
            HistoricProcessInstanceEventEntity.class.getSimpleName().concat("-end"),
            HistoricActivityInstanceEventEntity.class.getSimpleName().concat("-start"),
            HistoricActivityInstanceEventEntity.class.getSimpleName().concat("-end"),
            HistoricVariableUpdateEventEntity.class.getSimpleName().concat("-create"),
            HistoricVariableUpdateEventEntity.class.getSimpleName().concat("-update"),
            HistoricVariableUpdateEventEntity.class.getSimpleName().concat("-delete"),
            HistoricTaskInstanceEventEntity.class.getSimpleName().concat("-create"),
            HistoricTaskInstanceEventEntity.class.getSimpleName().concat("-update"),
            HistoricTaskInstanceEventEntity.class.getSimpleName().concat("-complete"),
            UserOperationLogEntryEventEntity.class.getSimpleName().concat("-assignee")
    );

    public CustomHistoryEventHandler(HistoryEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void handleEvent(HistoryEvent historyEvent) {
        String historyEventType = createHistoryEventType(historyEvent);
        logger.info("Handle event  " + historyEventType + " " + (historyEvent instanceof HistoricActivityInstanceEventEntity ? ((HistoricActivityInstanceEventEntity) historyEvent).getActivityId() : ""));
        ObjectNode node = mapper.valueToTree(historyEvent);
        node.put("historyEventType", historyEventType);

        if (supportedEvents.contains(historyEventType)) {
            publisher.publish(node, historyEventType);
            try {
                logger.info(new ObjectMapper().writeValueAsString(node));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Event: [" + historyEventType + "] will NOT be sent.");
            logger.info(node.toString());
        }
    }

    @Override
    public void handleEvents(List<HistoryEvent> list) {
        list.forEach(this::handleEvent);
    }

    private String createHistoryEventType(HistoryEvent historyEvent) {
        String name = historyEvent.getClass().getSimpleName();
        if (historyEvent instanceof UserOperationLogEntryEventEntity && ((UserOperationLogEntryEventEntity) historyEvent).getProperty() != null) {
            name = name.concat("-").concat(((UserOperationLogEntryEventEntity) historyEvent).getProperty());
        } else if (historyEvent.getEventType() != null) {
            name = name.concat("-").concat(historyEvent.getEventType());
        }
        return name;
    }

    @Override
    public void onApplicationEvent(PayloadApplicationEvent<?> event) {
        if (event.getPayload() instanceof HistoryEvent) {
            handleEvent((HistoryEvent) event.getPayload());
        }
    }
}

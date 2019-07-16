package pl.xyz.demo.eventaggregator.updates;

import lombok.Getter;
import pl.xyz.demo.eventaggregator.rawevent.RawEvent;

import java.util.List;

@Getter
public class OutOfOrderUpdateEvent extends AggregationEvent {
    private final String processInstanceId;
    private final List<RawEvent> events;

    public OutOfOrderUpdateEvent(Object source, String processInstanceId, List<RawEvent> events) {
        super(source);
        this.processInstanceId = processInstanceId;
        this.events = events;
    }
}

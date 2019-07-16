package pl.xyz.demo.eventaggregator.updates;

import lombok.Getter;
import pl.xyz.demo.eventaggregator.rawevent.RawEvent;

@Getter
public class UpdateReceivedEvent extends AggregationEvent {
    private final String processInstanceId;
    private final RawEvent event;

    public UpdateReceivedEvent(Object source, String processInstanceId, RawEvent event) {
        super(source);
        this.processInstanceId = processInstanceId;
        this.event = event;
    }
}

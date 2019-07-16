package pl.xyz.demo.eventaggregator.updates;

import org.springframework.context.ApplicationEvent;

public abstract class AggregationEvent extends ApplicationEvent {
    public AggregationEvent(Object source) {
        super(source);
    }
}

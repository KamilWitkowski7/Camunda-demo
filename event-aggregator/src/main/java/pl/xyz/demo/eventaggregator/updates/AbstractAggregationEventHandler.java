package pl.xyz.demo.eventaggregator.updates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import pl.xyz.demo.eventaggregator.rawevent.RawEvent;

@Slf4j
public abstract class AbstractAggregationEventHandler<T,K> implements ApplicationListener<AggregationEvent> {
    @Override
    public void onApplicationEvent(AggregationEvent aggregationEvent) {
        if (aggregationEvent instanceof UpdateReceivedEvent) {
            this.onUpdateReceived((UpdateReceivedEvent) aggregationEvent);
        } else if (aggregationEvent instanceof OutOfOrderUpdateEvent) {
            this.onOutOfOrderUpdateReceived((OutOfOrderUpdateEvent) aggregationEvent);
        } else {
            log.error(String.format("Missing handler for type %ss", aggregationEvent.getClass().getSimpleName()));
        }
    }

    protected abstract void onUpdateReceived(UpdateReceivedEvent receivedEvent);

    protected abstract void onOutOfOrderUpdateReceived(OutOfOrderUpdateEvent event);

    protected abstract void apply(T projection, RawEvent event);

    protected abstract T getOrCreate(K projectionId);
}

package camelDebt.camelStartCamundaProcess.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.builder.Builder.constant;

public class CamelCamundaRedeliveryProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(CamelCamundaRedeliveryProcessor.class.getName());

    @Override
    public void process(Exchange exchange) {
        exchange.getIn().setHeader(RabbitMQConstants.REQUEUE, constant(true));
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.warn("redelivering", exception);
    }
}

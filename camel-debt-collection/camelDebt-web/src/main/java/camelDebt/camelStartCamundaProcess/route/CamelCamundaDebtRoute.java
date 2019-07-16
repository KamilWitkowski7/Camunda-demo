package camelDebt.camelStartCamundaProcess.route;

import camelDebt.camelStartCamundaProcess.processor.CamelCamundaExceptionHandler;
import camelDebt.camelStartCamundaProcess.processor.CamelCamundaRedeliveryProcessor;
import camelDebt.camelStartCamundaProcess.processor.CamelCamundaStartProcessProcessor;
import camelDebt.config.CamelCamundaStartProcessProperties;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CamelCamundaDebtRoute  extends RouteBuilder {
    private final CamelCamundaStartProcessProperties properties;
    private final CamelCamundaStartProcessProcessor startProcessProcessor;

    @Override
    public void configure() {
        onException(Exception.class).process(new CamelCamundaExceptionHandler())
                .onRedelivery(new CamelCamundaRedeliveryProcessor())
                .maximumRedeliveries(properties.getMaximumRedeliveriesOnException())
                .asyncDelayedRedelivery()
                .delayPattern(properties.getDelayPatternOnException())
                .handled(true)
                .setFaultBody(constant(true))
                .end();

        from("rabbitmq:" + getCamundaProcessStartQueueUri())
                .routeId("from queue to camunda")
                .streamCaching()
                .log("message recieved ${body}")
                .convertBodyTo(String.class)
                .process(startProcessProcessor)
                .log("message processed ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                .setHeader("Accept",  simple(MediaType.APPLICATION_JSON.toString()))
                .log("before send ${body}")
                .to(getCamundaProcessStartUri());
    }

    public String getCamundaProcessStartQueueUri() {
        return properties.getDebtCollectionEventQueue();
    }

    public String getCamundaProcessStartUri() { return properties.getDebtCollectionProcessStartUri(); }
}

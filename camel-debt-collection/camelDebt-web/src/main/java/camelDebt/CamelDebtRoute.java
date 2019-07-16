package camelDebt;

import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import pl.xyz.demo.camelDebt.generated.model.PolicyRetentionProcessRequest;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import camelDebt.config.CamelCamundaStartProcessProperties;
import camelDebt.exception.StartProcessException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CamelDebtRoute extends RouteBuilder {
    private final CamelCamundaStartProcessProperties properties;

    @Override
    public void configure() {
        // error handling to return custom HTTP status codes for the various exceptions
        onException(StartProcessException.class)
                .handled(true)
                // use HTTP status 400 when input data is invalid
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setBody(simple("Invalid input data"));

        rest()
                .description("Start process rest service provider")
                .consumes("application/xml").produces("application/xml")
                .post("/start").type(PolicyRetentionProcessRequest.class)
                .description("Service to start a test process")
                .route().routeId("REST").log("Message send: \n ${body}")

                .doTry()
                .to("validator:PolicyRetention.xsd")
                .doCatch(Exception.class)
                    .log(LoggingLevel.ERROR, "${exception.stacktrace}")
                    .to("direct:error").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(422))
                    .stop()
                .end()


                .doTry()
                .setHeader(RabbitMQConstants.DELIVERY_MODE, constant(2))
                .to(ExchangePattern.InOnly, properties.getStartUri())
                .doCatch(Exception.class)
                    .to("direct:error").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(503))
                    .log(LoggingLevel.ERROR, "${exception.stacktrace}")
                    .stop()
                .end()

                .setBody(simple(""))
                .endRest();

        from("direct:error").setBody(simple("${exception.message}"));
    }
}

package camelDebt;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelDebtSwaggerRoute extends RouteBuilder {
    @Override
    public void configure() {
        restConfiguration().component("servlet") // configure we want to use servlet as the component for the rest DSL
                .bindingMode(RestBindingMode.json_xml) // enable json/xml binding mode
                .dataFormatProperty("prettyPrint", "true") // output using pretty print
                .contextPath("/camel-debt/api/")
                .apiContextPath("/api-doc")  // enable swagger api
                .apiProperty("api.version", "2.0.0")
                .apiProperty("api.title", "Start process")
                .apiProperty("api.description", "Start process")
                .apiProperty("api.contact.name", "Kamil Witkowski")
                .apiProperty("cors", "true") // enable CORS
                .apiProperty("schemes", "http,https");
    }
}

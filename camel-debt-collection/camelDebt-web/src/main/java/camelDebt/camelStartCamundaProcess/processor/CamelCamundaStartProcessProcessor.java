package camelDebt.camelStartCamundaProcess.processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import pl.xyz.demo.camelDebt.generated.model.PolicyRetentionProcessRequest;
import pl.xyz.demo.camelDebt.variable.StartProcessInstanceRequest;
import pl.xyz.demo.camelDebt.variable.StartProcessVariable;
import pl.xyz.demo.camelDebt.variable.ValueInfo;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CamelCamundaStartProcessProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("processing");
        PolicyRetentionProcessRequest request = exchange.getIn().getBody(PolicyRetentionProcessRequest.class);
        exchange.getIn().setBody(prepareRequestBody(request));
        exchange.getIn().removeHeaders("CamelHttp*");
    }

    private StartProcessInstanceRequest prepareRequestBody(PolicyRetentionProcessRequest startProcessInstanceRequest) throws Exception {
        Map<String, StartProcessVariable> variables = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        variables.put("policyRetentionProcessRequestObject",
                new StartProcessVariable(objectMapper.writeValueAsString(startProcessInstanceRequest), "Object",
                        new ValueInfo(PolicyRetentionProcessRequest.class.getName(), MediaType.APPLICATION_JSON.toString())));

        return new StartProcessInstanceRequest(variables);
    }
}

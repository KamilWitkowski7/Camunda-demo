package pl.xyz.demo.camelDebt.variable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Map;

@JsonTypeName(value = "variables")
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use= JsonTypeInfo.Id.NAME)
public class StartProcessInstanceRequest {
    public StartProcessInstanceRequest(Map<String, StartProcessVariable> variables) {
        this.policyRetentionProcessRequestObject = variables.get("policyRetentionProcessRequestObject");
    }

    @JsonProperty("policyRetentionProcessRequestObject")
    protected StartProcessVariable policyRetentionProcessRequestObject;
}

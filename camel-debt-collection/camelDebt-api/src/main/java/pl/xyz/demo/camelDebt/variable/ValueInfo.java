package pl.xyz.demo.camelDebt.variable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueInfo {
    private String objectTypeName;
    private String serializationDataFormat;

    public ValueInfo(String objectTypeName, String serializationDataFormat) {
        this.objectTypeName = objectTypeName;
        this.serializationDataFormat = serializationDataFormat;
    }
}
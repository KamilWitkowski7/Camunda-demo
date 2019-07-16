package pl.xyz.demo.camelDebt.variable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartProcessVariable {
    public StartProcessVariable(String value, String type, ValueInfo valueInfo) {
        this.value = value;
        this.type = type;
        this.valueInfo = valueInfo;
    }

    private String value;
    private String type;
    private ValueInfo valueInfo;
}


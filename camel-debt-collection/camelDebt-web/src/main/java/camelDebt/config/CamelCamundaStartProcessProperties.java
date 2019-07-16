package camelDebt.config;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "camundastartprocess")
@Getter
@Setter
public class CamelCamundaStartProcessProperties {
    private String debtCollectionEventQueue;
    private String debtCollectionProcessStartUri;
    private int maximumRedeliveriesOnException;
    private String delayPatternOnException;
    private String startUri;
}

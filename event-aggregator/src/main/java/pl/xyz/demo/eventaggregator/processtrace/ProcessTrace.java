package pl.xyz.demo.eventaggregator.processtrace;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "process_trace", type = "process_trace")
@Getter
@Setter
@NoArgsConstructor
public class ProcessTrace {
    @Id
    @Field(fielddata = true, type = FieldType.Text)
    private String processInstanceId;
    @Field(fielddata = true, type = FieldType.Text)
    private String eventId;
    private Long timestamp;

    public ProcessTrace(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public void update(Long timestamp, String eventId) {
        this.timestamp = timestamp;
        this.eventId = eventId;
    }
}

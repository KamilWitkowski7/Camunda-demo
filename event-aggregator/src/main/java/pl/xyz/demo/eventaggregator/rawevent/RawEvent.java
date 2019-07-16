package pl.xyz.demo.eventaggregator.rawevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "raw_event", type = "raw_event")
public class RawEvent {
    @Id
    @Field(fielddata = true, type = FieldType.Text)
    private String eventId;
    @Field(fielddata = true, type = FieldType.Text)
    private String processInstanceId;
    private String historyEventType;
    private Long startTime;
    private Long endTime;
    @Field(type = FieldType.Object)
    private Map<String, Object> content;
}

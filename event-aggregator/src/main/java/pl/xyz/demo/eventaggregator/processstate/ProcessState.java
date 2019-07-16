package pl.xyz.demo.eventaggregator.processstate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.*;

@Document(indexName = "process_state", type = "process_state")
@Getter
@Setter
@NoArgsConstructor
public class ProcessState {
    @Id
    private String processInstanceId;
    private String rootProcessInstanceId;
    private String executionId;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processDefinitionName;
    private String businessKey;
    private String startActivityId;
    @Field(type = FieldType.Date)
    private Long startTime;
    @Field(type = FieldType.Date)
    private Long endTime;
    private String endActivityId;
    private boolean finished = false;
    private List<Activity> currentActivities = new LinkedList<>();
    private Map<String, Object> currentVariables = new HashMap<>();

    public ProcessState(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public void fillProcessInfo(
            String executionId,
            String processDefinitionId,
            String processDefinitionKey,
            String businessKey,
            String startActivityId,
            Long startTime
    ) {
        this.executionId = executionId;
        this.processDefinitionId = processDefinitionId;
        this.processDefinitionKey = processDefinitionKey;
        this.businessKey = businessKey;
        this.startActivityId = startActivityId;
        this.startTime = startTime;
    }

    public void startActivity(
            String activityInstanceId,
            String activityId,
            String activityName,
            String activityType,
            Long startTime) {
        currentActivities.add(
                new Activity(activityInstanceId, activityId, activityName, activityType, startTime, new HashMap<>())
        );
    }

    public void handleActivityEndEvent(String activityInstanceId) {
        Optional<Activity> currentActivity = currentActivities.stream()
                .filter(activity -> activity.getActivityInstanceId().equals(activityInstanceId))
                .findFirst();
        currentActivity.ifPresent(activity -> currentActivities.remove(activity));
    }

    public void reset() {
        this.currentActivities = new LinkedList<>();
        this.currentVariables = new HashMap<>();
        this.finished = false;
    }

    public void finish(Long endTime, String endActivityId) {
        this.endTime = endTime;
        this.endActivityId = endActivityId;
        this.finished = true;
    }

    public void updateProcessVariable(String variableName, Object value) {
        currentVariables.put(variableName, value);
    }

    public void updateTaskVariable(String activityInstanceId, String variableName, Object value) {
        currentActivities.stream().filter(activity -> activity.getActivityInstanceId().equals(activityInstanceId)).findFirst()
                .ifPresent(activity -> activity.getCurrentVariables().put(variableName, value));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Activity {
        private String activityInstanceId;
        private String activityId;
        private String activityName;
        private String activityType;
        private Long startTime;
        private Map<String, Object> currentVariables = new HashMap<>();
    }
}

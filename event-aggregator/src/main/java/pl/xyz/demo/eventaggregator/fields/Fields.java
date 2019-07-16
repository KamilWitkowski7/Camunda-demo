package pl.xyz.demo.eventaggregator.fields;

public enum Fields {
    ID("id"),
    BUSINESS_KEY("businessKey"),
    START_ACTIVITY_ID("startActivityId"),
    START_USER_ID("startUserId"),
    PROCESS_INSTANCE_ID("processInstanceId"),
    EXECUTION_ID("executionId"),
    PROCESS_DEFINITION_ID("processDefinitionId"),
    PROCESS_DEFINITION_KEY("processDefinitionKey"),
    SUPER_PROCESS_INSTANCE_ID("superProcessInstanceId"),
    SUPER_CASE_INSTANCE_ID("superCaseInstanceId"),
    DELETE_REASON("deleteReason"),
    END_ACTIVITY_ID("endActivityId"),
    TENANT_ID("tenantId"),
    CASE_INSTANCE_ID("caseInstanceId"),
    CASE_EXECUTION_ID("caseExecutionId"),
    CASE_DEFINITION_ID("caseDefinitionId"),
    CASE_DEFINITION_KEY("caseDefinitionKey"),
    START_TIME("startTime"),
    END_TIME("endTime"),
    DURATION_IN_MILLIS("durationInMillis"),
    VARIABLES("variables"),
    ACTIVITIES("activities"),
    CHILD_PROCESSES("childProcesses"),
    STATE("state"),

    PROCESS_DEFINITION_NAME("processDefinitionName"),
    PROCESS_DEFINITION_VERSION("processDefinitionVersion"),
    CURRENT_ACTIVITY_ID("currentActivityId"),
    CURRENT_ACTIVITY_NAME("currentActivityName"),
    ACTIVITY_ID("activityId"),
    ACTIVITY_NAME("activityName"),
    ACTIVITY_TYPE("activityType"),
    ACTIVITY_INSTANCE_ID("activityInstanceId"),
    ACTIVITY_INSTANCE_STATE("activityInstanceState"),
    EVENT_TYPE("eventType"),
    PERSISTANCE_STATE("persistentState"),
    HISTORY_EVENT_TYPE("historyEventType"),
    PARENT_ACTIVITY_INSTANCE_ID("parentActivityInstanceId"),
    TASK_ID("taskId"),
    TASK_NAME("taskName"),
    TASK_ASSIGNEE("taskAssignee"),
    TASK_ASSIGNEE_TIMESTAMP("taskAssigneeTimestamp"),
    TASK_ASSIGNEE_HISTORY("taskAssigneeHistory"),
    CALLED_PROCESS_INSTANCE_ID("calledProcessInstanceId"),
    CALLED_CASE_INSTANCE_ID("calledCaseInstanceId"),
    VARIABLE_NAME("variableName"),
    COMPLETE_SCOPE("completeScope"),
    CANCELED("canceled"),
    DURATION_RAW("durationRaw"),
    NAME("name"),
    ASSIGNEE("assignee"),
    SEQUENCE_COUNTER("sequenceCounter"),
    SCOPE_ACTIVITY_INSTANCE_ID("scopeActivityInstanceId"),

    OPERATION_TYPE("operationType"),
    USER_ID("userId"),
    TIMESTAMP("timestamp"),
    PROPERTY("property"),
    ORG_VALUE("orgValue"),
    NEW_VALUE("newValue");

    private final String name;

    private Fields(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

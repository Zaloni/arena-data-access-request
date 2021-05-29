package com.zaloni.bedrock.servicenow.common;

public class ServiceNowFieldsMapping {
    private String entityDetails;//arena_entity_details
    private String arenaAdditionalDetails;// arena_additional_details # json format will contain entity details like Datastore type, DB type, schema, tablename etc.
    private String arenaAccessRequestId;// arena_sdar_requestid
    private String requestedFor;// requested_for
    private String requestedReason;// requested_reason
    private String requestedFromProjectId;// requested_from_project_id

    public String getEntityDetails() {
        return entityDetails;
    }

    public void setEntityDetails(String entityDetails) {
        this.entityDetails = entityDetails;
    }

    public String getArenaAdditionalDetails() {
        return arenaAdditionalDetails;
    }

    public void setArenaAdditionalDetails(String arenaAdditionalDetails) {
        this.arenaAdditionalDetails = arenaAdditionalDetails;
    }

    public String getArenaAccessRequestId() {
        return arenaAccessRequestId;
    }

    public void setArenaAccessRequestId(String arenaAccessRequestId) {
        this.arenaAccessRequestId = arenaAccessRequestId;
    }

    public String getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(String requestedFor) {
        this.requestedFor = requestedFor;
    }

    public String getRequestedReason() {
        return requestedReason;
    }

    public void setRequestedReason(String requestedReason) {
        this.requestedReason = requestedReason;
    }

    public String getRequestedFromProjectId() {
        return requestedFromProjectId;
    }

    public void setRequestedFromProjectId(String requestedFromProjectId) {
        this.requestedFromProjectId = requestedFromProjectId;
    }

    @Override
    public String toString() {
        return "ServiceNowFieldsMapping{" +
                "entityDetails='" + entityDetails + '\'' +
                ", arenaRequestedJson='" + arenaAdditionalDetails + '\'' +
                ", arenaAccessRequestId='" + arenaAccessRequestId + '\'' +
                ", requestedFor='" + requestedFor + '\'' +
                ", requestedReason='" + requestedReason + '\'' +
                ", requestedFromProjectId='" + requestedFromProjectId + '\'' +
                '}';
    }
}

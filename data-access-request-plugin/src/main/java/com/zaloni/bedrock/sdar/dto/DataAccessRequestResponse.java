package com.zaloni.bedrock.sdar.dto;

public class DataAccessRequestResponse {

    private String status;
    private String statusMessage;
    private String integrationId;

    public String getStatus() {
        return status;
    }

    public DataAccessRequestResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatusMessage() {
        return statusMessage;
    }


    public DataAccessRequestResponse setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public DataAccessRequestResponse setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
        return this;
    }

    public String getIntegrationId() {
        return integrationId;
    }
}

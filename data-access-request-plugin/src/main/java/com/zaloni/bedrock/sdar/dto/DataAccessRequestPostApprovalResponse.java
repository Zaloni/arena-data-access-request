package com.zaloni.bedrock.sdar.dto;

import java.util.Map;

public class DataAccessRequestPostApprovalResponse {
    private String status;
    private String statusMessage;
    private Map<String, String> additionalInfo;

    public String getStatus() {
        return status;
    }

    public DataAccessRequestPostApprovalResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public DataAccessRequestPostApprovalResponse setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}

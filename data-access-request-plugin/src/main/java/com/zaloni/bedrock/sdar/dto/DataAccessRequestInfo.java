package com.zaloni.bedrock.sdar.dto;

import java.util.List;

public class DataAccessRequestInfo {
    private String requestId;
    private String explanationForRequest;
    private String requestedBy;

    private List<? extends EntityInfo> entityInfos;

    private List<RequestedForUser> requestedFor;
    private List<NotificationToUser> notificationTo;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExplanationForRequest() {
        return explanationForRequest;
    }

    public void setExplanationForRequest(String explanationForRequest) {
        this.explanationForRequest = explanationForRequest;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public List<? extends EntityInfo> getEntityInfos() {
        return entityInfos;
    }

    public void setEntityInfos(List<EntityInfo> entityInfos) {
        this.entityInfos = entityInfos;
    }

    public List<RequestedForUser> getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(List<RequestedForUser> requestedFor) {
        this.requestedFor = requestedFor;
    }

    public List<NotificationToUser> getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(List<NotificationToUser> notificationTo) {
        this.notificationTo = notificationTo;
    }

    @Override
    public String toString() {
        return "DataAccessRequestInfo{" +
                "requestId='" + requestId + '\'' +
                ", explanationForRequest='" + explanationForRequest + '\'' +
                ", requestedBy='" + requestedBy + '\'' +
                ", entityInfos=" + entityInfos +
                ", requestedFor=" + requestedFor +
                ", notificationTo=" + notificationTo +
                '}';
    }
}

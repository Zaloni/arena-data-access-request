package com.zaloni.bedrock.sdar.enums;

import java.util.ArrayList;
import java.util.List;

public enum DataAccessRequestStatus {

    PENDING_FULFILLMENT("Pending Fulfillment"),
    APPROVED("Approved"),
    INTEGRATION_ERROR("Integration Error");

    private String statusValue;

    DataAccessRequestStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return this.statusValue;
    }

    public static String getValues() {
        List<String> values = new ArrayList();
        for (DataAccessRequestStatus dataAccessRequestStatus : values()) {
            values.add(dataAccessRequestStatus.name());
        }
        return values.toString();
    }
}

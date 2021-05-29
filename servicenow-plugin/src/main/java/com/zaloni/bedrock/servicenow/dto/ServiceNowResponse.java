package com.zaloni.bedrock.servicenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceNowResponse {

    @JsonProperty("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Result {

        @JsonProperty("sys_id")
        private String sysId;


        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "sysId='" + sysId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ServiceNowResponse{" +
                "result=" + result +
                '}';
    }
}




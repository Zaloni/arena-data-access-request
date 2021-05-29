/*
package com.zaloni.bedrock.servicenow.approver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaloni.bedrock.sdar.dto.PluginConfig;
import com.zaloni.bedrock.servicenow.common.ServiceNowInfo;
import com.zaloni.bedrock.servicenow.dto.ServiceNowApprovalStatus;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ServiceNowDataAccessApproverTest {

    @Test
    public void getServiceNowInfo() throws FileNotFoundException, JsonProcessingException {
        ServiceNowDataAccessApprover serviceNowDataAccessApprover = new ServiceNowDataAccessApprover();
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(new File("src/main/resources/config/plugin-config.yml"));
        Map<String, Object> config = yaml.load(inputStream);
        //System.out.println(config);
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.setConfigs(config);
        ServiceNowInfo serviceNowInfo = serviceNowDataAccessApprover.getServiceNowInfo(pluginConfig);
        System.out.println(serviceNowInfo);

        ServiceNowApprovalStatus serviceNowApprovalStatus = new ServiceNowApprovalStatus();
        serviceNowApprovalStatus.setStatus("Approved");
        Map<String, String> additionalProperties = new HashMap<>();
        additionalProperties.put("key1", "value1");
        additionalProperties.put("key2", "value2");
        serviceNowApprovalStatus.setAdditionalProperties(additionalProperties);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("json: " + objectMapper.writeValueAsString(serviceNowApprovalStatus));
    }
}*/

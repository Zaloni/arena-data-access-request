package com.zaloni.bedrock.sdar.dto;

import java.util.Map;

/**
 * Custom config properties read from /etc/zdp-gateway/data-access-request/plugin-config.yml.
 * The accessToken used here is used to store any Arena access token e.g. SSO
 */
public class PluginConfig {
    Map<String, Object> configs;
    String accessToken;

    public Map<String, Object> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, Object> configs) {
        this.configs = configs;
    }

   /**
    * @return Access token used by Arena (if any) e.g. SSO token
    */
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

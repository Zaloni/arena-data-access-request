package com.zaloni.bedrock.servicenow.common;

public class ServiceNowInfo {

    private String username;
    private String password;
    private String url;
    private String table;
    private String namespace;
    private ServiceNowFieldsMapping fieldsMapping;


    public String getUsername() {
        return username;
    }

    public ServiceNowInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ServiceNowInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ServiceNowInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTable() {
        return table;
    }

    public ServiceNowInfo setTable(String table) {
        this.table = table;
        return this;
    }

    public ServiceNowFieldsMapping getFieldsMapping() {
        return fieldsMapping;
    }

    public void setFieldsMapping(ServiceNowFieldsMapping fieldsMapping) {
        this.fieldsMapping = fieldsMapping;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return "ServiceNowInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", table='" + table + '\'' +
                ", namespace='" + namespace + '\'' +
                ", fieldsMapping=" + fieldsMapping +
                '}';
    }
}

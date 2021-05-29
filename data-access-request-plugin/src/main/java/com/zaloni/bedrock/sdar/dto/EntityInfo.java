package com.zaloni.bedrock.sdar.dto;

import java.util.List;
import java.util.Map;

/**
 * This class can be used to define the properties common to all entity types.
 * Extending classes such as DBEntityInfo and HiveEntityInfo defines specific properties
 * relevant to entity type RDBMS and Hive respectively.
 */
public class EntityInfo {
    private Integer entityId;
    private String entityBusinessName;
    private String entityTechnicalName;
    private String sourcePlatform;
    private String sourceSchema;
    private List<EntityField> entityFields;

    private Map<String, String> additionalProperties;//for future

    public EntityInfo() {

    }

    public EntityInfo(EntityInfo entityInfo) {
        this.entityId = entityInfo.entityId;
        this.entityBusinessName = entityInfo.entityBusinessName;
        this.entityTechnicalName = entityInfo.entityTechnicalName;
        this.sourcePlatform = entityInfo.sourcePlatform;
        this.sourceSchema = entityInfo.sourceSchema;
        this.entityFields = entityInfo.entityFields;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityBusinessName() {
        return entityBusinessName;
    }

    public void setEntityBusinessName(String entityBusinessName) {
        this.entityBusinessName = entityBusinessName;
    }

    public String getEntityTechnicalName() {
        return entityTechnicalName;
    }

    public void setEntityTechnicalName(String entityTechnicalName) {
        this.entityTechnicalName = entityTechnicalName;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public String getSourceSchema() {
        return sourceSchema;
    }

    public void setSourceSchema(String sourceSchema) {
        this.sourceSchema = sourceSchema;
    }

    public List<EntityField> getEntityFields() {
        return entityFields;
    }

    public void setEntityFields(List<EntityField> entityFields) {
        this.entityFields = entityFields;
    }

    @Override
    public String toString() {
        return "EntityInfo{" +
                "entityId=" + entityId +
                ", entityBusinessName='" + entityBusinessName + '\'' +
                ", entityTechnicalName='" + entityTechnicalName + '\'' +
                ", sourcePlatform='" + sourcePlatform + '\'' +
                ", sourceSchema='" + sourceSchema + '\'' +
                ", entityFields=" + entityFields +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

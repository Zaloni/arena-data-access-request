package com.zaloni.bedrock.sdar.dto;

/**
 * Helps define properties related to a field of an Entity.
 * This class is currently used inside EntityInfo to facilitate population of list of fields for an entity
 */
public class EntityField {
    private String fieldName;
    private String fieldTechnicalName;
    private String fieldDataType;
    private Integer fieldPosition;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldTechnicalName() {
        return fieldTechnicalName;
    }

    public void setFieldTechnicalName(String fieldTechnicalName) {
        this.fieldTechnicalName = fieldTechnicalName;
    }

    public String getFieldDataType() {
        return fieldDataType;
    }

    public void setFieldDataType(String fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    public Integer getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(Integer fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    @Override
    public String toString() {
        return "EntityField{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldTechnicalName='" + fieldTechnicalName + '\'' +
                ", fieldDataType='" + fieldDataType + '\'' +
                ", fieldPosition='" + fieldPosition + '\'' +
                '}';
    }
}

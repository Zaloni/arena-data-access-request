package com.zaloni.bedrock.sdar.dto;

/**
 * Helps define properties specific to RDBMS type entity.
 * see super class EntityInfo that is used to define properties common to all type of entities
 */
public class DBEntityInfo extends EntityInfo {
    private String dbJdbcUrl;
    private String dbType;
    private String dbSchema;
    private String dbTable;

    public DBEntityInfo(EntityInfo entityInfo) {
        super(entityInfo);
    }

    public String getDbJdbcUrl() {
        return dbJdbcUrl;
    }

    public void setDbJdbcUrl(String dbJdbcUrl) {
        this.dbJdbcUrl = dbJdbcUrl;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

    @Override
    public String toString() {
        return "DBEntityInfo{" +
                "dbJdbcUrl='" + dbJdbcUrl + '\'' +
                ", dbType='" + dbType + '\'' +
                ", dbSchema='" + dbSchema + '\'' +
                ", dbTable='" + dbTable + '\'' +
                '}';
    }
}

package com.zaloni.bedrock.sdar.dto;

/**
 * Helps define properties specific to Hive type entity
 * see super class EntityInfo that is used to define properties common to all type of entities
 */
public class HiveEntityInfo extends EntityInfo {
    private String hiveDb;
    private String hiveTable;
    private String hiveTableType;
    private String hiveDataFormat;

    public HiveEntityInfo(EntityInfo entityInfo) {
        super(entityInfo);
    }

    public String getHiveDb() {
        return hiveDb;
    }

    public void setHiveDb(String hiveDb) {
        this.hiveDb = hiveDb;
    }

    public String getHiveTable() {
        return hiveTable;
    }

    public void setHiveTable(String hiveTable) {
        this.hiveTable = hiveTable;
    }

    public String getHiveTableType() {
        return hiveTableType;
    }

    public void setHiveTableType(String hiveTableType) {
        this.hiveTableType = hiveTableType;
    }

    public String getHiveDataFormat() {
        return hiveDataFormat;
    }

    public void setHiveDataFormat(String hiveDataFormat) {
        this.hiveDataFormat = hiveDataFormat;
    }

    @Override
    public String toString() {
        return "HiveEntityInfo{" +
                "hiveDb='" + hiveDb + '\'' +
                ", hiveTable='" + hiveTable + '\'' +
                ", hiveTableType='" + hiveTableType + '\'' +
                ", hiveDataFormat='" + hiveDataFormat + '\'' +
                '}';
    }
}

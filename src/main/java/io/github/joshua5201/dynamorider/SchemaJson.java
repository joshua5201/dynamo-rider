package io.github.joshua5201.dynamorider;

import com.fasterxml.jackson.annotation.JsonProperty;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;

public class SchemaJson {
    @JsonProperty("Table")
    TableJson table;
    public TableJson getTable() {
        return table;
    }
    public void setTable(TableJson table) {
        this.table = table;
    }

    public static class TableJson {
        @JsonProperty("TableName")
        String tableName;
        @JsonProperty("KeySchema")
        KeySchemaJson[] keySchema;
        @JsonProperty("AttributeDefinitions")
        AttributeDefinitionJson[] attributeDefinitions;

        public String getTableName() {
            return tableName;
        }
        public void setTableName(String tableName) {
            this.tableName = tableName;
        }
        public KeySchemaJson[] getKeySchema() {
            return keySchema;
        }
        public void setKeySchema(KeySchemaJson[] keySchema) {
            this.keySchema = keySchema;
        }
        public AttributeDefinitionJson[] getAttributeDefinitions() {
            return attributeDefinitions;
        }
        public void setAttributeDefinitions(AttributeDefinitionJson[] attributeDefinitions) {
            this.attributeDefinitions = attributeDefinitions;
        }

    }

    public static class KeySchemaJson {
        @JsonProperty("AttributeName")
        String attributeName;
        @JsonProperty("KeyType")
        String keyType;
        public String getAttributeName() {
            return attributeName;
        }
        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }
        public String getKeyType() {
            return keyType;
        }
        public void setKeyType(String keyType) {
            this.keyType = keyType;
        }
        public KeySchemaElement toKeySchemaElement() {
            return KeySchemaElement.builder()
                    .attributeName(getAttributeName())
                    .keyType(getKeyType())
                    .build();
        }
    }

    public static class AttributeDefinitionJson {
        @JsonProperty("AttributeName")
        String attributeName;
        @JsonProperty("AttributeType")
        String attributeType;

        public String getAttributeName() {
            return attributeName;
        }
        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }
        public String getAttributeType() {
            return attributeType;
        }
        public void setAttributeType(String attributeType) {
            this.attributeType = attributeType;
        }
        public AttributeDefinition toAttributeDefinition() {
            return AttributeDefinition.builder()
                    .attributeName(getAttributeName())
                    .attributeType(getAttributeType())
                    .build();
        }
    }
}

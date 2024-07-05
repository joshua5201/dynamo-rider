package io.github.joshua5201.dynamorider;

import com.fasterxml.jackson.jr.annotationsupport.JacksonAnnotationExtension;
import com.fasterxml.jackson.jr.ob.JSON;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.BillingMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SchemaFileLoader {
    private final DynamoDbClient dynamoDbClient;
    private final JSON json = JSON.builder().register(JacksonAnnotationExtension.std).build();

    public SchemaFileLoader(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void load(String filename) {
        String jsonContent;
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            jsonContent = Files.readString(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load schema file", e);
        }
        try {
            var schema = json.beanFrom(SchemaJson.class, jsonContent);
            var tableJson = schema.getTable();
            dynamoDbClient.createTable(builder -> {
                builder.tableName(tableJson.getTableName());
                builder.attributeDefinitions(Arrays.stream(tableJson.getAttributeDefinitions())
                        .map(SchemaJson.AttributeDefinitionJson::toAttributeDefinition).collect(Collectors.toList()));
                builder.keySchema(Arrays.stream(tableJson.getKeySchema())
                        .map(SchemaJson.KeySchemaJson::toKeySchemaElement).collect(Collectors.toList()));
                builder.billingMode(BillingMode.PAY_PER_REQUEST);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

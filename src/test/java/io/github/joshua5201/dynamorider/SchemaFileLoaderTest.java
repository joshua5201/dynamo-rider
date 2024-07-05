package io.github.joshua5201.dynamorider;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchemaFileLoaderTest {
    private final String DYNAMO_HOST = System.getenv("DYNAMO_HOST");
    private final String DYNAMO_POST = System.getenv("DYNAMO_TCP_8000");
    private final String DYNAMO_TABLE_NAME = "Music";

    @Test
    void givenCorrectSchemaFile_TableCreatedInDynamo() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(Region.US_EAST_1)
                .endpointOverride(URI.create("http://" + DYNAMO_HOST + ":" + DYNAMO_POST)).build();
        SchemaFileLoader schemaFileLoader = new SchemaFileLoader(dynamoDbClient);
        schemaFileLoader.load("schema.json");
        DescribeTableResponse response = dynamoDbClient.describeTable(builder -> builder.tableName(DYNAMO_TABLE_NAME));
        assertEquals(DYNAMO_TABLE_NAME, response.table().tableName());
        assertEquals(2, response.table().keySchema().size());
    }
}

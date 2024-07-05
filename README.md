# dynamo-rider
A simple and easy to use test framework for AWS DynamoDB inspired by [database-rider](https://github.com/database-rider/database-rider).

## Implementation Plan

- [v] Create tables from json definition files exported from AWS CLI
- [] Load and insert data from yaml or json files in classpath to DynamoDB using injected DynamoDB client
- [] Annotations to specify the data set to be loaded before a test method
- [] Extend JUnit 5 to support the annotations
- [] Verify the data in the database after the test method
- [] Clean the data after the test method
- [] Configure the framework using properties file
- [] Support different formats for the data set files
- [] Extend Spring framework to get the DynamoDB client bean
- [] Error handling and verification of datasets
- [] Add support to real dynamodb
- [] Support merging datasets
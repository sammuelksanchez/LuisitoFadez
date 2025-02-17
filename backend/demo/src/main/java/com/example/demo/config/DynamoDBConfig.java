package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;



@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Change region as needed
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY"), System.getenv("AWS_SECRET_KEY"))
                ))
                .build();
    }
}

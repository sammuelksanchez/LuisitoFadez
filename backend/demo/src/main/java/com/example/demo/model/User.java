package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.dynamodbv2
@DynamoDbBean
public class User {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    @DynamoDbPartitionKey
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

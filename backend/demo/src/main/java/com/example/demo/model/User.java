package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import lombok.Data;

@Data
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String role;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @DynamoDbAttribute("first_name")
    public String getFirst_Name(){ return firstName;}

    @DynamoDbAttribute("last_name")
    public String getLast_Name(){ return lastName;}

    @DynamoDbAttribute("role")
    public String getRole(){ return role;}
}

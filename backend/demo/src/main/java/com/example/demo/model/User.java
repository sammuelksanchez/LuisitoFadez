package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @DynamoDbAttribute("first_name")
    public String getFirstName(){ return firstName;}

    @DynamoDbAttribute("last_name")
    public String getLastName(){ return lastName;}

    @DynamoDbAttribute("role")
    public String getRole(){ return role;}
}

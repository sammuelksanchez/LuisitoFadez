package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import lombok.Data;


@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class Appointments {

    private String appointmentId;
    private String phoneNumber;
    private String appointmentDatetime;
    private String createdAt;
    private String status;


    public void setAppointmentId(String appointmentId){
        this.appointmentId = appointmentId;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAppointmentDatetime(String appointmentDatetime){
        this.appointmentDatetime = appointmentDatetime;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public void setStatus(String status){
        this.status = status;
    }
    @DynamoDbSortKey
    @DynamoDbAttribute("appointmentId")
    public String getAppointmentId(){return appointmentId;}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("phoneNumber")
    public String getPhoneNumber(){return phoneNumber;}

    @DynamoDbAttribute("appointmentDateTime")
    public String getAppointmentDateTime(){return appointmentDatetime;}

    @DynamoDbAttribute("createdAt")
    public String getCreatedAt(){return createdAt;}

    @DynamoDbAttribute("status")
    public String getStatus(){return status;}
}


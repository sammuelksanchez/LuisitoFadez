package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import lombok.Data;

@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointments {
    private long appt_bid;
    private String phone_number;
    private String appointment_datetime;
    private String create_at;
    private String status;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("appt_id")
    public long getAppointmentId(){return appt_id;}

    @DynamoDbSortKey
    @DynamoDbAttribute("phone_number")
    public String getPhoneNumber(){return phone_number;}

    @DynamoDbAttribute("appointment_datetime")
    public String getAppointmentDateTime(){return appointment_datetime;}

    @DynamoDbAttribute("created_at")
    public String getCreationTime(){return create_at;}

    @DynamoDbAttribute("status")
    public String getStatus(){return status;}
}


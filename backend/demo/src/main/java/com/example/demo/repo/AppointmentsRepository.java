package com.example.demo.repo;


import com.example.demo.model.Appointments;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AppointmentsRepository {
    private final DynamoDbTable<Appointments> appointmentTable;

    public AppointmentsRepository(DynamoDbEnhancedClient enhancedClient){
        this.appointmentTable = enhancedClient.table("Appointments", TableSchema.fromBean(Appointments.class));
    }

    public Appointments save(Appointments appointments){
        appointmentTable.putItem(appointments);
        return appointments;
    }

    public Optional<Appointments> findById(String phoneNumber,String appointmentId){
        Key key = Key.builder()
                .partitionValue(phoneNumber)
                .sortValue(appointmentId)
                .build();
        return Optional.ofNullable(appointmentTable.getItem(key));
    }

    public List<Appointments> findByPhoneNumber(String phone_number){
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder()
                        .partitionValue(phone_number)
                        .build());
        return appointmentTable.query(queryConditional)
                .items()
                .stream()
                .collect(Collectors.toList());
    }

    public boolean existsById(String phoneNumber,String appointmentId){
        return findById(phoneNumber, appointmentId).isPresent();
    }

    public void deleteById(String phoneNumber ,String appointmentId){
        if(!existsById(phoneNumber,appointmentId)){
            throw new RuntimeException("Appointment Not Found: " + appointmentId);
        }
        Key key = Key.builder()
                .partitionValue(phoneNumber)
                .sortValue(appointmentId)
                .build();
        appointmentTable.deleteItem(key);
    }

    public List<Appointments> findAll(){
        return appointmentTable.scan().items().stream().collect(Collectors.toList());
    }


}

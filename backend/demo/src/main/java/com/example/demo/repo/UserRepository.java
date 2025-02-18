package com.example.demo.repo;

import com.example.demo.model.Appointments;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class UserRepository {
    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbEnhancedClient enhancedClient){
        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(User.class));
    }

    public User save(User user){
        userTable.putItem(user);
        return user;
    }

    public Optional<User> findByPhoneNumber(String phoneNumber){
        Key key = Key.builder()
                .partitionValue(phoneNumber)
                .build();
        return Optional.ofNullable(userTable.getItem(key));
    }

    public boolean existsByPhoneNumber(String phoneNumber){
        return findByPhoneNumber(phoneNumber).isPresent();
    }

    public void deleteByPhoneNumber(String phoneNumber){
        Key key = Key.builder()
                .partitionValue(phoneNumber)
                .build();
        userTable.deleteItem(key);
    }

    public List<User> findAll(){
        return userTable.scan().items().stream().collect(Collectors.toList());
    }


}

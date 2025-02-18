package com.example.demo.login;

import com.google.api.client.util.Value;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import javax.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {



    @PostConstruct
    public void initialize() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("demo/src/main/resources/firebase-service-account.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setDatabaseUrl("https://your-project-id.firebaseio.com") // Replace with your Firebase Database URL
                .build();

        FirebaseApp.initializeApp(options);
    }
}

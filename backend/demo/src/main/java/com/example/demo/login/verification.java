package com.example.demo.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.FirebaseAuthException;
import java.util.concurrent.ExecutionException;

public class verification {

    public static FirebaseToken verifyPhoneNumber(String verificationCode) throws ExecutionException, InterruptedException, FirebaseAuthException {
        // Use Firebase to verify the phone number with the provided verification code
        return FirebaseAuth.getInstance().verifyIdToken(verificationCode);
    }
}

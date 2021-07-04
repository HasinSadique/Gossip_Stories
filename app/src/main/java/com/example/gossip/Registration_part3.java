package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration_part3 extends AppCompatActivity {

    private String pass,repass;
    private EditText Password,Repassword;
    String FullName,Email,Birthday;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_part3);

        Password=findViewById(R.id.EditText_password);
        Repassword=findViewById(R.id.EditText_RetypePassword);
        Password=findViewById(R.id.EditText_password);
        Repassword= findViewById(R.id.EditText_RetypePassword);

        FullName = Registration.fullName;
        Email = Registration_part2.email;
        Birthday=Registration_part2.birthday;

        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(this, "Email: "+ Email+" "+FullName, Toast.LENGTH_LONG).show();


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        //updateUI(currentUser);
//    }

    public void Register(View view) {

        pass=Password.getText().toString();
        repass=Repassword.getText().toString();

        if(pass.equals(repass)){
            //Authenticate
            mAuth.createUserWithEmailAndPassword(Email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //Insert into DB
                                storeInfo(user.getUid());
                                //go to Login UI
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed."+task.getException(),
                                        Toast.LENGTH_LONG).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }
    }

    private void storeInfo(String ID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("Dob", Birthday);
        user.put("Email", Email);
        user.put("Full Name", FullName);

// Add a new document with a generated ID
        db.collection("users")
                .document(ID).set(user);
    }
}
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

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
                                storeInfo();
                                //go to Login UI
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
            //create user object
            User user= new User(FullName,Birthday,Email);
            //insert into DB
        }

        Intent intent=new Intent(this, Homepage.class);
        startActivity(intent);
        this.finish();
    }

    private void storeInfo() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        String key = myRef.push().getKey();
        User user = new User(FullName, Birthday, Email);
        myRef.child(key).setValue(user);
    }
}
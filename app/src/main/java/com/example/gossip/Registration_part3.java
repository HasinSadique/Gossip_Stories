package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class Registration_part3 extends AppCompatActivity {

    private String pass,repass;
    private EditText Password,Repassword;
    String FullName,Email,Birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_part3);

        Password=findViewById(R.id.EditText_password);
        Repassword=findViewById(R.id.EditText_RetypePassword);
        FullName = Registration.fullName;
        Email = Registration_part2.email;
        Birthday=Registration_part2.birthday;

        Password=findViewById(R.id.EditText_password);
        Repassword= findViewById(R.id.EditText_RetypePassword);


    }

    public void Register(View view) {
        pass=Password.getText().toString();
        repass=Repassword.getText().toString();

        if(pass.equals(repass)){
            //create user object
            //insert into DB
        }

        Intent intent=new Intent(this, Homepage.class);
        startActivity(intent);
        this.finish();
    }
}
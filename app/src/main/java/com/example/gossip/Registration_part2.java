package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Registration_part2 extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText Birthday;
    private EditText Email;
    public static String email,birthday;
    private TextView textViewError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrartion_part2);

        Birthday=findViewById(R.id.EditText_Birthday);
        Email=findViewById(R.id.EditText_email);
        textViewError=findViewById(R.id.TextView_RegistrationPart2_error);

    }

    public void ShowDatePicker(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                Registration_part2.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("Dater", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                Birthday.setText(date);
            }

        };

    }

    public void proceed(View view) {

        email=Email.getText().toString();
        birthday=Birthday.getText().toString();
        if((email.isEmpty())||(birthday.isEmpty())){
            textViewError.setText("Both email and date of birth are need to proceed, Please enter accordinly.");
        }else{
            Intent intent= new Intent(this, Registration_part3.class);
            startActivity(intent);
        }
    }
}

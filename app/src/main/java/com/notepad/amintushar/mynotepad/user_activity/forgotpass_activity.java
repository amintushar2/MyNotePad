package com.notepad.amintushar.mynotepad.user_activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.notepad.amintushar.mynotepad.R;

public class forgotpass_activity extends AppCompatActivity {
    EditText emailforgot;
    Button forgotsend;
    TextView confirmation;
    FirebaseAuth myAppAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass_activity);
        emailforgot=findViewById(R.id.emailforgot);
        forgotsend=findViewById(R.id.sendforgot);
        confirmation=findViewById(R.id.confirmation);
        myAppAuth=FirebaseAuth.getInstance();

forgotsend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
String emailf=emailforgot.getText().toString().trim();
    if(emailf.equals("")){
        emailforgot.setError("Enter Email Adress ");
        Toast.makeText(forgotpass_activity.this, "Please Enter Register Email", Toast.LENGTH_SHORT).show();
    }else{
        myAppAuth.sendPasswordResetEmail(emailf).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    confirmation.setText("Password Reset Email Sent");
                    finish();

                }else{
                    confirmation.setText("Error Sending Reset Email");
                }
            }
        });
    }
    }
});


    }
}

package com.notepad.amintushar.mynotepad.user_activity;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.notepad.amintushar.mynotepad.MainActivity;
import com.notepad.amintushar.mynotepad.R;

public class log_in_activity extends AppCompatActivity {

    Button log_in;
    TextView sign_up,forgot;
    EditText email3 , pass3;
    FirebaseAuth loginAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);
        log_in=findViewById(R.id.login1);
        sign_up=findViewById(R.id.sigup1);
        email3=findViewById(R.id.email);
        pass3=findViewById(R.id.pass);
        forgot=findViewById(R.id.forgotpass);
        loginAuth= FirebaseAuth.getInstance();



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(log_in_activity.this,sign_up_activity.class);
                startActivity(intent1);

            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email3.getText().toString();
                String passwordd = pass3.getText().toString();

                if (emailText.isEmpty()||passwordd.isEmpty()) {

                    email3.setError("Fill Id");
                    pass3.setError("Fill valid Pass");
                    Toast.makeText(log_in_activity.this, "Please enter all of informatin", Toast.LENGTH_SHORT).show();
                }  else {
                    loginUser(emailText,passwordd);

                }
            }


        });
        forgot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(log_in_activity.this, forgotpass_activity.class);
        startActivity(intent);
    }
});


    }

    private void loginUser(final String emailText, final String passwordd) {
        loginAuth.signInWithEmailAndPassword(emailText,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(log_in_activity.this, "Log In SuccesFully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(log_in_activity.this, MainActivity.class);
                    intent.putExtra("email",email3.getText().toString());
                    startActivity(intent);
                    finish();
                }    else {
                    Toast.makeText(log_in_activity.this, "Not Success ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}

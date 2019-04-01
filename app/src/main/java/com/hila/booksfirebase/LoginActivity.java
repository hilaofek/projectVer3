package com.hila.booksfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
public class LoginActivity extends AppCompatActivity{

    TextView register;
    EditText email, password;
    Button login;
    FirebaseAuth auth;
    String stremail,strpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button) findViewById(R.id.llogin);
        email=(EditText)findViewById(R.id.lemail);
        password=(EditText)findViewById(R.id.lpassword);
        register=(TextView) findViewById(R.id.lregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent( LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stremail=email.getText().toString();
                strpassword=password.getText().toString();

                auth= FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(stremail,strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"User logged in successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser user=auth.getCurrentUser();
                            String username= user.getDisplayName();
                            Intent i= new Intent();
                            i.putExtra("text",username);
                            setResult(RESULT_OK,i);
                            finish();

                        }
                    }
                });

            }
        });
    }


}

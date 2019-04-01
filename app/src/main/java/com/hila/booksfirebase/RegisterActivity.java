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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    TextView login;
    EditText username, email, password;
    Button register;
    FirebaseAuth auth;
    String strusername, stremail,strpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login=(TextView)findViewById(R.id.rlogin);
        username=(EditText)findViewById(R.id.rusername);
        email=(EditText)findViewById(R.id.remail);
        password=(EditText)findViewById(R.id.rpassword);
        register=(Button)findViewById(R.id.rregister);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strusername=username.getText().toString();
                stremail=email.getText().toString();
                strpassword=password.getText().toString();

                auth= FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(stremail,strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            initUser();
                        }
                    }
                });

            }
        });
    }

    public void initUser()
    {
        FirebaseUser user=auth.getCurrentUser();
        UserProfileChangeRequest request=new UserProfileChangeRequest.Builder().setDisplayName(strusername).build();
        user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterActivity.this,"User created successfully", Toast.LENGTH_LONG).show();
            }
        });

        String id=user.getUid();
        User newuser= new User(strusername,stremail,id);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(id).setValue(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterActivity.this, "User Added to database!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}

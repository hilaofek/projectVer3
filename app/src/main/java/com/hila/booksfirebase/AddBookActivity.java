package com.hila.booksfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Random;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDescription;
    private Button buttonSave;

    private int id;
    private String name;
    private String description;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_books);

            editTextName = findViewById(R.id.editTextName);
            editTextDescription = findViewById(R.id.editTextDescription);
            buttonSave = findViewById(R.id.buttonSave);

            editTextName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddBookActivity.this);
                    builder.setMessage("Do you want to Write in hebrew?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(AddBookActivity.this, MikledetActivity.class);
                            startActivityForResult(myIntent,0);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
            editTextDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddBookActivity.this);
                    builder.setMessage("Do you want to Write in hebrew?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(AddBookActivity.this, MikledetActivity.class);
                            startActivityForResult(myIntent,1);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Random random = new Random();
                    id = random.nextInt(10000);
                    name = editTextName.getText().toString();
                    description = editTextDescription.getText().toString();
                    ArrayList<Chapter> chapters=new ArrayList<Chapter>(10);
                    chapters.add(0,new Chapter("",null,0,""));
                    Book b = new Book(id, name, description,chapters);
                    final String sid= String.valueOf(id);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("books");
                    databaseReference.child(sid).setValue(b).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddBookActivity.this, "Book added to database!", Toast.LENGTH_SHORT).show();
                            editTextName.setText("");
                            editTextDescription.setText("");
                            Intent i= new Intent(AddBookActivity.this, AddChapterActivity.class);
                            i.putExtra("bookID",sid);
                            startActivity(i);
                        }
                    });
                }
            });

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String text = data.getStringExtra("text");
        if (requestCode==0)
            editTextName.setText(text);
        if(requestCode==1)
            editTextDescription.setText(text);

    }

}

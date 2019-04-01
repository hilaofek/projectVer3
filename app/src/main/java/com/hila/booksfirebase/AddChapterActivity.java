package com.hila.booksfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AddChapterActivity extends AppCompatActivity {

    EditText editText;
    Button save;
    ArrayList<String> chapter;
    Chapter c=null;
    String bookID;
    Intent i;
    Book b;
    ArrayList<String>chapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);
        editText =(EditText)findViewById(R.id.editText);
        save=(Button)findViewById(R.id.save);
        chapter=new ArrayList<String>(10);
        i=getIntent();
        bookID=i.getStringExtra("bookID");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input=editText.getText().toString();
                Scanner s = new Scanner(input).useDelimiter("\n\n");
                Integer i=0;
                while(s.hasNext())
                {
                    chapter.add(i,s.next());
                    i++;
                }
                s.close();

                Random random = new Random();
                final int id = random.nextInt(10000);
                final String chapterID= String.valueOf(id);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("books");
                final DatabaseReference myRef= databaseReference.child(bookID);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        b=dataSnapshot.getValue(Book.class);
                        c=new Chapter("chapter",chapter,b.getNumOfChapters(),chapterID);
                        b.addChapter(c);
                        myRef.child(bookID).setValue(b).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddChapterActivity.this, "chapter added to database!", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
            });


            }
        });
    }
}
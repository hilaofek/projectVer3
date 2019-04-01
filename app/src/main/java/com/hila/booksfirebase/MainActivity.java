package com.hila.booksfirebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private SharedPreferences sp;
    private String username;
    private EditText edName,edDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getSharedPreferences("details1",0);
        SharedPreferences.Editor ed= sp.edit();
        ed.putString("username", null);
        ed.commit();


        final ListView listViewBooks = findViewById(R.id.listViewBooks);
        final ArrayList<Book> list = new ArrayList<>();

        final BookAdapter bookAdapter = new BookAdapter(this,0, list);
        listViewBooks.setAdapter(bookAdapter);

        final DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("books");
        trigger(dRef, bookAdapter, listViewBooks);

/*
        listViewBooks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
        public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
            final DatabaseReference db = FirebaseDatabase.getInstance().getReference("books");
            Book b =bookAdapter.getItem(position);
            final int bookID = b.getId();



            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_update, null);
            edName= (EditText)viewInflated.findViewById(R.id.updateName);
            edDescription=(EditText)viewInflated.findViewById(R.id.updateDescription);

            edName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Do you want to Write in hebrew?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(MainActivity.this, MikledetActivity.class);
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
            edDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Do you want to Write in hebrew?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(MainActivity.this, MikledetActivity.class);
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
            builder.setView(viewInflated);

            builder.setMessage("Do you want to delete or Update?");

            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    String newName=edName.getText().toString();
                    String newDescription=edDescription.getText().toString();

                    db.child(String.valueOf(bookID)).setValue(new Book(bookID,newName,newDescription)).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            trigger(dRef, bookAdapter, listViewBooks);
                        }
                    });
                }
            });

            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    db.child(String.valueOf(bookID)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                            trigger(dRef, bookAdapter, listViewBooks);
                        }
                    });

                }

            });
            builder.create();
            builder.show();
            return true;
        }
    });
    */
}


    void trigger(DatabaseReference dRef, final BookAdapter adapter, final ListView listViewBooks) {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Book b = ds.getValue(Book.class);
                    adapter.add(b);
                    listViewBooks.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent;

        switch (item.getItemId()) {
            case (R.id.itemProfile):
                //username=null;
                username =sp.getString("username",null);
                if(username ==null) {
                    myIntent = new Intent(this, LoginActivity.class);
                    startActivityForResult(myIntent,2);
                }
                else
                {
                    myIntent = new Intent(this, ProfileActivity.class);
                    myIntent.putExtra("username",username);
                    startActivity(myIntent);
                }
                break;

            case (R.id.itemSearch):
                myIntent = new Intent(this, SearchActivity.class);
                startActivity(myIntent);
                break;

            case(R.id.itemHome):
                myIntent = new Intent(this, MainActivity.class);
                startActivity(myIntent);

            case(R.id.itemAdd):
                myIntent = new Intent(this, AddBookActivity.class);
                startActivity(myIntent);
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String text = data.getStringExtra("text");
        if (requestCode==0)
            edName.setText(text);
        if(requestCode==1)
            edDescription.setText(text);
        if(requestCode==2)
        {
            SharedPreferences.Editor ed= sp.edit();
            ed.putString("username", text);
            ed.commit();
        }

    }

}
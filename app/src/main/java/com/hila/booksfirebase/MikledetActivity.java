package com.hila.booksfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MikledetActivity extends Activity {
    Mikledet m;
    Button save;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m=new Mikledet(this);
        setContentView(m);
        save=(Button)Mikledet.save;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str=Mikledet.str;
                Intent i= new Intent();
                i.putExtra("text",str);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}

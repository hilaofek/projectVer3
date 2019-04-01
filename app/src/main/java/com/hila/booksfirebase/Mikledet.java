package com.hila.booksfirebase;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Mikledet extends LinearLayout implements View.OnClickListener
{

    static String str="";
    Context context;
    TextView tv;
    static Button save;
    public Mikledet(Context context)
    {
        super(context);
        this.context=context;
        tv=new TextView(context);
        tv.setBackgroundColor(Color.BLACK);
        tv.setTextColor(Color.WHITE);
        //tv.setTextDirection(View.TEXT_DIRECTION_FIRST_STRONG_RTL);

        //LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        this.setOrientation(VERTICAL);
        this.addView(tv);
        loadKeyboard('א');
        loadKeyboard('י');
        loadKeyboard('ע');
        loadSpaceAndDelete(); //


        // LinearLayout.LayoutParams layoutParams1
    }

    public void loadKeyboard(char ch)
    {
        LinearLayout l=new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,100,0,0); // WTF ?!
        l.setLayoutParams(layoutParams);

        for (int i=0;i<9;i++)
        {
            Button btn=new Button(context);
            btn.setBackgroundResource(android.R.drawable.editbox_background); // WTF 2
            btn.setText(String.valueOf(ch));
            LinearLayout.LayoutParams btnParam = new LinearLayout.LayoutParams(100,100);
            btn.setLayoutParams(btnParam);
            l.addView(btn); // add current Button to  View ... YA !
            ch++;
            btn.setOnClickListener(this);

        }
        this.addView(l);
    }

    public void loadSpaceAndDelete()
    {
        Button space = new Button(context);
        space.setBackgroundResource(android.R.drawable.editbox_background);
        space.setBackgroundColor(Color.GRAY);

        LinearLayout.LayoutParams btnParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParam.setMargins(20,50,20,0); // liav !!
        space.setLayoutParams(btnParam);
        space.setText("רווח");
        space.setOnClickListener(this);
        this.addView(space);

        //detete handle Btn
        Button delete = new Button(context);
        delete.setBackgroundResource(android.R.drawable.editbox_background);
        delete.setBackgroundColor(Color.GRAY);


        btnParam.setMargins(20,50,20,0); // liav !!
        delete.setLayoutParams(btnParam);
        delete.setText("מחק");
        delete.setOnClickListener(this);
        this.addView(delete);


        //LinearLayout.LayoutParams btnParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        save = new Button(context);
        save.setBackgroundResource(android.R.drawable.editbox_background);
        save.setBackgroundColor(Color.GRAY);


        btnParam.setMargins(20,50,20,0); // liav !!
        save.setLayoutParams(btnParam);
        save.setText("שמור");
        save.setOnClickListener(this);
        this.addView(save);




    }

    @Override
    public void onClick(View v)
    {
        Button btn=(Button)v;
        String s=btn.getText().toString();
        if (s.equals("מחק"))
        {
            if (this.str.length()>0)
            {
                this.str=this.str.substring(0,this.str.length()-1);
            }
        }
        else if (s.equals("רווח")){
            this.str+=" ";// concat string
        }

        else
            this.str+=btn.getText().toString();
        tv.setText(str); // View it
    }
}
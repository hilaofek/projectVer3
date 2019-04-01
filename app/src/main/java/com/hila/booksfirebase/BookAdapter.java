package com.hila.booksfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    Context context;
    List<Book> objects;

    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_book, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.textViewName);
        TextView tvdescription = (TextView)view.findViewById(R.id.textViewDescription);
        ImageView iv = (ImageView) view.findViewById(R.id.imageViewPicture);

        Book temp = objects.get(position);
        tvName.setText(temp.getName());
        tvdescription.setText(temp.getDescription());
        iv.setImageResource(R.drawable.ic_book_black_24dp);

        return view;
    }

}
package com.example.readbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.readbook.Model.Book;
import com.example.readbook.R;


import java.util.ArrayList;

public class BookListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Book> list;
    static Intent intent;
    public BookListAdapter(Context context, ArrayList<Book> arrayList) {
        super(context, R.layout.book_list_item, arrayList);
        this.context = context;
        this.list = arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.book_list_item, parent, false);

        TextView tvName;
        ImageView imgvCoverImage;

        tvName = rowView.findViewById(R.id.tv_item_name);
        imgvCoverImage = rowView.findViewById(R.id.imgV_cover_image);

        final Book item = list.get(position);
        if (item.getmImage() == null) {
            imgvCoverImage.setImageResource(R.drawable.unfound_cover_image);
        }
        else
        imgvCoverImage.setImageBitmap(item.getmImage());
        tvName.setText(item.getmName());

//        final PDFView pdfView = rowView.findViewById(R.id.pdfView);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent pdfIntent = new Intent(context, PdfReader.class);
//                pdfIntent.putExtra("file", item.getmPath());
//                context.startActivity(pdfIntent);
            }
        });
        return rowView;
    }


}

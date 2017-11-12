package com.attodeba.ads.tp_dao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.attodeba.ads.tp_dao.R;
import com.attodeba.ads.tp_dao.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by persol on 05/11/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> listBooks) {
        super(context, R.layout.list_books_view, listBooks);
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_books_view, parent, false);

         }

          Book book = getItem(index);
            String tt= book.toString();
            // Recuperation des differents composants de la vue
            TextView title = (TextView) convertView.findViewById(R.id.book_title);
            TextView price =(TextView) convertView.findViewById(R.id.book_price);
            TextView quantity =(TextView) convertView.findViewById(R.id.book_qty);
            TextView author = (TextView) convertView.findViewById(R.id.book_author);

            title.setText(" Title:  "+book.getTitle());
            price.setText(" Price:  "+book.getPrice());
            quantity.setText(" Quantity :"+Integer.toString(book.getQuantity()));
            author.setText(" Author :"+ ( (book.getAuthor()!=null)?
                                                book.getAuthor().toString()
                                                :" undefined"));


        return convertView;
    }
}

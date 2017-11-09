package com.attodeba.ads.tp_dao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.attodeba.ads.tp_dao.R;
import com.attodeba.ads.tp_dao.models.Author;

import java.util.List;

/**
 * Created by persol on 17/10/17.
 */

public class AuthorAdapter extends ArrayAdapter<Author> {

    public AuthorAdapter(Context context, List listAuthors){
        super(context, R.layout.list_books_view, listAuthors);

    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_authors_view, parent, false);
        }
        //Recuperation de l'objet author
        Author author = getItem(index);
        TextView authorName = (TextView) view.findViewById(R.id.author_name);
        TextView authorFName =(TextView) view.findViewById(R.id.author_fname);

        authorName.setText("  "+author.getName());
        authorFName.setText(author.getFirstName());
        return view;
    }
}

package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.models.Author;
import com.attodeba.ads.tp_dao.models.Book;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity {
    public EditText title;
    public EditText price;
    public EditText quantity;
    public EditText authorName;
    public EditText authorFirstName;
    public Book book;
    public Spinner authorspin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        title=(EditText) findViewById(R.id.titleTextView);
        price=(EditText) findViewById(R.id.priceTextView);
        quantity=(EditText) findViewById(R.id.qtyTextView);
        authorspin = (Spinner) findViewById(R.id.authorspinner);

        List listauthors = SugarRecord.listAll(Author.class);
        List spinValue = new ArrayList();
       /* for (Author author: listauthors ){
            spinValue.add(author.toString());
        }*/
        ArrayAdapter adapter = new ArrayAdapter(AddBook.this,android.R.layout.simple_list_item_1 ,spinValue);
        this.book= new Book();
        authorspin.setAdapter(adapter);
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean addBookOK=false;
                try{
                      Author author = new Author(authorName.getText().toString(),authorFirstName.getText().toString());
                        book.setTitle(title.getText().toString());
                        book.setPrice(price.getText().toString());
                        book.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        book.setAuthor(author);
                       if(book.isValid()) book.save();
                        addBookOK=book.isValid();
                }
                catch (Exception e) {
                    addBookOK=false;
                    Toast.makeText(AddBook.this, e.getCause().getMessage(), Toast.LENGTH_SHORT).show();
                }

                Intent result = new Intent();
                result.putExtra("bookId", book.getId());

                if(addBookOK) {
                    AddBook.this.setResult(RESULT_OK, result);
                    AddBook.this.finish();
                }


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }


}

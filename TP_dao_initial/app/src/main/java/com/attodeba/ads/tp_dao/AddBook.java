package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.models.Author;
import com.attodeba.ads.tp_dao.models.Book;
//import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity {
    public EditText titleET;
    public EditText priceET;
    public EditText quantityET;
    public Book book;
    public Spinner authorspin;
    public List<Author> listauthors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        this.titleET=(EditText) findViewById(R.id.titleET);
        this.priceET=(EditText) findViewById(R.id.priceET);
        this.quantityET=(EditText) findViewById(R.id.qtyET);
        this.authorspin = (Spinner) findViewById(R.id.authorspinner);
        this.listauthors= null;// =  SugarRecord.listAll(Author.class);
        List spinlist = new ArrayList();
       for (int i= 0; i< listauthors.size(); i++ ){
           Author author = listauthors.get(i);
            spinlist.add(author.toString());
        }
       ArrayAdapter adapter = new ArrayAdapter(AddBook.this,android.R.layout.simple_list_item_1 ,spinlist);
        this.book= new Book();
        authorspin.setAdapter(adapter);

        findViewById(R.id.saveBookButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleET.getText().toString(),
                        price=priceET.getText().toString(),
                        toastMsg;
                 int selectAuthor_id = (int)authorspin.getSelectedItemId();
                 int quantity=Integer.parseInt(quantityET.getText().toString());

                try{
                    if(price!="" && title!="" && quantity>=0){
                        Author author = listauthors.get(selectAuthor_id);
                        book.setTitle(title);
                        book.setPrice(price);
                        book.setQuantity(quantity);
                        book.setAuthor(author);
                        //Etape 5.1 ajout d'un livre dans la base donnée
                       // SugarRecord.save(book);
                       // List<Book> liste = SugarRecord.listAll(Book.class);
                        toastMsg="le livre "+book.toString()+" est bien ajouté";
                        Toast.makeText(AddBook.this,toastMsg, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        toastMsg="champs vides";
                        Toast.makeText(AddBook.this,toastMsg, Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e) {
                    toastMsg="data base exception";
                    Toast.makeText(AddBook.this,toastMsg, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //this.listauthors = SugarRecord.listAll(Author.class);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }


}

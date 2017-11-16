package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.adapter.AuthorAdapter;
import com.attodeba.ads.tp_dao.adapter.BookAdapter;
import com.attodeba.ads.tp_dao.models.Author;
import com.attodeba.ads.tp_dao.models.Book;
//import com.orm.SugarRecord;

import java.util.List;


public class Home extends AppCompatActivity {
    public Book book;
    public List<Author> list;
    public List<Book> listBook;
    public ListView listView;
    public Intent intent;
    final  String UPDATE_DATA= "UPDATE";
    final  String ADD_DATA= "ADD";
    final String AUTHOR_ID="author_id";
    final String BOOK_ID="book_id";
    final String LISTING = "listing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
                 Book book = listBook.get(position);
                 String msg = book.toString() + " est bien supprimé de la base de donnée ";
             //    book.delete();
                 updateView();
                 Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
                Book book = listBook.get(position);
                intent = new Intent(Home.this, AddBook.class);
                long id = book.getId();
                intent.putExtra(BOOK_ID,(int)id);
                intent.setAction(UPDATE_DATA);
                startActivity(intent);

            }
        });

    }

    public void updateView(){
        listView = (ListView) findViewById(R.id.listView);
        try {

        /*    list = Author.listAll(Author.class);
            listBook = SugarRecord.listAll(Book.class);
            BookAdapter  adapterBook = new BookAdapter(Home.this,listBook);
            AuthorAdapter adapter = new AuthorAdapter(Home.this,list);
            listView.setAdapter(adapterBook);
            */
        }
        catch (Exception e){
            Toast.makeText(Home.this, "no data loading", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_navigation_home, menu);
        return true;
    }

 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (item.getItemId()){
            case R.id.navigation_books:
                intent=new  Intent(getApplicationContext(),AddBook.class);
                startActivity(intent);
                return true;
            case R.id.navigation_authors:
                intent=new  Intent(getApplicationContext(),AuthorList.class);
                startActivity(intent);
                return true;
            case R.id.action_add_authors:
                 intent=new  Intent(getApplicationContext(),AuthorActivity.class);
                 intent.setAction(ADD_DATA);
                 startActivity(intent);
                 return true;

            default:
                return true;//super.onOptionsItemSelected(item);
        }
    }

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem item) {
          Intent intent;
          switch (item.getItemId()) {
              case R.id.navigation_authors:

                  intent = new Intent(getApplicationContext(), AuthorList.class);
                  startActivity(intent);
                  return true;
              case R.id.navigation_books:
                  intent = new Intent(getApplicationContext(), AddBook.class);
                  intent.setAction("addBook");
                  startActivity(intent);
                  return true;

              case R.id.action_add_book:
                  intent = new Intent(getApplicationContext(), AddBook.class);
                  intent.setAction("addBook");
                  startActivity(intent);
                  return true;
          }
          return false;
      }
  };

}

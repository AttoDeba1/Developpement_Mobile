package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.os.Bundle;
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
import com.attodeba.ads.tp_dao.models.Author;
import com.attodeba.ads.tp_dao.models.Book;

import java.util.List;


public class Home extends AppCompatActivity {
    public Book book;
    public List<Author> list;
    public ListView listView;
    public Intent intent;

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
/*
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
                 Author auteur = list.get(position);
                 String msg = auteur.toString() + " est bien supprimé de la base de donnée ";
             //    auteur.delete();
                 updateView();
                 Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
 */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
                Author auteur = list.get(position);
                String msg = auteur.toString() + " est bien modifié de la base de donnée ";
                intent = new Intent(Home.this, AuthorActivity.class);
               // intent.putExtra("auhor_id", auteur.getId());
                Home.this.setResult(RESULT_OK, intent);
                startActivityForResult(intent , 0);

            }
        });

    }

    public void updateView(){
        listView = (ListView) findViewById(R.id.listView);
        try {
           // list = Author.listAll(Author.class);
            AuthorAdapter adapter = new AuthorAdapter(Home.this,list);
            listView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(Home.this, "no data loading", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(Home.this, "ajouté", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(Home.this, "non ajouté", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_add_book:
                intent=new  Intent(getApplicationContext(),AddBook.class);
                startActivity(intent);
                return true;
            case R.id.action_add_authors:
                intent=new  Intent(getApplicationContext(),AuthorActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

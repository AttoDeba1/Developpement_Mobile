package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.adapter.AuthorAdapter;
import com.attodeba.ads.tp_dao.models.Author;

import java.util.List;

public class AuthorList extends AppCompatActivity {

    public List<Author> list;
    public ListView listView;
    public Intent intent;

      /**les constantes**/
    final String UPDATE_DATA= "UPDATE";
    final String AUTHOR_ID="author_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);
        listView = (ListView) findViewById(R.id.authorListView);
        updateView();
    }
    /**
     * Methode permettant d'afficher la liste de tous les auteurs sauvegardés dans la base de donnée
     * */
    public void updateView(){
        try {
            list = Author.listAll(Author.class);
            AuthorAdapter adapter = new AuthorAdapter(AuthorList.this,list);
            listView.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(AuthorList.this, "no data loading", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                Author auteur = list.get(position);
                String msg = auteur.toString() + " est bien supprimé de la base de donnée ";
                auteur.delete();
                updateView();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                return true;
                 }
            });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                Author auteur = list.get(position);
                intent = new Intent(getApplicationContext(), AuthorActivity.class);
                long id = auteur.getId();
                intent.putExtra(AUTHOR_ID,(int)id);
                intent.setAction(UPDATE_DATA);
                startActivity(intent);
             }
            });
    }
}

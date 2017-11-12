package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.models.Author;
import com.orm.SugarRecord;

import java.util.List;

public class AuthorActivity extends AppCompatActivity {
    final String UPDATE_DATA= "UPDATE";
    final String AUTHOR_ID="author_id";
    //final String LISTING = "listing";
    final String SAVE_ACTION="save";
    final String UPDATE_ACTION="update";
    public EditText  authorFirstNameET;
    public EditText authorNameET;
    public Button saveButton;
    public Author author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        this.authorFirstNameET=(EditText) findViewById(R.id.authorFName);
        this.authorNameET = (EditText) findViewById(R.id.authorName);
        this.saveButton = (Button) findViewById(R.id.saveAuthorButton);
        this.author= new Author();

    }
   @Override
   public void onStart(){
        super.onStart();
        this.author=new Author();
       Intent result = getIntent();
       String action = result.getAction();
       if(action==UPDATE_DATA) {
           int author_id = getIntent().getIntExtra(AUTHOR_ID,11 );
           setUpdateView(author_id);
       }
       else{
           saveButton.setText(SAVE_ACTION);
           if(action==null) setContentView(R.layout.activity_author);
       }
   }
     @Override
     public void onResume(){
         super.onResume();
         Intent result = getIntent();
         String action = result.getAction();
         findViewById(R.id.saveAuthorButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 authorFirstNameET=(EditText)findViewById(R.id.authorFName);
                 authorNameET = (EditText) findViewById(R.id.authorName);

                 boolean saveOK=false;
                 String toastMsg;

                 try{
                     String name= authorNameET.getText().toString();
                     String firstname = authorFirstNameET.getText().toString();
                     if(name!="" && firstname!=""){
                         boolean isSaveAction = (saveButton.getText().toString()==SAVE_ACTION);

                         if(isSaveAction){
                             saveAuthor(name,firstname);
                          }
                         else{
                             long id=author.getId();
                             updateAuthor(id,name,firstname);
                         }
                         saveOK=author.isValid();
                         toastMsg= author.toString()+"est bien "+(isSaveAction?"ajouté":"modifié")+" dans la base de donnée";
                         Toast.makeText(AuthorActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
                     }
                     else {
                         toastMsg="champs vides";
                         Toast.makeText(AuthorActivity.this,toastMsg, Toast.LENGTH_SHORT).show();
                     }

                 }
                 catch (Exception e) {
                     saveOK=false;
                     toastMsg= "database exception"+e.getCause().getMessage().toString();
                     Toast.makeText(AuthorActivity.this,toastMsg , Toast.LENGTH_SHORT).show();
                 }

                 Intent result = new Intent();

                 if(saveOK) {
                     setResult(RESULT_OK, result);
                     finish();
                 }


             }

         });

     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }
    public void setUpdateView(int id){
        author = SugarRecord.findById(Author.class, (long)id);
        authorFirstNameET.setText(author.getFirstName());
        authorNameET.setText(author.getName());
        saveButton.setText(UPDATE_ACTION);
    }


    /****methode permetant de mettre à jour un auteur *****/
    public void updateAuthor(long authorId, String firstname, String name){
        author= SugarRecord.findById(Author.class,authorId);
        author.setFirstName(firstname);
        author.setName(name);
        SugarRecord.save(author);
        //author.save();
        saveButton.setText(SAVE_ACTION);
    }
    /****methode permetant d'ajouter un nouveau auteur *****/
    public void saveAuthor(String name, String firstname){
        author = new Author(name,firstname);
        SugarRecord.save(author);
    }

}

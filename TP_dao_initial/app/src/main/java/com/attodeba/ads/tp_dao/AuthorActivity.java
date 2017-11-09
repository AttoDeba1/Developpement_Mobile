package com.attodeba.ads.tp_dao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.attodeba.ads.tp_dao.models.Author;
//import com.orm.SugarRecord;

public class AuthorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
    }

     @Override
     public void onResume(){
         super.onResume();
         setContentView(R.layout.activity_author);
         findViewById(R.id.saveAuthorButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 EditText authorFirstName=(EditText) findViewById(R.id.authorFName);
                 EditText authorName = (EditText) findViewById(R.id.authorName);
                 Button saveButton = (Button) findViewById(R.id.saveAuthorButton);
                 boolean saveOK=false;
                 String toastMsg;

                 try{
                     String name= authorName.getText().toString();
                     String firstname = authorFirstName.getText().toString();
                     Author author;
                     if(name!="" && firstname!=""){
                         author = new Author(name,firstname);
                   //     if(author.isValid()) author.save();
                         saveOK=author.isValid();
                         toastMsg= author.toString()+"est bien ajouté dans la base de donnée";
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      int id;
        id = data.getExtras().getInt("author_id");
       // Author auteur = SugarRecord.findById(Author.class, id);
        //Toast.makeText(AuthorActivity.this, auteur.toString(), Toast.LENGTH_SHORT).show();

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }
}

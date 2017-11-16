package com.attodeba.ads.tp_dao.models;

//Etape 1
/*
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
*/
//@Table(name="Author")
public class Author /* extends SugarRecord */  {
   // @Column(name="name")
    private String name;
   // @Column(name="firstName")
    private String firstName;
    public Author() {
    }

    public Author(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
    //Methode à commenter
    public long getId(){ return 2;}

    //getteur et setteur de la classe
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isValid(){
        return this.firstName!=null && this.name!=null ;
    }
    @Override
    public String toString(){
        return this.getId()+"  "+this.getName()+"  "+this.getFirstName();
    }


}

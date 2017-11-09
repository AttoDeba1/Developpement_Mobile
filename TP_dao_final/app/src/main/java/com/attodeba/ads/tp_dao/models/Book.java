package com.attodeba.ads.tp_dao.models;

/**
 * Created by persol on 12/10/17.
 */
import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

@Table(name="Book")
public class Book extends SugarRecord {
     @Column(name="title")
     private String title;
    @Column(name = "price")
    private  String price;
    @Column(name="quantity")
    private  int  quantity;
    @Ignore
    private Author author;

    public Book() {this.author = SugarRecord.last(Author.class); }
    public Book(String title,String price){
        this.title=title;
        this.price=price;
        this.author = SugarRecord.last(Author.class);
    }

    public Book(String title, String price, int quantity, Author authors) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.author = authors;
    }
  /*******************GETTER***********************/
    public String getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }
    public int getQuantity() {
        return quantity;
    }
    public Author getAuthor() {
        return author;
    }
    public void setPrice(String price) {
        this.price = price;
    }


    /*******************SETTER***********************/
    public void setTitle(String title)  {
         this.title = title;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return this.title;
    }
    public boolean isValid(){
        return (this.title!=null) && (this.price!=null) ; //&& (this.author.isValid());
    }

}

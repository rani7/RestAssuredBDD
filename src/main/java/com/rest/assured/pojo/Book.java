package com.rest.assured.pojo;

public class Book {
	
	private String category;
    private String author;
    private String title;
    private int bookPrice;
    
    public Book(String category,String author, String title, int bookPrice) {
    	this.category=category;
    	this.author=author;
    	this.title=title;
    	this.bookPrice=bookPrice;
    }

   public String getCategory() {
	   return category;
   }
   
   public String getAuthor() {
	   return author;
   }
   
   public String getTitle() {
	   return title;
   }
   
   public int getBookPrice() {
	   return bookPrice;
   }
}

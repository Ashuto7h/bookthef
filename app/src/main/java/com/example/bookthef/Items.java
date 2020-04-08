package com.example.bookthef;

import androidx.appcompat.app.AppCompatActivity;

public class Items  {
    public String description;
    public String image ;
    public String title ;
    public String author ;
    public String price;
    public String rating;
    public String isbn_10 ;
    public String isbn_13;
    public String originalPrice;
    public String language;
    public String publisher ;
    public String published_date;
    public String pages;
    public String location;
    public String condition;
    public String coverType;
    public String quantity = 0 + "";

    public Items(){}

    public Items(String image , String publisher , String publishedDate , String pages , String language, String rating, String coverType , String condition , String isbn_10, String isbn_13, String description ,String quantity) {
        this.image = image;
        this.publisher = publisher;
        this.published_date = publishedDate;
        this.pages = pages;
        this.rating = rating;
        this.coverType = coverType;
        this.language = language;
        this.condition = condition;
        this.isbn_10 = isbn_10;
        this.isbn_13 = isbn_13;
        this.description = description;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRating() {
        return rating;
    }

    public void set_title(String title){
        this.title = title;
    }
    public String get_title(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setIsbn_13(String isbn_13) {
        this.isbn_13 = isbn_13;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }

    public String getIsbn_13() {
        return isbn_13;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public String getPrice() {
        return price;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}

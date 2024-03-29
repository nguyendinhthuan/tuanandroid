package com.example.student.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Book {
    private int id;
    private String title;
    private String author;

    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
    }

    public Book(int id, String title, String nameAuthor) {
        this.id = id;
        this.title = title;
        this.author = nameAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

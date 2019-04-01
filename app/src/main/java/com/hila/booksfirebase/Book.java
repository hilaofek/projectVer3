package com.hila.booksfirebase;

import java.util.ArrayList;

public class Book {
    private int id;
    private String name;
    private String description;
    private ArrayList<Chapter> chapters;
    private int numOfChapters;

    public Book(int id, String name, String description, ArrayList<Chapter> chapters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.chapters=chapters;
        this.numOfChapters=1;

    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Chapter> getChapters(){return this.chapters;}

   public void setChapters(ArrayList<Chapter> chapters)
   {
       this.chapters=chapters;
   }

   public void addChapter(Chapter c)
   {
       this.chapters.add(c);
   }
    public void nextChapter()
    {
        this.numOfChapters++;
    }

    public int getNumOfChapters(){
        return this.numOfChapters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
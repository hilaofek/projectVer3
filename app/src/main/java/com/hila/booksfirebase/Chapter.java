package com.hila.booksfirebase;

import java.util.ArrayList;

public class Chapter {
    String name;
    ArrayList<String> chapter;
    int numOfChapter;
    String id;

    public Chapter(String name,ArrayList<String> chapter,int numOfChapter,String id)
    {
        this.name=name;
        this.chapter=chapter;
        this.numOfChapter=numOfChapter;
        this.id=id;
    }

    public String getName()
    {
        return this.name;
    }

    public ArrayList<String>getChapter()
    {
        return this.chapter;
    }

    public int getNumOfChapter(){
        return this.numOfChapter;
    }

    public String getId()
    {
        return this.id;
    }
}


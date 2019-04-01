package com.hila.booksfirebase;

import java.util.ArrayList;
import java.util.Vector;

public class User
{
    private String name;
    private String bio;
    private String email;
    String IDUser;
    private boolean isLoggedIn;
    //ArrayList<Chapter>chapterWritten;
    Vector<Long> liked;

    public User(){}
    public User (String name,String email,String IDUser)
    {
        this.name=name;
        this.email=email;
        this.IDUser=IDUser;
        this.liked=new Vector<Long>();
        this.isLoggedIn=true;
    }

    public String getUserName()
    {
        return this.name;
    }
    public void setUserName(String name)
    {
        this.name=name;
    }
    public String getBio()
    {
        return this.bio;
    }
    public void setBio(String bio)
    {
        this.bio=bio;
    }

    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getIDUser()
    {
        return this.IDUser;
    }
    public void setIDUser(String IDUser)
    {
        this.IDUser=IDUser;
    }

    public void addLiked(long IDBook)
    {
        this.liked.add(IDBook);
    }

    public void removeliked(long IDBook)
    {
        this.liked.remove(IDBook);
    }

    public  boolean isLoggedIn()
    {
        return this.isLoggedIn;
    }

    public void logIn()
    {
        this.isLoggedIn=true;
    }






}

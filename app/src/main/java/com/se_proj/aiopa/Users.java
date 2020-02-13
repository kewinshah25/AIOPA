package com.se_proj.aiopa;

public class Users {

    //private variables
    int _id; // Just to have it.
    String _userName;
    String _passWord;
    String _name;
    String _budget;
    String _weight;
    String _height;
    String _sex;

    // Empty constructor
    public Users(){

    }
    // constructor
    public Users(String userName, String _passWord, String _name, String _budget, String _weight, String _height, String _sex){
        this._userName = userName;
        this._passWord = _passWord;
        this._name = _name;
        this._budget = _budget;
        this._weight = _weight;
        this._height = _height;
        this._sex = _sex;

    }
    // getting User
    public String getUser(){
        return this._userName;
    }

    // getting Password
    public String getPassword(){
        return this._passWord;
    }

    public String getName(){
        return this._name;
    }

    public String getBudget(){
        return this._budget;
    }

    public String getWeight(){
        return this._weight;
    }

    public String getHeight(){
        return this._height;
    }

    public String getSex(){
        return this._sex;
    }
}
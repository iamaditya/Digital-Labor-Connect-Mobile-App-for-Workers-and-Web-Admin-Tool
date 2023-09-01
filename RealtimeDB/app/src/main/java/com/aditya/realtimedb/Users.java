package com.aditya.realtimedb;

public class Users {
    String name;
    String no;
    Users(String name,String no){
        this.name = name;
        this.no = no;
    }
    Users(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}

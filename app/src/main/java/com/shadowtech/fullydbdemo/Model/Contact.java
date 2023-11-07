package com.shadowtech.fullydbdemo.Model;

public class Contact {
    private int id ;
    private String name ;
    private String phone ;
    private String time ;

    public Contact(int id, String name, String phone, String time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }
    public Contact(String name, String phone, String time) {

        this.name = name;
        this.phone = phone;
        this.time = time;
    }

    public Contact(int id) {
        this.id = id;
    }

    public Contact(String name, String phone) {

        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

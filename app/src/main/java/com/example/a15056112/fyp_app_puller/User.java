package com.example.a15056112.fyp_app_puller;

/**
 * Created by 15056112 on 7/8/2017.
 */

public class User {
    private String name;
    private String role;
    private String uid;

    public User(){

    }

    public User(String name, String role, String uid) {
        this.name = name;
        this.role = role;
        this.uid = uid;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

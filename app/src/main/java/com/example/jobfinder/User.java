package com.example.jobfinder;

public class User {

    public String fullName, email;

    public User() {

    }

    public User(String fn, String e) {
        fullName = fn;
        email = e;
    }

    public String getFullName () {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

}

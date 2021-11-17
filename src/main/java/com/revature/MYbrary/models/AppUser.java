package com.revature.MYbrary.models;

public class AppUser extends Object {

    private String id;
    private String personalName;
    private String email;
    private String username;
    private String password;

    public AppUser(String personalName, String email, String username, String password) {
        this.personalName = personalName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public AppUser(String id, String personalName, String email, String username, String password) {
        this(personalName, email, username, password);
        this.id = id;
    }

    public AppUser() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

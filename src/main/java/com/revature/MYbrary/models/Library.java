package com.revature.MYbrary.models;

public class Library extends Object {

    private Integer id;
    private String name;
    private Integer user_id;

    public Library(String name, Integer user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public Library(Integer id, String name, Integer user_id) {
        this(name, user_id);
        this.id = id;
    }

    public Library() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }
}

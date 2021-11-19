package com.revature.MYbrary.models;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.util.LinkedList;

public class Library extends Object {

    private BookDAO bookDao = new BookDAO();
    private Integer id;
    private String name;
    private String user_id;

    public Library(String name, String user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public Library(Integer id, String name, String user_id) {
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

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public LinkedList<Book> getBooks() {
        return bookDao.findAll(this.id);
    }
}

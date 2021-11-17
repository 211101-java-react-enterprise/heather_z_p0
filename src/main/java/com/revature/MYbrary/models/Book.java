package com.revature.MYbrary.models;

public class Book extends Object {
    private Integer id;
    private String title;
    private String author;
    private Integer page_count;
    private Integer library_id;

    public Book(String title, String author, Integer page_count, Integer library_id) {
        this.title = title;
        this.author = author;
        this.page_count = page_count;
        this.library_id = library_id;
    }

    public Book(Integer id, String title, String author, Integer page_count, Integer library_id) {
        this(title, author, page_count, library_id);
        this.id = id;
    }

    public Book() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return page_count;
    }

    public void setPageCount(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getLibraryId() {
        return library_id;
    }

    public void setLibraryId(Integer library_id) {
        this.library_id = library_id;
    }
}

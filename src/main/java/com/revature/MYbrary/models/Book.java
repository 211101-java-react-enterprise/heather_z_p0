package com.revature.MYbrary.models;

public class Book extends Object {
    private Integer id;
    private String title;
    private String author;
    private Integer page_count;
    private Integer current_page = 0;
    private Integer library_id;

    public Book(String title, String author, Integer page_count, Integer current_page, Integer library_id) {
        this.title = title;
        this.author = author;
        this.page_count = page_count;
        this.current_page = current_page;
        this.library_id = library_id;
    }

    public Book(String title, String author, Integer page_count, Integer library_id) {
        this(title, author, page_count, 0, library_id);
    }

    public Book(Integer id, String title, String author, Integer page_count, Integer current_page, Integer library_id) {
        this(title, author, page_count, current_page, library_id);
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

    public Integer getCurrentPage() {
        return current_page;
    }

    public void setCurrentPage(Integer current_page) {
        this.current_page = current_page;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getLibraryId() {
        return library_id;
    }

    public void setLibraryId(Integer library_id) {
        this.library_id = library_id;
    }
}

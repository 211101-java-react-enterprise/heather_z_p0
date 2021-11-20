package com.revature.MYbrary.models;

import com.revature.MYbrary.daos.AnnotationDAO;

public class Annotation extends Object {

    private AnnotationDAO annotationDAO = new AnnotationDAO();
    private Integer id;
    private String startingWords;
    private Integer startingPage;
    private String endingWords;
    private Integer endingPage;
    private String notes;
    private Integer bookId;

    public Annotation(String startingWords, Integer startingPage, String endingWords, Integer endingPage, String notes, Integer bookId) {
        this.startingWords = startingWords;
        this.startingPage = startingPage;
        this.endingWords = endingWords;
        this.endingPage = endingPage;
        this.notes = notes;
        this.bookId = bookId;
    }

    public Annotation(){
        super();
    }

    public AnnotationDAO getAnnotationDAO() {
        return annotationDAO;
    }

    public void setAnnotationDAO(AnnotationDAO annotationDAO) {
        this.annotationDAO = annotationDAO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartingWords() {
        return startingWords;
    }

    public void setStartingWords(String startingWords) {
        this.startingWords = startingWords;
    }

    public Integer getStartingPage() {
        return startingPage;
    }

    public void setStartingPage(Integer startingPage) {
        this.startingPage = startingPage;
    }

    public String getEndingWords() {
        return endingWords;
    }

    public void setEndingWords(String endingWords) {
        this.endingWords = endingWords;
    }

    public Integer getEndingPage() {
        return endingPage;
    }

    public void setEndingPage(Integer endingPage) {
        this.endingPage = endingPage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}

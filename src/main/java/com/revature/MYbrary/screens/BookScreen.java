package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.BookService;
import com.revature.MYbrary.services.LibraryService;
import com.revature.MYbrary.util.ScreenRouter;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;

import java.io.BufferedReader;

public class BookScreen extends Screen {
    private Library library;
    public BookScreen(BufferedReader consoleReader, ScreenRouter router, Library library) {
        super("BookScreen", "/books", consoleReader, router);
        this.library = library;
    }

    private BookDAO bookDAO = new BookDAO();
    private BookService bookService = new BookService(bookDAO);

    @Override
    public void render() throws Exception {
        Book activeBook = bookService.promptBookSelection(library.getId(), consoleReader);
    }
}

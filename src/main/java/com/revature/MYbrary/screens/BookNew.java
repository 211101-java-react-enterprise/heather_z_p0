package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.BookService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class BookNew extends Screen {
    private Library library;
    public BookNew(BufferedReader consoleReader, ScreenRouter router, Library library) {
        super("BookNew", "/books", consoleReader, router);
        this.library = library;
    }

    private BookDAO bookDAO = new BookDAO();
    private BookService bookService = new BookService(bookDAO);

    @Override
    public void render() throws Exception {
        System.out.println("Please enter details for your new book.");

        System.out.print("TITLE: ");
        String title = consoleReader.readLine();
        System.out.print("AUTHOR: ");
        String author = consoleReader.readLine();
        System.out.print("PAGE COUNT: ");
        Integer pageCount = Integer.parseInt(consoleReader.readLine());

        Book newBook = new Book(title, author, pageCount, library.getId());

        // Library newLibrary = new Library(libraryName, userService.getSessionUser().getId());
        try {
            bookService.createNewBook(newBook);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

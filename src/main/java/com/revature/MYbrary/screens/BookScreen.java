package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.BookService;
import com.revature.MYbrary.services.LibraryService;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;

import static com.revature.MYbrary.util.AppState.shutdown;

import java.io.BufferedReader;

public class BookScreen extends Screen {
    private final UserService userService;
    public BookScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookScreen", "/books", consoleReader, router);
        this.userService = userService;
    }

    private BookDAO bookDAO = new BookDAO();
    private BookService bookService = new BookService(bookDAO);

    @Override
    public void render() throws Exception {
        // Book activeBook = bookService.promptBookSelection(library.getId(), consoleReader);
        Book activeBook = new Book();

        System.out.printf("======== %s by %s ========"); //, activeBook.getTitle().toUpperCase(), activeBook.getAuthor());

        System.out.print(" 1) Update Page\n" +
                " 2) Exit\n\n" +
                "> ");
        String userSelection = consoleReader.readLine();

        switch(userSelection) {
            case "1":
                System.out.print("CURRENT PAGE: ");
                bookService.updateCurrentPage(activeBook, Integer.parseInt(consoleReader.readLine()));
                break;
            case "2":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }


    }
}

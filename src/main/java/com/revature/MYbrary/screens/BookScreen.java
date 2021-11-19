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
import java.sql.SQLException;

public class BookScreen extends Screen {
    private final UserService userService;
    public BookScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookScreen", "/book", consoleReader, router);
        this.userService = userService;
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        Book activeBook = userService.getSessionBook();

        if (activeBook == null) {
            router.navigate("/book-select"); // If they get to this page without an active book, kick them back to the selection menu.
        }

        // Also need to have a "view annotations" menu

        System.out.printf("======== %s by %s ========\n", activeBook.getTitle().toUpperCase(), activeBook.getAuthor());

        System.out.print(" 1) Update Page\n" +
                " 2) View Annotations\n" +
                " 3) Back to Dashboard\n\n" +
                "> ");
        String userSelection = consoleReader.readLine();

        switch(userSelection) {
            case "1":
                System.out.print("CURRENT PAGE: ");
                String userInput = consoleReader.readLine();
                try {
                    Integer newPage = Integer.parseInt(userInput);
                    bookDAO.updateCurrentPage(activeBook, newPage);
                    router.navigate("/book");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                router.navigate("/annotation-select");
            case "3":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }


    }
}

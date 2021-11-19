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

        System.out.println(activeBook.getTitle().toUpperCase());
        System.out.println("    by " + activeBook.getAuthor());
        System.out.printf("\nOn page %d of %d\n\n", activeBook.getCurrentPage(), activeBook.getPageCount());

        System.out.print(" 1) Update Page\n" +
                " 2) View Annotations\n" +
                " 3) New Annotation\n" +
                " 4) Loan to Another User\n" +
                " 5) Back to Dashboard\n\n" +
                "> ");
        String userSelection = consoleReader.readLine();

        switch(userSelection) {
            case "1":
                router.navigate("/update-page");
                break;
            case "2":
                router.navigate("/new-annotation");
                break;
            case "3":
                router.navigate("/select-annotation");
                break;
            case "4":
                router.navigate("/loan");
                break;
            case "5":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }


    }
}

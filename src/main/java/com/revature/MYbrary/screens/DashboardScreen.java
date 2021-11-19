/*
 * Dashboard has all the basic features we want
 * 1) Select Active Library (New Library on this screen, as the last item in the list above "Nevermind...").
 *      - Nay, have Library Settings here and then Select Active Library in that screen, along with New and Modify Name
 * 2) View Books (redirect to 1 if the user hasn't picked one this session)
 * 3) Add New Book
*/
package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

import static com.revature.MYbrary.util.AppState.shutdown;

public class DashboardScreen extends Screen {
    private final UserService userService;
    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        Library library = userService.getSessionLibrary();
        try {
            LinkedList<Book> libraryBooks = library.getBooks();
        } catch (Exception e) {
            System.out.println("No books in library!");
        }

        System.out.printf("~~~~~~~~ %s's Library ~~~~~~~~\n", library.getName());
        System.out.println(" 1) View Books\n 2) New Book\n 3) Change Libraries\n 4) Create a New Library");
        System.out.print("> ");
        String userInput = consoleReader.readLine();

        switch (userInput) {
            case "1":
                router.navigate("/select-book");
                break;
            case "2":
                router.navigate("/new-book");
                break;
            case "3":
                router.navigate("/select-library");
                break;
            case "4":
                router.navigate("/new-library");
                break;
        }

    }
}

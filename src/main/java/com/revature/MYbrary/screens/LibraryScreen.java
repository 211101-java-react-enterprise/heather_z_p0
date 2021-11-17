package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.services.LibraryService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.ScreenRouter;

import static com.revature.MYbrary.util.AppState.shutdown;

import java.io.BufferedReader;

public class LibraryScreen extends Screen {
    private final UserService userService;
    public LibraryScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibraryScreen", "/libraries", consoleReader, router);
        this.userService = userService;
    }

    private LibraryDAO libraryDAO = new LibraryDAO();
    private LibraryService libraryService = new LibraryService(libraryDAO);

    @Override
    public void render() throws Exception {
        AppUser user = userService.getSessionUser();

        Library activeLibrary = libraryService.promptLibrarySelection(user, consoleReader);

        System.out.printf("======== LIBRARY - %s ========", activeLibrary.getName());
        System.out.print("\n" +
                " 1) New Book\n" +
                " 2) View Books\n" +
                " 3) Exit\n\n" +
                "> ");
        String userSelection = consoleReader.readLine();

        switch (userSelection) {
            case "1":
                router.navigate("/new-book");
                break;
            case "2":
                router.navigate("/books");
            case "3":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }
    }
}

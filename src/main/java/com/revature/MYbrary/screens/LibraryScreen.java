package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.services.LibraryService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.ScreenRouter;

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
        // Display all libraries, then prompt the user to select one.
        // Then render the specifics
        AppUser user = userService.getSessionUser();
        Library activeLibrary = libraryService.promptLibrarySelection(user, consoleReader);

        System.out.printf("======== LIBRARY - %s ========", activeLibrary.getName());

    }
}

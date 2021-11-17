package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.services.LibraryService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LibraryNew extends Screen {
    private final UserService userService;
    public LibraryNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibraryNew", "/new-library", consoleReader, router);
        this.userService = userService;
    }

    private LibraryDAO libraryDAO = new LibraryDAO();
    private LibraryService libraryService = new LibraryService(libraryDAO);

    @Override
    public void render() throws Exception {
        System.out.println("Please enter a name for your new Library.");
        System.out.print("LIBRARY NAME: ");
        String libraryName = consoleReader.readLine();
        if (libraryName == null || libraryName == "") {
            libraryName = "My Library";
        }

        Library newLibrary = new Library(libraryName, userService.getSessionUser().getId());
        try {
            libraryService.createNewLibrary(newLibrary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

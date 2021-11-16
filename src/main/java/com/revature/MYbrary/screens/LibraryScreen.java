package com.revature.MYbrary.screens;

import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LibraryScreen extends Screen {
    private final UserService userService;
    public LibraryScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibraryScreen", "/libraries", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        // Display all libraries, then prompt the user to select one.
        // Then render the specifics

        // LibraryDAO > getAll()
        // foreach, display a new line. Ask the user to make a selection, secretly search by ID

    }
}

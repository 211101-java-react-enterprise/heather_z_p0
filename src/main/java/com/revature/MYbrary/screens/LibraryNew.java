package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LibraryNew extends Screen {

    private final UserService userService;
    private final Logger logger;

    public LibraryNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibraryNew", "/new-library", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    private LibraryDAO libraryDAO = new LibraryDAO();

    @Override
    public void render() throws Exception {
        System.out.println("Please enter a name for your new Library.");
        System.out.print("LIBRARY NAME: ");
        String libraryName = consoleReader.readLine();
        if (libraryName.equals("") | libraryName.equals(null)) { // This is repeated from Register... I think I should move it to a LibraryService?
            libraryName = userService.getSessionUser().getPersonalName() + "'s Library";
        }

        Library newLibrary = new Library(libraryName, userService.getSessionUser().getId());

        try {
            Library createdLibrary = libraryDAO.save(newLibrary);
            userService.setSessionLibrary(createdLibrary.getId());
            logger.log("Created Library ID " + createdLibrary.getId());
            router.navigate("/dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

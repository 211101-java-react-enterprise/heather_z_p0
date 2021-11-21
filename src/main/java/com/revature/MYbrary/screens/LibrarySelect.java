package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LibrarySelect extends Screen {

    private final UserService userService;
    private final Logger logger;

    public LibrarySelect(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibrarySelect", "/select-library", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    LibraryDAO libraryDAO = new LibraryDAO();

    @Override
    public void render() throws Exception {
        AppUser user = userService.getSessionUser();
        Library activeLibrary = userService.getSessionLibrary();
        LinkedList<Library> libraries = libraryDAO.findAll(user.getId());

        System.out.println("==== All Libraries ====");

        StringBuilder consoleOutput = new StringBuilder();
        for (int i = 0; i < libraries.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            Library thisLibrary = libraries.get(i);
            consoleOutput.append(thisLibrary.getName());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        String userInput = consoleReader.readLine();

        try {
            Integer selectedLibraryId = Integer.parseInt(userInput) - 1;
            Library userSelection = libraries.get(selectedLibraryId);
            userService.setSessionLibrary(userSelection.getId());
            logger.log("Library ID " + selectedLibraryId + " is now the active Library");
            router.navigate("/dashboard");
        } catch (NumberFormatException e) {
            System.out.println("Input could not be interpreted as an Integer.");
            router.navigate("/dashboard");
        } catch (RuntimeException e) {
            System.out.println("Selection could not be found in the above list.");
            router.navigate("/dashboard");
        }

    }
}

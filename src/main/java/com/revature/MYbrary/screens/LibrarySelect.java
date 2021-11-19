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

public class LibrarySelect extends Screen {
    private final UserService userService;
    public LibrarySelect(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LibrarySelect", "/select-library", consoleReader, router);
        this.userService = userService;
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
            Library userSelection = libraries.get(Integer.parseInt(userInput) - 1);
            userService.setSessionLibrary(userSelection.getId());
            router.navigate("/dashboard");
        } catch (Exception e) {
            System.out.println("~~~~ ERROR ~~~~ Trouble with reading console input");
            e.printStackTrace();
        }

    }
}

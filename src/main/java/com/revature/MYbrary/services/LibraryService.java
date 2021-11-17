package com.revature.MYbrary.services;

import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.exceptions.ResourcePersistenceException;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;

import java.io.BufferedReader;

public class LibraryService {

    private final LibraryDAO libraryDAO;
    private Library activeLibrary = null; // This isn't right, but I'll fix it when it breaks

    public LibraryService(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    public boolean createNewLibrary (Library newLibrary) {
        Library createdLibrary = libraryDAO.save(newLibrary);
        if (createdLibrary != null) {
            return true;
        } else {
            throw new ResourcePersistenceException("The library could not be persisted to the datasource!");
        }
    }

    // TODO - Fix Me!
    public Library getLibrary() {
        return activeLibrary;
    }

    public LinkedList<Library> getUserLibraries(String userId) {
        System.out.println("~~~~ FLAG - LibraryService - L36 ~~~~\n" + userId);
        LinkedList<Library> userLibraries = libraryDAO.findAll(userId);

        if (userLibraries != null) {
            return userLibraries;
        } else {
            return null;
        }

    }

    public Library promptLibrarySelection(AppUser user, BufferedReader consoleReader) {
        Library activeLibrary = null;
        StringBuilder consoleOutput = new StringBuilder();

        LinkedList<Library> userLibraries = getUserLibraries(user.getId());
        System.out.println("~~~~ FLAG - LibraryService- L51 ~~~~\n" + userLibraries.get(0));

        for (int i = 0; i < userLibraries.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            Library thisLibrary = userLibraries.get(i);
            consoleOutput.append(thisLibrary.getName());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        try {
            String userInput = consoleReader.readLine();
            int userSelection = Integer.parseInt(userInput);
            activeLibrary = userLibraries.get(userSelection - 1);
        } catch (Exception e) {
            System.out.println("~~~~ ERROR ~~~~ Trouble with reading console input");
            e.printStackTrace();
        }

        return activeLibrary;
    }
}

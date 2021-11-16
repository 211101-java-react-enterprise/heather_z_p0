package com.revature.MYbrary.services;

import com.revature.MYbrary.util.List;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.exceptions.ResourcePersistenceException;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;

public class LibraryService {

    private final LibraryDAO libraryDAO;
    private Library activeLibrary = null;

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

    public Library getLibrary() {
        return activeLibrary;
    }
    public List<Library> getUserLibraries() {
        List<Library> userLibraries = libraryDAO.findAll();

        if (userLibraries != null) {
            return userLibraries;
        } else {
            return null;
        }

    }
}

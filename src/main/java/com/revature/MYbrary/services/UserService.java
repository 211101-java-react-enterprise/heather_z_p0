/*
* Allows for a persistent user experience as the app navigates between Screens
* */

package com.revature.MYbrary.services;

import com.revature.MYbrary.daos.AnnotationDAO;
import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.exceptions.AuthenticationException;
import com.revature.MYbrary.exceptions.InvalidRequestException;
import com.revature.MYbrary.exceptions.ResourcePersistenceException;
import com.revature.MYbrary.models.Annotation;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.AppState;

public class UserService {

    private final AppUserDAO userDAO;
    private final LibraryDAO libraryDAO;
    private final BookDAO bookDAO;
    private final AnnotationDAO annotationDAO;
    private AppUser sessionUser;
    private Library sessionLibrary;
    private Book sessionBook;
    private Annotation sessionAnnotation;

    // Constructor
    public UserService(AppUserDAO userDAO, LibraryDAO libraryDAO, BookDAO bookDAO, AnnotationDAO annotationDAO) {
        this.userDAO = userDAO;
        this.libraryDAO = libraryDAO;
        this.bookDAO = bookDAO;
        this.annotationDAO = annotationDAO;
        this.sessionUser = null;
        this.sessionLibrary = null;
        this.sessionBook = null;
        this.sessionAnnotation = null;
    }

    public boolean registerNewUser(AppUser newUser, Library newLibrary) {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        // Check user input versus the database to see if they've duplicated anything in fields we've defined as unique
        boolean usernameAvailable = (userDAO.findUserByUsername(newUser.getUsername()) == null);
        boolean emailAvailable = (userDAO.findUserByEmail(newUser.getEmail()) == null);

        // Provide detailed information to the user
        if (!usernameAvailable || !emailAvailable) {
            //?? Aren't Strings immutable? How does this work ??//
            String msg = "The values provided for the following fields are already taken by other users:";
            if (!usernameAvailable) msg = msg + "\n\t- username";
            if (!emailAvailable) msg = msg + "\n\t- email";
            throw new ResourcePersistenceException(msg);
        }

        AppUser registeredUser = userDAO.save(newUser); // Attempt to save the input data to the database
        newLibrary.setUserId(registeredUser.getId());
        if (newLibrary.getName().equals("") | newLibrary.getName().equals(null)) { // Provide default name when upon blank input
            newLibrary.setName(registeredUser.getPersonalName() + "'s Library");
        }

        Library initialLibrary = libraryDAO.save(newLibrary);

        if (registeredUser == null) { // If registeredUser is still empty after the last line, something prevented userDAO from returning an AppUser
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }
        if (initialLibrary == null) {
            throw new ResourcePersistenceException("The library could not be persisted to the datasource!");
        }
        return true;

    }

    public void authenticateUser(String username, String password) {

        // Sanitizing inputs
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        // Authentication is successful if the input username and password values are both true on a single record.
        AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);

        if (authenticatedUser == null) { // If authenticatedUser has a value, the above query was successful.
            throw new AuthenticationException();
        } else {
            sessionUser = authenticatedUser;
            sessionLibrary = libraryDAO.getDefaultLibrary(authenticatedUser.getId());
        }

    }

    public void logout() {
        sessionUser = null;
        sessionLibrary = null;
        sessionBook = null;
        sessionAnnotation = null;
        AppState.shutdown();
    }

    public boolean isSessionActive() {
        return sessionUser != null;
    }

    // Checks all the columns we've specified as NOT NULL in SQL
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getPersonalName() == null || user.getPersonalName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    // Getters/Setters
    public AppUser getSessionUser() {
        return sessionUser;
    }

    public Library getDefaultLibrary() {
        return libraryDAO.getDefaultLibrary(sessionUser.getId());
    }

    public Library getDefaultLibrary(String id) {
        return libraryDAO.getDefaultLibrary(id);
    }

    public Library getSessionLibrary() {
        return sessionLibrary;
    }

    public void setSessionLibrary(Integer libraryId) {
        this.sessionLibrary = libraryDAO.findById(libraryId);
    }

    public Book getSessionBook() {
        return sessionBook;
    }

    public void setSessionBook(Integer bookId) {
        this.sessionBook = bookDAO.findById(bookId);
    }

    public Annotation getSessionAnnotation() {
        return sessionAnnotation;
    }

    public void setSessionAnnotation(Integer annotationId) {
        this.sessionAnnotation = annotationDAO.findById(annotationId);
    }
}

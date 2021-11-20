package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LoanScreen extends Screen {

    private final UserService userService;
    public LoanScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoanScreen", "/loan", consoleReader, router);
        this.userService = userService;
    }
    private AppUserDAO userDAO = new AppUserDAO();
    private LibraryDAO libraryDAO = new LibraryDAO();
    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {

        LinkedList<AppUser> users = userDAO.findAll();
        Book thisBook = userService.getSessionBook();

        StringBuilder consoleOutput = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            AppUser thisUser = users.get(i);
            consoleOutput.append(thisUser.getUsername());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        String userInput = consoleReader.readLine();

        AppUser userSelection = users.get(Integer.parseInt(userInput) - 1);
        // Then we set the library_id field of the active book to the new value.
        // Do some kind of confirmation first...
        Library selectedUserDefaultLibrary = libraryDAO.getDefaultLibrary(userSelection.getId());
        System.out.println("~~~~~~~~ FLAG - LoanScreen L.46 ~~~~~~~~\n" + selectedUserDefaultLibrary.getName());
        Book newBook = new Book(thisBook.getTitle(), thisBook.getAuthor(), thisBook.getPageCount(), 0, selectedUserDefaultLibrary.getId());
        bookDAO.save(newBook);
        System.out.println("Ok, your book has been lent to " + userSelection.getUsername());
        router.navigate("/dashboard");
    }
}

package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLException;

public class BookPageUpdate extends Screen {
    private final UserService userService;
    public BookPageUpdate(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookPageUpdate", "/update-page", consoleReader, router);
        this.userService = userService;
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        Book activeBook = userService.getSessionBook();
        System.out.print("NEW CURRENT PAGE: ");
        String userInput = consoleReader.readLine();
        try {
            Integer newPage = Integer.parseInt(userInput);
            bookDAO.updateCurrentPage(activeBook, newPage);
            userService.setSessionBook(activeBook.getId());
            router.navigate("/book");
        } catch (SQLException e) {
            // This gives an error no matter what???
            // e.printStackTrace();
        }
    }
}

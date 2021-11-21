package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLException;

public class BookPageUpdate extends Screen {

    private final UserService userService;
    private final Logger logger;

    public BookPageUpdate(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookPageUpdate", "/update-page", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        Book activeBook = userService.getSessionBook();
        System.out.print("NEW CURRENT PAGE: ");
        Integer userInput = Integer.parseInt(consoleReader.readLine());

        if (userInput > activeBook.getPageCount() | userInput < 0) {
            System.out.println("Page selection out of range");
            router.navigate("/update-page");
        }

        try {
            Integer newPage = userInput;
            logger.log("Book ID " + activeBook.getId() + " current page updated from " + activeBook.getCurrentPage() + " to " + newPage);
            bookDAO.updateCurrentPage(activeBook, newPage);
            userService.setSessionBook(activeBook.getId()); // Refresh the userService's copy of the book record
            router.navigate("/book");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

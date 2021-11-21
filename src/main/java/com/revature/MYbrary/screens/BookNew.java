package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class BookNew extends Screen {
    private UserService userService;
    private Logger logger;
    public BookNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookNew", "/new-book", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        Library library = userService.getSessionLibrary();

        System.out.println("Please enter details for your new book.");

        System.out.print("TITLE: ");
        String title = consoleReader.readLine();
        System.out.print("AUTHOR: ");
        String author = consoleReader.readLine();
        System.out.print("PAGE COUNT: ");
        Integer pageCount = Integer.parseInt(consoleReader.readLine());

        Book newBook = new Book(title, author, pageCount, library.getId());

        try {
            Book createdBook = bookDAO.save(newBook);
            userService.setSessionBook(createdBook.getId());
            logger.log(createdBook.getTitle() + " has been created for " + userService.getSessionUser().getUsername() + " with ID " + createdBook.getId());
            router.navigate("/book");
        } catch (NumberFormatException e) {
            System.out.println("pageCount could not be converted to an Integer. Check your inputs and try again. ");
            router.navigate("/dashboard");
        }
    }
}

package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class BookSelect extends Screen {
    private final UserService userService;
    public BookSelect(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("BookSelect", "/select-book", consoleReader, router);
        this.userService = userService;
    }
    private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        AppUser user = userService.getSessionUser();
        Library activeLibrary = userService.getSessionLibrary();
        LinkedList<Book> books = bookDAO.findAll(activeLibrary.getId());
        if (books.size() == 0) {
            System.out.println("No books in current library! Here, let's fix that...");
            router.navigate("/new-book");
        }

        System.out.printf("==== All Books in %s ====\n", activeLibrary.getName());

        StringBuilder consoleOutput = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            Book thisBook = books.get(i);
            consoleOutput.append(thisBook.getTitle());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        String userInput = consoleReader.readLine();
        try {
            Book userSelection = books.get(Integer.parseInt(userInput) - 1);
            userService.setSessionBook(userSelection.getId());
            router.navigate("/book");
        } catch (Exception e) {
            System.out.println("~~~~ ERROR ~~~~ Trouble with reading console input");
            e.printStackTrace();
        }

    }
}

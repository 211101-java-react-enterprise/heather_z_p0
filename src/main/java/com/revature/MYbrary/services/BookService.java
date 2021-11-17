package com.revature.MYbrary.services;

import com.revature.MYbrary.exceptions.ResourcePersistenceException;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;

import java.io.BufferedReader;

public class BookService {
    private final BookDAO bookDAO;
    private Book activeBook = null;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public boolean createNewBook (Book newBook) {
        Book createdBook = bookDAO.save(newBook);
        if (createdBook != null) {
            return true;
        } else {
            throw new ResourcePersistenceException("The book could not be persisted to the datasource!");
        }
    }

    public LinkedList<Book> getLibraryBooks(Integer libraryId) {
        LinkedList<Book> libraryBooks = bookDAO.findAll(libraryId);

        if(libraryBooks != null) {
            return libraryBooks;
        } else {
            return null;
        }
    }

    public Book promptBookSelection(Integer libraryId, BufferedReader consoleReader) {
        Book activeBook = null;
        StringBuilder consoleOutput = new StringBuilder();
        LinkedList<Book> libraryBooks = getLibraryBooks(libraryId);

        for (int i = 0; i < libraryBooks.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            Book thisBook = libraryBooks.get(i);
            consoleOutput.append(thisBook.getTitle());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        try {
            String userInput = consoleReader.readLine();
            int userSelection = Integer.parseInt(userInput);
            activeBook = libraryBooks.get(userSelection - 1);
        } catch (Exception e) {
            System.out.println("~~~~ ERROR ~~~~ Trouble with reading console input");
            e.printStackTrace();
        }

        return activeBook;
    }

    public boolean updateCurrentPage(Book book, Integer newCurrentPage) {
        return bookDAO.updateCurrentPage(book, newCurrentPage);
    }
}

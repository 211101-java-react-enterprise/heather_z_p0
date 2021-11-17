package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO implements CrudDAO<Book> {

    @Override
    public Book save(Book newBook) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into books (title, author, page_count, library_id) values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newBook.getTitle());
            statement.setString(2, newBook.getAuthor());
            statement.setInt(3, newBook.getPageCount());;
            statement.setInt(4, newBook.getLibraryId());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted != 0) {
                return newBook;
            }
        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List findAll() {
        LinkedList<Book> libraries = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from books";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setLibraryId(rs.getInt("library_id"));

                libraries.add(book);
            }
            return libraries;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<Book> findAll(Integer libraryId) {
        LinkedList<Book> libraries = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from books where library_id = '" + libraryId + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();

                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setLibraryId(rs.getInt("library_id"));

                libraries.add(book);
            }
            return libraries;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book findById(String id) {
        return findById(Integer.parseInt(id));
    }

    public Book findById(Integer id) {
        return null;
    }

    @Override
    public boolean removeById(String id) {
        return removeById(Integer.parseInt(id));
    }

    public boolean removeById(Integer id) {
        return false;
    }

    @Override
    public boolean update(Book updatedObj) {
        return false;
    }


}

package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;

import java.sql.*;

public class BookDAO implements CrudDAO<Book> {

    @Override
    public Book save(Book newBook) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into books (title, author, page_count, current_page, library_id) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newBook.getTitle());
            statement.setString(2, newBook.getAuthor());
            statement.setInt(3, newBook.getPageCount());;
            statement.setInt(4, newBook.getCurrentPage());
            statement.setInt(5, newBook.getLibraryId());

            int rowsInserted = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                newBook.setId(rs.getInt(1));
            }
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
            String query = "select * from books;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setCurrentPage(rs.getInt("current_page"));
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
        LinkedList<Book> books = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from books where library_id = '" + libraryId + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setCurrentPage(rs.getInt("current_page"));
                book.setLibraryId(rs.getInt("library_id"));

                books.add(book);
            }
            return books;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return books;
        }
    }

    @Override
    public Book findById(String id) {
        return findById(Integer.parseInt(id));
    }

    public Book findById(Integer id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from books where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setCurrentPage(rs.getInt("current_page"));
                book.setLibraryId(rs.getInt("library_id"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public boolean update(Book book) { return false; }

    public boolean updateCurrentPage(Book book, Integer newCurrentPage) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            /*
            UPDATE Customers
            SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
            WHERE CustomerID = 1;
             */
            String query = "update books " +
                    "set current_page = " + newCurrentPage +
                    "where book_id = " + book.getId() + ";";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            return true;

        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return false;
        }

    }


}

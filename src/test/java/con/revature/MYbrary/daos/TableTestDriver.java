package con.revature.MYbrary.daos;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import org.junit.Test;

public class TableTestDriver {

    @Test
    public void createTables() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            LinkedList<String> queries = new LinkedList<>();
            // APP_USERS
            queries.add("create table users (" +
                    "id serial primary key, " +
                    "personal_name varchar, " +
                    "email varchar unique not null, " +
                    "username varchar unique not null, " +
                    "password varchar not null, " +
                    "date_joined datetime); ");
            // LIBRARIES
            queries.add("create table libraries (" +
                    "id serial primary key, " +
                    "name varchar not null, " + // Make a default value here
                    "user_id int, " +
                    // constraints
                    "constraint library_user_fk " +
                    "foreign key (user_id) " +
                    "references users); ");
            // BOOKS
            queries.add("create table books (" +
                    "id serial primary key, " +
                    "title varchar not null, " +
                    "author varchar not null, " +
                    "page_count int not null, " +
                    "genre varchar, " +
                    "edition varchar, " +
                    "publisher varchar, " +
                    "publication_city varchar, " +
                    "publication_year int, " +
                    "library_id int, " +
                    // constraints
                    "constraint book_library_fk " +
                    "foreign key (library_id) " +
                    "references libraries); ");
            // QUOTES
            queries.add("create table quotes (" +
                    "id serial primary key, " +
                    "starting_words varchar(64) not null, " +
                    "starting_page int not null, " +
                    "ending_words varchar(64) not null, " +
                    "ending_page int not null, " +
                    "notes varchar(8192), " +
                    "book_id int, " +
                    // constraints
                    "constraint quote_book_fk " +
                    "foreign key (book_id) " +
                    "references books); ");
            System.out.println(queries.toString());
            PreparedStatement statement = connection.prepareStatement(queries.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void populateTables() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            LinkedList<String> queries = new LinkedList<>();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

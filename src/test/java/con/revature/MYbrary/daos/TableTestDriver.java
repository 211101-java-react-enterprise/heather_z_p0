package con.revature.MYbrary.daos;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.LibraryService;
import org.junit.Assert;
import org.junit.Test;

import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.models.AppUser;

public class TableTestDriver {
    AppUserDAO userDAO = new AppUserDAO();
    LibraryDAO libraryDAO = new LibraryDAO();
    UserService userService = new UserService(userDAO);
    LibraryService libraryService = new LibraryService(libraryDAO);


    @Test
    public void createTables() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            LinkedList<String> queries = new LinkedList<>();
            queries.add("");
            // APP_USERS
            queries.add("create table app_users (" +
                    "id varchar primary key, " +
                    "personal_name varchar, " +
                    "email varchar unique not null, " +
                    "username varchar unique not null, " +
                    "password varchar not null, " +
                    "date_joined date); ");
            // LIBRARIES
            queries.add("create table libraries (" +
                    "id serial primary key, " +
                    "name varchar not null, " + // Make a default value here
                    "user_id varchar, " +
                    // constraints
                    "constraint library_user_fk " +
                    "foreign key (user_id) " +
                    "references app_users); ");
            // BOOKS
            queries.add("create table books (" +
                    "id serial primary key, " +
                    "title varchar not null, " +
                    "author varchar not null, " +
                    "page_count int not null, " +
                    "library_id int not null, " +
                    // constraints
                    "constraint book_library_fk " +
                    "foreign key (library_id) " +
                    "references libraries); ");
            // ANNOTATIONS
            queries.add("create table annotations (" +
                    "id serial primary key, " +
                    "starting_words varchar(64) not null, " +
                    "starting_page int not null, " +
                    "ending_words varchar(64) not null, " +
                    "ending_page int not null, " +
                    "notes varchar(8192), " +
                    "book_id int, " +
                    // constraints
                    "constraint annotation_book_fk " +
                    "foreign key (book_id) " +
                    "references books); ");
            System.out.println(queries.toStringRaw());
            PreparedStatement statement = connection.prepareStatement(queries.toStringRaw());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void populateAppUsers() {
        boolean user1 = userService.registerNewUser(new AppUser("Eagle Eyes Jamison","james@eagleeyes.biz","SeaEwe","oicu812"));
        boolean user2 = userService.registerNewUser(new AppUser("Grimms Gramms","grandgramm@altavista.com","bred-crumz","1M$0C|3^3R"));
        boolean user3 = userService.registerNewUser(new AppUser("Crash","outerlimits95@hotmail.com","crashOverride","~Secure~~And~~Unique~"));

        Assert.assertTrue(user1 && user2 && user3);
    }

    @Test
    public void populateLibraries() {
        boolean library1 = libraryService.createNewLibrary(new Library("My First Library","5b4ed11a-760f-4c16-9389-000b930a1614"));

        Assert.assertTrue(library1);
    }
}

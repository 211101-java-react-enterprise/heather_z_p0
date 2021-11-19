package con.revature.MYbrary.daos;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.LibraryService;
import org.junit.Assert;
import org.junit.Test;

import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.models.AppUser;

public class LibraryServiceTester {
    AppUserDAO userDAO = new AppUserDAO();
    LibraryDAO libraryDAO = new LibraryDAO();
    BookDAO bookDAO = new BookDAO();
    UserService userService = new UserService(userDAO, libraryDAO, bookDAO);
    LibraryService libraryService = new LibraryService(libraryDAO);

    @Test
    public void getUserLibraries() {
        String userId = "5b4ed11a-760f-4c16-9389-000b930a1614";

        LinkedList<Library> libraries = libraryService.getUserLibraries(userId);
        String libraryName = libraries.get(0).getName();

        System.out.println(libraryName);
    }

}

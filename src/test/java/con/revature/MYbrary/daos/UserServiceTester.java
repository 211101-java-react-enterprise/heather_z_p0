package con.revature.MYbrary.daos;

import com.revature.MYbrary.daos.AnnotationDAO;
import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class UserServiceTester {

    UserService sut;
    AppUserDAO mockDA0;
    LibraryDAO mockLibrary;
    BookDAO mockBook;
    AnnotationDAO mockAnnotation;

    @Before
    public void testCaseSetup(){
        mockDA0 = mock(AppUserDAO.class);
        sut = new UserService(mockDA0, mockLibrary, mockBook, mockAnnotation);
    }

    @Test
    public void test_isUserValid_returnsTrue_givenValidUser(){
        AppUser testUser = new AppUser("valid","valid","valid","valid");
        boolean testResult = sut.isUserValid(testUser);
        Assert.assertTrue("Expected user to be valid", testResult);
    }

    @Test
    public void test_isUserValid_returnsFalse_givenInvalidUsername(){
        AppUser testUser = new AppUser("valid","valid",null,"valid");
        AppUser testUser2 = new AppUser("valid","valid","","valid");
        AppUser testUser3 = new AppUser("valid","valid","        ","valid");

        boolean testResult = sut.isUserValid(testUser);
        boolean testResult2 = sut.isUserValid(testUser2);
        boolean testResult3 = sut.isUserValid(testUser3);

        Assert.assertFalse("Expected user to be invalid", testResult);
        Assert.assertFalse("Expected user to be invalid", testResult2);
        Assert.assertFalse("Expected user to be invalid", testResult3);
    }

    @Test
    public void test_isUserValid_returnsFalse_givenInvalidPassword(){
        AppUser testUser = new AppUser("valid","valid","valid",null);
        AppUser testUser2 = new AppUser("valid","valid","valid","");
        AppUser testUser3 = new AppUser("valid","valid","valid","        ");

        boolean testResult = sut.isUserValid(testUser);
        boolean testResult2 = sut.isUserValid(testUser2);
        boolean testResult3 = sut.isUserValid(testUser3);

        Assert.assertFalse("Expected user to be invalid", testResult);
        Assert.assertFalse("Expected user to be invalid", testResult2);
        Assert.assertFalse("Expected user to be invalid", testResult3);
    }

    @Ignore
    @Test
    public void test_registerNewUser_returnsTrue(){

        AppUser test = new AppUser("Tim Testerino","test@example.com","TINYtim","NeverYouMind");
    }
}

package con.revature.MYbrary.daos;

import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class UserServiceTester {

    UserService sut;
    AppUserDAO mockDA0;

    @Before
    public void testCaseSetup(){
        mockDA0 = mock(AppUserDAO.class);
        sut = new UserService(mockDA0);
    }

    @Test
    public void test_registerNewUser_returnsTrue(){
        AppUser test = new AppUser("Tim Testerino","test@example.com","TINYtim","NeverYouMind");
    }
}

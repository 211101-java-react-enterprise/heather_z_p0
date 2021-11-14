package con.revature.MYbrary.util;

import com.revature.MYbrary.util.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTestSuite {

    @Test
    public void test_getConnection_returnsValidConnection_givenProvidedCredentials() {
        try(Connection connection = ConnectionFactory.getInstance().getConnection()) {
            Assert.assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

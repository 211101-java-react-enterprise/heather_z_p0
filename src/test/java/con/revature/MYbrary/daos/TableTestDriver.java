package con.revature.MYbrary.daos;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.revature.MYbrary.util.ConnectionFactory;
import org.junit.Test;

public class TableTestDriver {

    @Test
    public void createTables() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "create table AppUsers (id int not null primary key, personalName varchar, email varchar unique not null, username varchar unique not null, password varchar not null);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

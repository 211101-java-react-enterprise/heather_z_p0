package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.List;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AppUserDAO implements CrudDAO<AppUser> {
    /* TODO
    *   - findById
    *   - findByUsername
    * */

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser findById(String id) {
        return null;
    }

    @Override
    public AppUser save(AppUser newUser) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

            newUser.setId(UUID.randomUUID().toString());

            String sql = "insert into app_users (id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newUser.getId());
            statement.setString(2, newUser.getPersonalName());
            statement.setString(4, newUser.getEmail());
            statement.setString(5, newUser.getUsername());
            statement.setString(6, newUser.getPassword());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted != 0) {
                return newUser;
            }

        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public boolean update(AppUser updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

    public AppUser findUserByUsername(String username) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("userId"));
                user.setPersonalName(rs.getString("personalName"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public AppUser findByEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where email = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("user_id"));
                user.setPersonalName(rs.getString("personalName"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public AppUser findByUsernameAndPassword(String username, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where username = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("user_id"));
                user.setPersonalName(rs.getString("personalName"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


    public AppUser findForLogin(String username, String password) {
        File file = new File("resources/data.txt");
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file));) {
            String line = fileReader.readLine();
            System.out.println(line);
            while(line != null){
                String[] lineBits = line.split(":");
                String lineUsername = lineBits[4];
                String linePassword = lineBits[5];

                if (username.equals(lineUsername) && password.equals(linePassword)) {
                    AppUser foundUser = new AppUser(lineBits[1], lineBits[2], lineBits[3], lineBits[4], lineBits[5]);
                    foundUser.setId(lineBits[0]);
                    return foundUser;
                } else {
                    line = fileReader.readLine();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

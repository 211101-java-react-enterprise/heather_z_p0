package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AppUserDAO implements CrudDAO<AppUser> {

    @Override
    public AppUser save(AppUser newUser) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

            newUser.setId(UUID.randomUUID().toString());

            String sql = "insert into app_users (id, personal_name, email, username, password) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newUser.getId());
            statement.setString(2, newUser.getPersonalName());
            statement.setString(3, newUser.getEmail());
            statement.setString(4, newUser.getUsername());
            statement.setString(5, newUser.getPassword());

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

    public AppUser findUserByUsername(String username) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("user_id"));
                user.setPersonalName(rs.getString("personal_name"));
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

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("user_id"));
                user.setPersonalName(rs.getString("personal_name"));
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

    public AppUser findUserByEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from app_users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("user_id"));
                user.setPersonalName(rs.getString("personal_name"));
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

    @Override
    public List<AppUser> findAll() {
        LinkedList<AppUser> users = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from app_users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getString("id"));
                user.setPersonalName(rs.getString("personal_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public AppUser findById(String id) {
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

}

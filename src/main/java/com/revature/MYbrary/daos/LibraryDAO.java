package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;

import java.sql.*;

public class LibraryDAO implements CrudDAO<Library> {

    @Override
    public Library save(Library newLibrary) {
        // System.out.println("~~~~~~~~ FLAG - LibraryDAO L.18 ~~~~~~~~\n" + newLibrary.getName());
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into libraries (name, user_id) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newLibrary.getName());
            statement.setString(2, newLibrary.getUserId());

            int rowsInserted = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                newLibrary.setId(rs.getInt(1));
            }

            if (rowsInserted != 0) {
                return newLibrary;
            }

        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public LinkedList<Library> findAll() {
        LinkedList<Library> libraries = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from libraries";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Library library = new Library();
                library.setName(rs.getString("name"));
                library.setUserId(rs.getString("user_id"));
                libraries.add(library);
            }
            return libraries;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<Library> findAll(String userId) {
        //System.out.println("~~~~ FLAG - LibraryDAO - L63 ~~~~\n" + userId);
        LinkedList<Library> libraries = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from libraries where user_id = '" + userId + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Library library = new Library();

                library.setId(rs.getInt("id"));
                library.setName(rs.getString("name"));
                library.setUserId(rs.getString("user_id"));

                libraries.add(library);
            }
            return libraries;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    public Library getDefaultLibrary(String userId) {
        LinkedList<Library> libraries = findAll(userId);

        if (libraries != null) return libraries.get(0);
        return null;

    }

    @Override
    public Library findById(String id) {
        return findById(Integer.parseInt(id));
    }

    public Library findById(Integer id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from libraries where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Library library = new Library();
                library.setId(rs.getInt("id"));
                library.setName(rs.getString("name"));
                library.setUserId(rs.getString("user_id"));
                return library;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean update(Library updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}

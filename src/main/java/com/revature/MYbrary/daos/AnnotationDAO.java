package com.revature.MYbrary.daos;

import com.revature.MYbrary.models.Annotation;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.util.ConnectionFactory;
import com.revature.MYbrary.util.LinkedList;

import java.sql.*;

public class AnnotationDAO implements CrudDAO<Annotation> {

    @Override
    public Annotation save(Annotation newAnnotation) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into annotations (starting_words, starting_page, ending_words, ending_page, notes, book_id) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newAnnotation.getStartingWords());
            statement.setInt(2, newAnnotation.getStartingPage());
            statement.setString(3, newAnnotation.getEndingWords());
            statement.setInt(4, newAnnotation.getEndingPage());
            statement.setString(5, newAnnotation.getNotes());
            statement.setInt(6, newAnnotation.getBookId());

            int rowsInserted = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                newAnnotation.setId(rs.getInt(1));
                return newAnnotation;
            }

        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public LinkedList<Annotation> findAll() {
        LinkedList<Annotation> annotations = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from libraries";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Annotation annotation = new Annotation();
                annotation.setId(rs.getInt("id"));
                statement.setString(1, annotation.getStartingWords());
                statement.setInt(2, annotation.getStartingPage());
                statement.setString(3, annotation.getEndingWords());
                statement.setInt(4, annotation.getEndingPage());
                statement.setString(5, annotation.getEndingWords());
                statement.setInt(6, annotation.getBookId());
                annotations.add(annotation);
            }
            return annotations;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<Annotation> findAll(Integer bookId) {
        LinkedList<Annotation> annotations = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = "select * from annotations where book_id = '" + bookId + "';";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Annotation annotation = new Annotation();
                annotation.setId(rs.getInt("id"));
                annotation.setStartingWords(rs.getString("starting_words"));
                annotation.setStartingPage(rs.getInt("starting_page"));
                annotation.setEndingWords(rs.getString("ending_words"));
                annotation.setEndingPage(rs.getInt("ending_page"));
                annotation.setNotes(rs.getString("notes"));
                annotation.setBookId(rs.getInt("book_id"));
                annotations.add(annotation);
            }
            return annotations;
        }  catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Annotation findById(String id) {
        return findById(Integer.parseInt(id));
    }

    public Annotation findById(Integer id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from annotations where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Annotation annotation = new Annotation();
                annotation.setId(rs.getInt("id"));
                annotation.setStartingWords(rs.getString("starting_words"));
                annotation.setStartingPage(rs.getInt("starting_page"));
                annotation.setEndingWords(rs.getString("ending_words"));
                annotation.setEndingPage(rs.getInt("ending_page"));
                annotation.setNotes(rs.getString("notes"));
                annotation.setBookId(rs.getInt("book_id"));
                return annotation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Annotation annotation) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}

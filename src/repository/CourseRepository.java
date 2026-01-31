package repository;

import exception.DatabaseOperationException;
import model.*;
import repository.interfaces.ICrudRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICrudRepository<Course> {

    @Override
    public void create(Course course) throws DatabaseOperationException {
        String sql = "INSERT INTO courses (name, credits, instructor_id, course_type, platform_url, room_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParams(stmt, course);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    @Override
    public List<Course> getAll() throws DatabaseOperationException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.*, i.name as i_name, i.email as i_email FROM courses c JOIN instructors i ON c.instructor_id = i.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
        return list;
    }

    @Override
    public Course getById(int id) throws DatabaseOperationException {
        String sql = "SELECT c.*, i.name as i_name, i.email as i_email FROM courses c JOIN instructors i ON c.instructor_id = i.id WHERE c.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(int id, Course course) throws DatabaseOperationException {
        String sql = "UPDATE courses SET name=?, credits=?, instructor_id=?, course_type=?, platform_url=?, room_number=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParams(stmt, course);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) throws DatabaseOperationException {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    private void setParams(PreparedStatement stmt, Course course) throws SQLException {
        stmt.setString(1, course.getName());
        stmt.setInt(2, course.getCreditValue());
        stmt.setInt(3, course.getInstructor().getId());
        if (course instanceof OnlineCourse) {
            stmt.setString(4, "ONLINE");
            stmt.setString(5, ((OnlineCourse) course).getPlatformUrl());
            stmt.setNull(6, Types.VARCHAR);
        } else if (course instanceof OnCampusCourse) {
            stmt.setString(4, "ON_CAMPUS");
            stmt.setNull(5, Types.VARCHAR);
            stmt.setString(6, ((OnCampusCourse) course).getRoomNumber());
        }
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        Instructor instr = new Instructor(rs.getInt("instructor_id"), rs.getString("i_name"), rs.getString("i_email"));
        String type = rs.getString("course_type");
        if ("ONLINE".equals(type)) {
            return new OnlineCourse(rs.getInt("id"), rs.getString("name"), rs.getInt("credits"), instr, rs.getString("platform_url"));
        } else {
            return new OnCampusCourse(rs.getInt("id"), rs.getString("name"), rs.getInt("credits"), instr, rs.getString("room_number"));
        }
    }
}
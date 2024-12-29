package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.PostDAO;
import model.entities.Post;
import model.ModelException;

public class PostDAOImpl implements PostDAO {

    private Connection conn;

    public PostDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Post post) throws ModelException {
        String sql = "INSERT INTO posts (content, date) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getContent());
            stmt.setDate(2, new java.sql.Date(post.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ModelException("Error saving post: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Post post) throws ModelException {
        String sql = "UPDATE posts SET content = ?, date = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getContent());
            stmt.setDate(2, new java.sql.Date(post.getDate().getTime()));
            stmt.setInt(3, post.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ModelException("Error updating post: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws ModelException {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ModelException("Error deleting post: " + e.getMessage(), e);
        }
    }

    @Override
    public Post findById(int id) throws ModelException {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Post post = new Post(rs.getInt("id"));
                    post.setContent(rs.getString("content"));
                    post.setDate(rs.getDate("date"));
                    return post;
                }
            }
        } catch (SQLException e) {
            throw new ModelException("Error finding post by ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Post> listAll() throws ModelException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Post post = new Post(rs.getInt("id"));
                post.setContent(rs.getString("content"));
                post.setDate(rs.getDate("date"));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new ModelException("Error listing posts: " + e.getMessage(), e);
        }
        return posts;
    }
    
}

package model.dao;

import java.util.List;
import model.entities.Post;
import model.ModelException;

public interface PostDAO {
    
    void save(Post post) throws ModelException;

    void update(Post post) throws ModelException;

    void delete(int id) throws ModelException;

    Post findById(int id) throws ModelException;

    List<Post> listAll() throws ModelException;
}

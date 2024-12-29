package model.dao;

import java.util.List;

import model.ModelException;
import model.entities.User;

public interface UserDAO {
	boolean save(User user) throws ModelException; //criação
	List<User> listAll() throws ModelException; //leitura
	boolean update(User user) throws ModelException; //update
	boolean delete(User user) throws ModelException; //delete
	User findByID(int id) throws ModelException; 
}

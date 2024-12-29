package model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.dao.impl.PostDAOImpl;

public class DAOFactory {
    
    private static Map<Class<?>, Object> daos = new HashMap<>(); 
    
    static {
        daos.put(UserDAO.class, new MySQLUserDAO());
        try {
			daos.put(PostDAO.class, new PostDAOImpl(MySQLConnectionFactory.getConnection()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static Object getDAO(Class<?> cls) {
        return daos.get(cls);
    }
}
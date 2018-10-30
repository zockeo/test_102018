package src.util;

import src.dao.IRoleDao;
import src.dao.IUserDAO;
import src.impl.RoleDaoImpl;
import src.impl.UserDAOImpl;

/**
 * Created by z on 2018/9/20.
 */
public class DaoFactory {
    public static IUserDAO getUserDao() {
        return new UserDAOImpl();
    }

    public static IRoleDao getRoleDao() {
        return new RoleDaoImpl();
    }
}

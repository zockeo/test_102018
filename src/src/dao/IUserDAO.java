package src.dao;

import src.entity.Page;
import src.entity.User;

import java.util.List;

/**
 * Created by z on 2018/9/15.
 */
public interface IUserDAO {
    public boolean check(User user) throws Exception;

    public List<User> getByPage(Page pages) throws Exception;

    public void delete(int id) throws Exception;
}

package src.dao;

import src.entity.Role;

import java.util.List;

/**
 * Created by z on 2018/10/28.
 */
public interface IRoleDao {
    public List<Role> getRoles() throws Exception;
}

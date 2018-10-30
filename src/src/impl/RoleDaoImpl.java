package src.impl;

import src.dao.IRoleDao;
import src.entity.Role;
import src.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by z on 2018/10/28.
 */
public class RoleDaoImpl implements IRoleDao {
    @Override
    public List<Role> getRoles() throws Exception {
        List<Role> res = new ArrayList<>();
        Connection conn= null;
        try{
            conn = ConnectionManager.getConn();
            PreparedStatement ps = conn.prepareStatement("select id,name from role");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                res.add(role);
            }
            ps.close();
            rs.close();
        }finally {
            ConnectionManager.closeConn(conn);
        }
        return res;
    }
}

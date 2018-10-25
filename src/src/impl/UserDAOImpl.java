package src.impl;

import org.apache.commons.beanutils.BeanUtils;
import src.dao.IUserDAO;
import src.entity.Page;
import src.entity.User;
import src.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by z on 2018/9/15.
 */
public class UserDAOImpl implements IUserDAO {

    @Override
    public boolean check(User user) throws Exception{
        boolean result = false;
        Connection conn = null;
        try{
            conn = ConnectionManager.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from user left join role on user.role_id = role.id where user.username = ? and user.password = ?");
//            PreparedStatement ps = conn.prepareStatement("select user.id,user.username,role.name from user left join role on
//                      user.role_id = role.id where user.username = ? and user.password = ?");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User temp = this.getBean(rs);
                BeanUtils.copyProperties(user,temp);
                result = true;
            }
            rs.close();
            ps.close();
        }finally {
            ConnectionManager.closeConn(conn);
        }
        return result;
    }

    @Override
    public List<User> getByPage(Page pages) throws Exception {
        List<User> res = new ArrayList<>();
        Connection conn = null;
        try{
            conn = ConnectionManager.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from user left join role on user.role_id = role.id order by user.id asc",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            PreparedStatement ps = conn.prepareStatement("select user.id,user.username,role.name,role.description from
//                      user left join role on user.role_id = role.id");
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                return res;
            }
            if(pages.getPageNum()<1){
                pages.setPageNum(1);
            }
            if(pages.getMaxPage()<1){
                rs.last();
                int rowsNum = rs.getRow();
                if(rowsNum<1){
                    return res;
                }
                int maxPage = rowsNum/pages.getRowsPerPage();
                if(rowsNum%pages.getRowsPerPage() != 0) {
                    maxPage++;
                }
                pages.setMaxPage(maxPage);
                pages.setRowsNum(rowsNum);
            }
            if(pages.getPageNum() > pages.getMaxPage()) {
                pages.setPageNum(pages.getMaxPage());
            }
            int begin = (pages.getPageNum()-1) * pages.getRowsPerPage() + 1 ;
            rs.absolute(begin);
            int end = 0 ;
            do{
                if(++end > pages.getRowsPerPage()){
                    break;
                }
                User temp = this.getBean(rs);
                res.add(temp);
            } while (rs.next());
            rs.close();
            ps.close();
        }finally {
            ConnectionManager.closeConn(conn);
        }
        return res;
    }

    @Override
    public void delete(int id) throws Exception {
        Connection conn= null ;
        try{
            conn = ConnectionManager.getConn();
            PreparedStatement ps = conn.prepareStatement("delete from user where id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        }finally {
            ConnectionManager.closeConn(conn);
        }
    }

    private User getBean(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.getRole().setId(rs.getInt("id"));
        user.getRole().setName(rs.getString("name"));
        user.getRole().setDesc(rs.getString("description"));
        return user;
    }

}

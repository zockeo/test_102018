package src.util;

import src.dao.IRoleDao;
import src.entity.Role;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by z on 2018/10/28.
 */
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        IRoleDao roleDao = DaoFactory.getRoleDao();
        try{
            List<Role> rList = roleDao.getRoles();
            application.setAttribute("roleList",rList);
        }catch (Exception e) {
            application.log("AppListener", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        application.removeAttribute("roleList");
    }
}

package servlet;

import src.dao.IRoleDao;
import src.entity.Role;
import src.util.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by z on 2018/10/24.
 */
@WebServlet(name = "register")
public class register extends HttpServlet {
    private IRoleDao roleDao = DaoFactory.getRoleDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Object oj = this.getServletContext().getAttribute("roleList");
//        if(oj == null && ((List)oj).size()<1){
//            try {
//                synchronized (this.getServletContext()){
//                    if(oj == null && ((List)oj).size()<1){
//                        List<Role> rList = roleDao.getRoles();
//                        this.getServletContext().setAttribute("roleList",rList);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        request.getRequestDispatcher("register.jsp").forward(request,response);

        InputStream is = request.getInputStream();
        FileOutputStream fos = new FileOutputStream("C:\\A\\Sys\\intellij\\project\\test2\\info.data");
        byte[] b = new byte[1024];
        int end = 0 ;
        while((end = is.read())>0){
            fos.write(b,0,end);
        }
        fos.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request,response);
        Object oj = this.getServletContext().getAttribute("roleList");
        if(oj == null && ((List)oj).size()<1){
            try {
                synchronized (this.getServletContext()){
                    if(oj == null && ((List)oj).size()<1){
                        List<Role> rList = roleDao.getRoles();
                        this.getServletContext().setAttribute("roleList",rList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}

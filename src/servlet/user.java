package servlet;

import src.dao.IUserDAO;
import src.entity.Page;
import src.entity.User;
import src.util.DaoFactory;
import src.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by z on 2018/10/8.
 */
@WebServlet(name = "user")
public class user extends HttpServlet {

    private IUserDAO userDao = DaoFactory.getUserDao();
    private int rowsPerPage = 3 ;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("show".equals(action)) {
            show(request,response);
        } else if("delete".equals(action)) {
                del(request,response);
            }
        else {
            show(request, response);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受客户端提交数据
        String ss = request.getParameter("page");
        int pageNum = 1 ;
        try{
            pageNum = Integer.parseInt(ss.trim());
        } catch (Exception e) {
            pageNum = 1 ;
        }
        //进行服务器端数据检查
        //调用java bean 完成业务处理
        try {
            Page pages = new Page();
            pages.setRowsPerPage(rowsPerPage);
            pages.setPageNum(pageNum);
            List<User> ulist = userDao.getByPage(pages);
            //根据返回结果决定跳转页面
            request.setAttribute("userlist",ulist);
            request.setAttribute("pageList",pages);
            request.getRequestDispatcher("show.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受客户端提交数据
        String ss = request.getParameter("id");
        int id = 0 ;
        try{
            id = Integer.parseInt(ss.trim());
            userDao.delete(id);
            response.sendRedirect("user");
            return ;
        } catch (Exception e){
            throw new ServletException(e);
        }

    }

        @Override
    public void init() throws ServletException{
        super.init();
        String ss = this.getServletConfig().getInitParameter("rowsPerPage");
        if(StringUtils.isNotBlank(ss)) {
            try{
                rowsPerPage = Integer.parseInt(ss.trim());
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

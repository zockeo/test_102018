package servlet;

import src.dao.IUserDAO;
import src.entity.User;
import src.util.DaoFactory;
import src.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z on 2018/9/12.
 */
@WebServlet(name = "login")
public class login extends HttpServlet {
    private IUserDAO userDAO = DaoFactory.getUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接受客户端提交数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        //进行服务器端数据检查
        HttpSession hs = request.getSession();
        Map<String ,String> info = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            info.put("username","username is needed!");
        }
        if (StringUtils.isBlank(password)) {
            info.put("password","password is needed!");
        }
        if (StringUtils.isBlank(code)) {
            info.put("code","pls input checkCode!");
        }
        else {
            Object obj = hs.getAttribute("code");
            if (!code.equals(obj))
                info.put("code","input correct checkCode!");
        }
        if (!info.isEmpty()) {
            request.setAttribute("info",info);
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }

        //调用java bean 完成业务处理
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        try {
            boolean b = userDAO.check(user);

            //根据返回结果决定跳转页面
            if(b){
                response.sendRedirect("user");
            } else {
                request.setAttribute("msg","Login failed! .. pls re try ~");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

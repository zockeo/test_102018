package servlet;

import src.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by z on 2018/9/6.
 */

@WebServlet(name = "code")
public class checkCode extends HttpServlet {

    private String src = "0123456789abcdefghijkmnpqrstuvwxyz" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bi = new BufferedImage(120 , 40 , BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0,0,120,40);
        g.setColor(Color.BLACK);
        g.drawRect(2,2,116,36);
        String checkCode = this.getCode();
        g.setColor(new Color(231,27,100));
        g.setFont(new Font("Segoe UI",Font.BOLD,26));
        g.drawString(checkCode,10,26);
        HttpSession hs = request.getSession();
        hs.setAttribute("code",checkCode);
        g.dispose();
        response.reset();
        response.setContentType("image/jpeg");
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setDateHeader("expires",0);

        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(bi,"jpg",sos);
        sos.flush();
        sos.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String s = getServletConfig().getInitParameter("src");
        if(StringUtils.isNotBlank(s)) {
            src = s.trim();
        }
    }

    private String getCode() {
        char[] c = new char[6];
        Random r = new Random();
        for(int i=0; i<6; i++) {
            c[i] = src.charAt(r.nextInt(src.length()));
        }
        return new String(c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

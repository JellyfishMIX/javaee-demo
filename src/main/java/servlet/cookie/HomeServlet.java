package servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author JellyfishMIX
 * @date 2021/6/15 11:53
 */
@WebServlet("/cookie/home")
public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止字符集乱码
        response.setContentType("text/html;charset=utf-8");
        String username = "";
        String checked = "";

        // 需要在登录时，将用户信息存入到Cookie中，在下次登录时可以从Cookie文件中读取这个用户之前保存的用户信息
        // 1. 在登录时，先取出之前存好的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SaveUser".equals(cookie.getName())) {
                    // 说明这个cookie存在
                    username = cookie.getValue();
                    // username不为null，也不为空串
                    if (username != null && !username.equals("")) {
                        // 直接返回用户登录后的界面
                        response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/welcome.html");
                        return;
                    }
                }
            }
        }

        // 返回 response
        // 标签输出，就是为了输出一个表单。
        // 为什么不用页面，因为页面中写不了Java代码
        // 具体代码参见《D:\河北农大\Java EE\09 cookie&session\资料\资料类\LoginServlet.txt》
        PrintWriter out = response.getWriter();
        // form表单
        out.write("<form action='" + request.getContextPath() + "/cookie/login' method='post'>");
        // html标签
        out.write("用户名：<input type='text' name='username' value='" + username + "' /><br/>");
        out.write("密码：<input type='password' name='password'/><br/>");
        out.write("记住用户名：<input type='checkbox' value='true' name='remember' " + checked + "/><br/>");
        out.write("<input type='submit' value='登录'/><br/>");
        out.write("</form>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
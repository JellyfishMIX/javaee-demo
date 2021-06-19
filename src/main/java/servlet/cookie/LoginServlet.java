package servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author JellyfishMIX
 * @date 2021/6/19 11:47
 */
@WebServlet("/cookie/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止字符集乱码，先设置 response，并取出输出对象
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 1.封装参数
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.验证用户登录是否正确 admin:123456
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 登录成功
            if ("true".equals(user.getRemember())) {
                // 如果复选框勾选，就是记住用户名checked
                // 怎么记住用户名呢？用Cookie，并将它发给客户端
                // 保存用户信息到Cookie
                Cookie cookie = new Cookie("SaveUser", user.getUsername());

                // 设置Cookie失效时间
                cookie.setMaxAge(Integer.MAX_VALUE);
                // 设置路径为：/应用名/.
                // 默认path路径为：/应用名/servlet/。访问的时候是不希望在地址栏中写servlet.
                // 如果希望本应用程序下所有的资源都可以访问这个Cookie，需要改写默认路径，不在用默认路径。
                // request.getContextPath();//得到/应用名/.就不再有servlet
                cookie.setPath(request.getContextPath());

                // 添加 cookie 到 response
                response.addCookie(cookie);
                // 给出提示信息
                out.write("登录成功，用户名已经记住了，客官");
                response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/welcome.html");
            } else {
                // 不记住用户名
                out.write("登录成功，2 秒后跳转到欢迎页面");
                response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/welcome.html");
            }
        } else {
            // 登录失败
            out.write("登录失败，2 秒后跳转到主页面");
            response.setHeader("Refresh", "2;URL=" + request.getContextPath() + "/cookie/home");
        }
    }
}
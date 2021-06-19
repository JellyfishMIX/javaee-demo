package servlet.authcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
 * 输出验证码
 */
@WebServlet("/auth-code")
public class AuthCode extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置图片的高和宽
        int height = 25, width = 120;
        // 1.得到一个图像缓冲区，使用BufferedImage类
        // BufferedImage(weight,height,imageType)
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2.得到一支画笔，使用Graphics类，awt包中类
        Graphics g = bufferedImage.getGraphics();

        // 3.画矩形、填充背景色、画干扰线条、画字符串
        // 3.1 画矩形框，可以先调画笔颜色
        // 设置画笔颜色为BLUE
        g.setColor(Color.BLUE);
        // 画矩形框
        g.drawRect(0, 0, width, height);
        //3.2 填充背景
        // 设置画笔颜色为YELLOW
        g.setColor(Color.YELLOW);
        // 边框颜色不变
        g.fillRect(1, 1, width - 2, height - 2);

        // 3.3 画干扰线条
        // 设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        // 两点确定一条直线
        // g.drawLine(x1,y1,x2,y2);
        // 画随机线条，引入随机数Random，生成随机坐标值
        Random random = new Random();

        //生成20条随机线条
        for (int i = 0; i < 20; i++) {
            //random.nextInt(width),x轴方向不能超出范围
            //random.nextInt(height),y轴方向不能超出范围
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }

        // 3.4 随机字符串,先设置字体颜色、大小
        // 设置字体颜色
        g.setColor(Color.RED);
        g.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 20));//设置字体类型、字号Font.BOLD|Font.ITALIC两种类型做叠加
        // 生成4个随机数
        for (int i = 0; i < 4; i++) {
            g.drawString(random.nextInt(9) + "", 20 + (i * 20), 20);
        }

        // 4.将画好的缓冲区的图像写入到浏览器
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        // 4.1服务器要通过响应消息头，告知客户端，发送的内容是一幅图片
        response.setHeader("Content-Type", "image/jpeg");

        // 验证码不要在客户端缓存，因为每次生成的验证码都不一样，设置响应头，告诉客户端不要缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
    }
}
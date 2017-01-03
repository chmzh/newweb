package com.cmz.web1.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCodeUtil {
	private static final int SALT_SIZE = 8;
	public static final int WIDTH = 120;//生成的图片的宽度
	public static final int HEIGHT = 30;//生成的图片的高度
	public void validateCode(HttpServletRequest request,HttpServletResponse response){
	//1.在内存中创建一张图片
	    BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	    //2.得到图片
	    Graphics g = bi.getGraphics();
	    //3.设置图片的背影色
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    //4.设置图片的边框
	    g.setColor(Color.GREEN);
	    g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	    //5.在图片上画干扰线
	    g.setColor(Color.GREEN);
	    for (int i = 0; i < 10; i++) {
	        int x1 = new Random().nextInt(WIDTH);
	        int y1 = new Random().nextInt(HEIGHT);
	        int x2 = new Random().nextInt(WIDTH);
	        int y2 = new Random().nextInt(HEIGHT);
	        g.drawLine(x1, y1, x2, y2);
	    }
	    //6.写在图片上随机数
	    String random = drawRandomNum((Graphics2D) g);//生成数字和字母组合的验证码图片
	    //7.将随机数存在session中
	    request.getSession().setAttribute("checkcode", random);
	    //8.设置响应头通知浏览器以图片的形式打开
	    response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
	    //9.设置响应头控制浏览器不要缓存
	    response.setDateHeader("expries", -1);
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    //10.将图片写给浏览器
	    try {
			ImageIO.write(bi, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String drawRandomNum(Graphics2D g) {
	    // 设置颜色
	    g.setColor(Color.RED);
	    // 设置字体
	    g.setFont(new Font("微软雅黑", Font.BOLD, 20));
	    //数字和字母的组合
	    String baseNumLetter = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	    StringBuffer sb = new StringBuffer();
	    int x = 5;
	    String ch ="";
	    // 控制字数
	    for (int i = 0; i < 4; i++) {
	        // 设置字体旋转角度
	        int degree = new Random().nextInt() % 30;
	        ch = baseNumLetter.charAt(new Random().nextInt(baseNumLetter.length())) + "";
	        sb.append(ch);
	        // 正向角度
	        g.rotate(degree * Math.PI / 180, x, 20);
	        g.drawString(ch, x, 20);
	        // 反向角度
	        g.rotate(-degree * Math.PI / 180, x, 20);
	        x += 30;
	    }
	    return sb.toString();
	}
}

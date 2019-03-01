package com.example.demo.Controller;

import com.example.demo.se.ImageCode.ImageCode;
import com.example.demo.se.ImageCode.KaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@RestController
public class ValidateCodeController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping(value = "/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = crateImageCode(request);
        // 存入session
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    private ImageCode crateImageCode(HttpServletRequest request) {
//        int width = 67;
//        int height = 23;
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        Random random = new Random();
//        g.setColor(getRandColor(200, 250));
//        g.fillRect(0, 0, width, height);
//        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
//        g.setColor(getRandColor(160, 200));
//        for (int i = 0; i < 155; i++) {
//            int x = random.nextInt(width);
//            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            g.drawLine(x, y, x + xl, y + yl);
//        }
//        String sRand = "";
//        for (int i = 0; i < 4; i++) {
//            String rand = String.valueOf(random.nextInt(10));
//            sRand += rand;
//            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
//            g.drawString(rand, 13 * i + 6, 16);
//        }
//        g.dispose();
//        return new ImageCode(image, sRand,300);
        System.out.println("111");
        //生产验证码字符串
        String code = defaultKaptcha.createText();
        //使用生产的验证码字符串返回一个BufferedImage对象
        BufferedImage image = defaultKaptcha.createImage(code);
        System.out.println(code);
        return new ImageCode(image, code,300);
    }
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
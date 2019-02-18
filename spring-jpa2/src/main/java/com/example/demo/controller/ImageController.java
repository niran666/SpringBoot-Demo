package com.example.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {
    //    @RequestMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public BufferedImage getImage() throws IOException {
//        return ImageIO.read(new FileInputStream(new File("E:/1.jpg")));
//    }
    @RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage() throws IOException {
        File file = new File("E:/1.jpg");
        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
        return ImageIO.read(inputStream);
    }
}
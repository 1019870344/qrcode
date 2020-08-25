package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.QRCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/select")
    public User selectByPrimaryKey(Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping("/")
    public String index(){
       return "qrcode";
    }

    @RequestMapping("/getqrcode")
    @ResponseBody
    public void getQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取前台传入的信息，此处可操作性很大，大家可以通过自己查询数据库，或者通过判断填入其他信息
        String content = request.getParameter("content");
//        String content = "qqqq";
        String deskPath = "";
        //二维码图片中间logo
        String imgPath = "";
        Boolean needCompress = true;
        //通过调用我们的写的工具类，拿到图片流
        ByteArrayOutputStream out = QRCodeUtils.encode(content ,imgPath,deskPath, needCompress);
        //定义返回参数
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg;charset=UTF-8");
        response.setContentLength(out.size());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(out.toByteArray());
        outputStream.flush();
        outputStream.close();
    }

}

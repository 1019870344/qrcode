package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/select")
    public User selectByPrimaryKey(Integer id) {
        return userService.selectByPrimaryKey(id);
    }
}

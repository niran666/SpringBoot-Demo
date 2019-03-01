package com.example.demo.Controller;


import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login()throws ServletException{
        throw new ServletException("需要登录");
    }

    @RequestMapping(value ="/register",method=RequestMethod.POST)
//    public Result register(@RequestParam("username") String username,@RequestParam("password") String password){
    public Result register(Account account) throws ServletException {
        if(accountService.addAccount(account)==1){
            return new Result("注册成功");
        }
        else{
            throw new ServletException("注册失败");
        }
    }
    @GetMapping("/timeout")
    public Result get()throws ServletException{
        throw new ServletException("登陆超时");
    }
}

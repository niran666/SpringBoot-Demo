package com.example.demo.controller;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Vo.Account;
import com.example.demo.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/login")
    Result login(@RequestParam("username") String username,@RequestParam("password") String password) throws ServletException
    {
        Account account=accountRepository.findAccountByUsername(username);
        if(account == null)
            throw new ServletException("用户不存在");
        else if(!password.equals(account.getPassword()))
            throw new ServletException("密码错误");
        else
            return new Result(account);
    }

    @PostMapping("/register")
    Result register(@RequestParam("username") String username,@RequestParam("password") String password) throws ServletException
    {
        Account account=new Account(username,password);
        if(accountRepository.existsAccountByUsername(username))
        {
            throw new ServletException("用户已存在");
        }
        try{
            accountRepository.save(account);
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result(account);
    }
}

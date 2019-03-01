package com.example.demo;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private AccountService accountService;
    @Test
    public void contextLoads() {

//        Account account=new Account();
//        account.setName("username");
//        account.setPassword("123456");
//
//        accountService.addAccount(account);
        System.out.println( DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}

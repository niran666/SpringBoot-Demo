package com.example.demo.Service;


import com.example.demo.pojo.Account;
import org.springframework.stereotype.Service;

public interface AccountService {
    int addAccount(Account account);
    Account findByName(String username);
}

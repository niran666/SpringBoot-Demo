package com.example.demo.Service.Impl;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public int addAccount(Account account) {
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        System.out.println(account.getPassword());
        if(accountRepository.save(account)!=null){
            return 1;
        }
        else{
            return 0;
        }

    }
    public Account findByName(String username)
    {
        return accountRepository.findAccountByUsername(username);
    }
}

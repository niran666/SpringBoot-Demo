package com.example.demo.se;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // 从数据库中取出用户信息
        Account user = accountRepository.findAccountByUsername(username);
//        // 判断用户是否存在
        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//      返回UserDetails实现类
        return new User(user.getUsername(), // 用户名
                user.getPassword(), // 密码
                true, // 是否可用
                true, // 账号是否过期
                true, // 密码是否过期
                true, // 账号没有被锁定标志
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
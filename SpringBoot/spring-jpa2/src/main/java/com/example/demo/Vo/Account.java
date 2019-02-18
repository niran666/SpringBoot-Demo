package com.example.demo.Vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity

public class Account {
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String identity;

    public Account()
    {
        this.identity="普通用户";
    }
    public Account(String username,String password) {
        this.username = username;
        this.password=password;
        this.identity="普通用户";
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}

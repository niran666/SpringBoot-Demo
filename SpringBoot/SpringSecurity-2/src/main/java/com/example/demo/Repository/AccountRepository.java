package com.example.demo.Repository;

import com.example.demo.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUsername(String username);

}

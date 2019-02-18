package com.example.demo.Repository;

import com.example.demo.Vo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUsernameAndPassword(String username, String password);
    Account findAccountByUsername(String username);

    public boolean existsAccountByUsername(String name);


}

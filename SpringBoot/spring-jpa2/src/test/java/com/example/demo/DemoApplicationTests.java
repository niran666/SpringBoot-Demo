package com.example.demo;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import com.example.demo.Vo.Account;
import com.example.demo.Vo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
//    private BookRepository bookRepository;
    private AccountRepository accountRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @Test
    public void contextLoads() {
        List<Book> bookList=null;
        bookList=bookService.findByKind("科学");
        System.out.println(bookList);

//        System.out.println(bookRepository.deleteById(2));
//        bookRepository.save(new Book("ni","ran","zhi"));
//        List<Book> BookList=bookRepository.findBooksByNameLike("%"+"2"+"%");
//        for(Book book:BookList)
//            System.out.println(book.getId());
//        System.out.println(accountRepository.existsAccountByUsername("3160608065"));
//        Account account=accountRepository.findAccountByUsernameAndPassword("316060065","niran");
//       System.out.println(account.getIdentity());
    }

}


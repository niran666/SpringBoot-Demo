package com.example.demo.Service;

import com.example.demo.Vo.Book;

import java.util.List;

public interface BookService {

    Book findById(int id);
    List<Book> findByName(String name);
    List<Book> findByKind(String kind);
    List<Book> findAll();
    int deleteById(int id);
}

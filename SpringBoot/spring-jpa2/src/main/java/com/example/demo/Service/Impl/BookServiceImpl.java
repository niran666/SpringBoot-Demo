package com.example.demo.Service.Impl;

import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import com.example.demo.Vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    BookServiceImpl(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }
    @Override
    public Book findById(int id){
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> findByName(String name){
        return bookRepository.findBooksByNameLike("%"+name+"%");
    }

    @Override
    public List<Book> findByKind(String kind){
        return bookRepository.findAllByKind(kind);
    }

    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    @Override
    public int deleteById(int id)
    {
        return bookRepository.deleteById(id);
    }

}

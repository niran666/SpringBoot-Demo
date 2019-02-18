package com.example.demo.controller;

import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import com.example.demo.Vo.Book;
import com.example.demo.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")

public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/SearchByKind")
//    @RequestParam("kind") String kind
    public Result SearchByKind(@RequestParam("kind") String kind) throws ServletException {
        List<Book> bookList=null;
        System.out.println(kind);
        try{
            bookList=bookService.findByKind(kind);
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result(bookList);
    }

    @DeleteMapping("/DeleteById")
     public Result DeleteById(@RequestParam("id") int id) throws ServletException
    {
        try{
            bookService.deleteById(id);
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result();
    }

    @PostMapping("/SearchById")
    public Result SearchById(@RequestParam("id") int id) throws ServletException
    {
        Book book;
        try{
            book=bookService.findById(id);
            if(book==null)
                throw new ServletException("该书不存在");
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result(book);
    }

    @PostMapping("/SearchByName")
    public Result SearchByName(@RequestParam("name") String name) throws ServletException {
        List<Book> bookList=null;
        try{
            bookList=bookService.findByName(name);
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result(bookList);
    }

    @GetMapping("/SearchAllBook")
    public Result SearchAllBook() throws ServletException {
        List<Book> bookList=null;
        try{
            bookList=bookService.findAll();
        }
        catch (Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        return new Result(bookList);
    }
}

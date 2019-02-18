package com.example.demo.Repository;

import com.example.demo.Vo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByName(String name);

    Book findBookById(int id);

    List<Book> findBooksByNameLike(String name);
    List<Book> findAllByKind(String kind);

    List<Book> findAll();

    @Transactional
    int deleteById(int id);
}

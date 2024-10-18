package com.Library.E_Library.service;

import com.Library.E_Library.entity.Book;
import com.Library.E_Library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        log.info("Saving a new book");
        Book savedBook = this.bookRepository.save(book);
        log.info("Saved a new book with id: {}", savedBook.getBookId());
        return savedBook;
    }

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Book getBookById(UUID bookId){
        Optional<Book> optionalBook =  this.bookRepository.findById(bookId);
        return optionalBook.orElse(null);
    }
}

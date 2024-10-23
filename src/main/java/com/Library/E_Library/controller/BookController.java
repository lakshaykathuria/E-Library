package com.Library.E_Library.controller;

import com.Library.E_Library.entity.Book;
import com.Library.E_Library.service.BookService;
import com.Library.E_Library.service.RedisService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final RedisService redisService;



    @Autowired
    public BookController(BookService bookService, RedisService redisService) {
        this.bookService = bookService;
        this.redisService = redisService;
    }


    @PostMapping("/add/newbook")
    public ResponseEntity<Book> addNewBook (@RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        }

    @GetMapping("/list/allbooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = this.bookService.getAllBooks();
//        this.redisService.addToCache("list", bookList);

//        List<Book> testList = (List<Book>) this.redisService.getCacheData("list");
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }


    //Path Parameter
//    @GetMapping("/{bookId}")
//    public ResponseEntity<Book> getTheBook(@PathVariable UUID bookId){
//        Book getbook = this.bookService.getBook(bookId);
//        return new ResponseEntity<>(getbook,HttpStatus.OK);
//    }


    //Request Parameter
        @GetMapping("/")
        public ResponseEntity<Book> getTheBook(@RequestParam UUID bookId) {
            Book getbook = this.bookService.getBookById(bookId);
            return new ResponseEntity<>(getbook, HttpStatus.OK);
        }



}

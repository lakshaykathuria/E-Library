package com.Library.E_Library.service;

import com.Library.E_Library.ELibraryApplication;
import com.Library.E_Library.entity.Book;
import com.Library.E_Library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest(classes = ELibraryApplication.class)
public class BookServiceTest {
    private final BookService bookService;

    @Autowired
    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    @MockBean
    private BookRepository bookRepository;

    static Book book = Book.builder()
            .bookId(UUID.randomUUID())
            .bookName("Huu lalalal")
            .category(Book.Category.FICTION)
            .price(1234D)
            .language("Test")
            .isbn("123654")
            .build();

    @Test
    void getAllBooks_WhenThereAre3Books(){
        List<Book> bookList = Arrays.asList(book,book.withPrice(1230D), book.withPrice(7878D));

        Mockito.when(this.bookRepository.findAll()).thenReturn(bookList);
        List<Book> fetchedBookList = this.bookService.getAllBooks();

        Assertions.assertEquals(bookList.size(), fetchedBookList.size());
    }

    @Test
    void getAllBooks_WhenThereAreNoBooks(){

        Mockito.when(this.bookRepository.findAll()).thenReturn(Collections.emptyList());
        List<Book> fetchedBookList = this.bookService.getAllBooks();

        Assertions.assertEquals(0, fetchedBookList.size());
    }

    @Test
    void getBookById_WhenTheBookExists_shouldReturnBook(){
        Mockito.when(this.bookRepository.findById(book.getBookId())).thenReturn(Optional.of(book));

        Book book1 = this.bookService.getBookById(book.getBookId());
        Assertions.assertEquals(book,book1);
    }

    @Test
    void getBookById_WhenTheBookNotExists_shouldReturnNull(){
        UUID invaliId = UUID.randomUUID();
        Mockito.when(this.bookRepository.findById(invaliId)).thenReturn(Optional.empty());

        Book book1 = this.bookService.getBookById(book.getBookId());
        Assertions.assertNull(book1);
//        Assertions.assertEquals(null,book1);
    }

    @Test
    void addBook_whenBookIsPassed_sholudReturnAddedBook(){
         Mockito.when(this.bookRepository.save(book)).thenReturn(book);

         Book book1 = this.bookService.addBook(book);

         Assertions.assertEquals(book,book1);
    }

}

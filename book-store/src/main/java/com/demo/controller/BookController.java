package com.demo.controller;

import com.demo.bookHelp.BookHelper;
import com.demo.entities.Book;
import com.demo.exceptions.BookNotFoundException;
import com.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/digitalbooks")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping("/bookList")
    public List<Book> getBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable int id) throws BookNotFoundException{
        return bookService.findBookById(id);
    }

    @GetMapping("/list-all")
    public List<Book> findBookList(@RequestParam String bookIds) throws BookNotFoundException {
        List<Integer> bookIdList = new ArrayList<>();
        for (String book : bookIds.split(",")) {
            bookIdList.add(Integer.parseInt(book));
        }
        return bookService.findAllBookByIds(bookIdList);
    }

    @DeleteMapping("/{id}")
    public void  deleteBookById(@PathVariable int id) throws BookNotFoundException{
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}/author/{authorId}")
    public Book updateBook(@PathVariable int id, @PathVariable int authorId, @RequestBody Book book) throws BookNotFoundException{
        return bookService.updateBook(id, authorId, book);
    }

    @GetMapping("/title")
    public List<Book> findBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTittle(title);
    }

    @GetMapping("/price/{price}")
    public List<Book> findBooksByPrice(@PathVariable Long price) {
        return bookService.findBooksByPrice(price);
    }

    @GetMapping("/search")
    public List<Book> getBooksList(@RequestParam Optional<String> title, @RequestParam Optional<String> category,
                                   @RequestParam Optional<Long> price, @RequestParam Optional<String> releasedDate, @RequestParam Optional<String> publisher) {
        BookHelper b = new BookHelper();
        b.setTitle(title.isPresent()?title.get() : null);
        b.setCategory(category.isPresent()? category.get() : null);
        b.setPrice(price.isPresent()?price.get():null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(releasedDate.isPresent()) {
            LocalDate dateTime = LocalDate.parse(releasedDate.get(), formatter);
            b.setReleasedDate(dateTime);
        }
        b.setPublisher(publisher.isPresent()?publisher.get():null);
        return bookService.findMatchBooks(b);
    }

}

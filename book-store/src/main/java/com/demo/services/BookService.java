package com.demo.services;

import com.demo.bookHelp.BookHelper;
import com.demo.entities.Book;
import com.demo.exceptions.BookNotFoundException;
import com.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

//    Saving new book in Database

    public Book saveBook(Book book){

        return bookRepository.save(book);
    }

//    Getting all books details

    public List<Book> findAllBooks(){

        return bookRepository.findAll();
    }

    //find book by id

    public Book findBookById(int id) throws BookNotFoundException {

        Optional<Book> optionalBook = bookRepository.findByIdandIsActive(id);

        if (optionalBook.isEmpty()){
            throw new BookNotFoundException("Book is not available or blocked for this Id ("+id+") book");
        }
        else {
            return optionalBook.get();
        }

    }

    //Books by price details

    public List<Book> findBooksByPrice(Long price){

        if (price != null){
            return  bookRepository.findByPriceGreaterThen(price);
        }
        else
        {
            return findAllBooks();
        }
    }

    //Books by tittle details

    public List<Book> findBooksByTittle(String title){

        if (title != null && title.length() != 0){
            return bookRepository.findByTitle(title);
        }
        else {
            return findAllBooks();
        }
    }

    //Books by Filter Details

    public List<Book> findMatchBooks(BookHelper bookFind) {
        return bookRepository.findAll(bookFind.getTitle(), bookFind.getPublisher(), bookFind.getCategory(), bookFind.getPrice(), bookFind.getReleasedDate());
    }

    //Delete Book By id

    public void deleteBook(int id) throws BookNotFoundException{
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()){
            throw new BookNotFoundException("Book is not present for this id" +id);
        }
        else {
            bookRepository.deleteById(id);
        }
    }

    //Update Book Details Data

    public Book updateBook(int id, int authorId, Book book) throws BookNotFoundException{

        Book book1 = bookRepository.findByIdAndAuthorId(id, authorId);

        if (book1 != null){

            book1.setTitle(book.getTitle() != null ? book.getTitle() : book1.getTitle());
            book1.setCategory(book.getCategory() != null ? book.getCategory() : book1.getCategory());
            book1.setPrice(book.getPrice() != null ? book.getPrice() : book1.getPrice());
            book1.setPublisher(book.getPublisher() != null ? book.getPublisher() : book1.getPublisher());
            book1.setContent(book.getContent() != null ? book.getContent() : book1.getContent());
            book1.setReleasedDate(book.getReleasedDate() != null ? book.getReleasedDate() : book1.getReleasedDate());
            book1.setActive(book.isActive());
            return saveBook(book1);

        }
        else {
            System.out.println("Book is not available for this id" +id);
            throw new BookNotFoundException("Book is not available for this id" +id);
        }
    }


    public List<Book> findAllBookByIds(List<Integer> bookIds) throws BookNotFoundException{

        List<Book> bookList = bookRepository.findByIdIn(bookIds);
        if (bookList == null){
            throw new BookNotFoundException("Book is not available or present for these id");
        }
        else {
            return bookList;
        }
    }
}

package com.demo.repositories;

import com.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = false , value = "Select book from Book book where book.title = ?1 and book.active = true")
    List<Book> findByTitle(String title);

    @Query(nativeQuery = false , value = "Select book from Book book where book.price > ?1 and book.active = true")
    List<Book> findByPriceGreaterThen(Long price);

    @Query(nativeQuery = false, value = "Select book from Book book where book.active = true and book.id = ?1")
    Optional<Book> findByIdandIsActive(int id);

    @Query(nativeQuery = false, value = "Select b from Book b where b.active = true and (b.title = ?1 or b.publisher = ?2 or b.category = ?3 or b.price = ?4 or b.releasedDate = ?5)")
    List<Book> findAll(String title,String publisher,String category, Long price, LocalDate releasedDate);

    @Query(nativeQuery = false, value = "Select book from Book book where book.active = true")
    List<Book> findAll();

    List<Book> findByIdIn(List<Integer> ids);

    Book findByIdAndAuthorId(int id, int authorId);
}

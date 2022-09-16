package com.demo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Long price;

    @Column(name = "releasedDate")
    private LocalDate releasedDate = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "content")
    private String content;

    @Column(name = "authorId")
    private int authorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Book(int id, String title, String author, String category, Long price) {
        super();
        this.id = id;
        this.title = title;
        this.publisher = author;
        this.category = category;
        this.price = price;
    }

    public Book(String title, String author, String category, Long price, String content) {
        super();
        this.title = title;
        this.publisher = author;
        this.category = category;
        this.price = price;
        this.content = content;
    }

    public Book(int id, String title, String author, String category, Long price, LocalDate releasedDate) {
        super();
        this.id = id;
        this.title = title;
        this.publisher = author;
        this.category = category;
        this.price = price;
        this.releasedDate = releasedDate;
    }

    public Book(int id, String title, String author, String category, Long price, LocalDate releasedDate, boolean ative,
                String content,int authorId) {
        super();
        this.id = id;
        this.title = title;
        this.publisher = author;
        this.category = category;
        this.price = price;
        this.releasedDate = releasedDate;
        this.active = ative;
        this.content = content;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", publisher=" + publisher + ", category=" + category
                + ", price=" + price + ", releasedDate=" + releasedDate + ", active=" + active + ", content=" + content
                + ", authorId=" + authorId + "]";
    }
    public Book() {
        super();
    }
}

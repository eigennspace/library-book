package com.harist.belajarspring.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findAllByTitle(String title);

    public List<Book> findAllByTitleAndAuthor(String title, String Author);

}

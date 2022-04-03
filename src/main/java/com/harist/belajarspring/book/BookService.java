package com.harist.belajarspring.book;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final static Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    public Book createBook(Book newBook) {

        return this.bookRepository.save(newBook);
    }

    public Book findBookID(Long id) {
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if (optionalBook.isPresent()) {

            return optionalBook.get();
        }
        logger.error("Book Not Found");
        throw new BookNotFoundException();
    }

    public Book updateBook(Book book) {
        this.findBookID(book.getId());

        return this.bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        Book foundBook = this.findBookID(book.getId());

        this.bookRepository.delete(foundBook);

    }

    public Book borrowBook(Book book) {
        Book borrowBook = this.findBookID(book.getId());

        borrowBook.borrow();

        return this.bookRepository.save(borrowBook);
    }

    public Book returnedBook(Book book) {
        Book returnedBook = this.findBookID(book.getId());

        returnedBook.returned();

        return this.bookRepository.save(returnedBook);
    }
}

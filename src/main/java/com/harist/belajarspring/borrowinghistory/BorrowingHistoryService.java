package com.harist.belajarspring.borrowinghistory;

import com.harist.belajarspring.book.Book;
import com.harist.belajarspring.book.BookService;
import com.harist.belajarspring.customer.Customer;
import com.harist.belajarspring.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BorrowingHistoryService {
    private final BookService bookService;
    private final CustomerService customerService;
    private final BorrowingHistoryRepository borrowingHistoryRepository;

    public BorrowingHistory create(BorrowingHistory borrowingHistory) {
        Customer customer = this.customerService.findCustomer(borrowingHistory.getCustomer());
        Book book = this.bookService.borrowBook(borrowingHistory.getBook());

        BorrowingHistory newBorrowingHistory = BorrowingHistory.builder().book(book).customer(customer).build();

        return this.borrowingHistoryRepository.save(newBorrowingHistory);

    }
}

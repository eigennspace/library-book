package com.harist.belajarspring.customer;

import com.harist.belajarspring.book.BookRequestDTO;
import com.harist.belajarspring.borrowinghistory.BorrowingHistoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRequestDTO.convertToEntity();
        Customer savedCustomer = this.customerService.create(customer);
        CustomerResponseDTO customerResponseDTO = savedCustomer.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }
}

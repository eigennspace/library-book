package com.harist.belajarspring.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harist.belajarspring.author.Author;
import com.harist.belajarspring.borrowinghistory.BorrowingHistory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private Author author;

    @OneToMany
    private List<BorrowingHistory> borrowingHistories;

    private int quantity;

    public BookResponseDTO convertToResponse() {
        return BookResponseDTO.builder().title(this.title).authorResponseDTO(this.author.convertToResponse()).build();
    }

    public void borrow() {
        int newQuantity = this.quantity - 1;

        if (newQuantity < 0) {
            throw new BookOutOfStockException();
        }
        this.quantity = newQuantity;
    }

    public void returned() {
        
        this.quantity += 1;
    }
}

package com.harist.belajarspring.customer;

import com.harist.belajarspring.borrowinghistory.BorrowingHistory;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updateAt;

    @OneToMany
    private List<BorrowingHistory> borrowingHistories;

    public CustomerResponseDTO convertToResponse() {
        return CustomerResponseDTO.builder().id(this.id).name(this.name).build();
    }
}

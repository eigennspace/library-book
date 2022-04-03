package com.harist.belajarspring.author;

import com.harist.belajarspring.book.Book;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Book> books;

    public AuthorResponseDTO convertToResponse() {
        return AuthorResponseDTO.builder()
                .id(this.id).name(this.name).build();
    }
}


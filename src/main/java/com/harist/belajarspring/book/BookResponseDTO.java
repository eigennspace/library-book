package com.harist.belajarspring.book;

import com.harist.belajarspring.author.AuthorResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {
    private String title;
    private AuthorResponseDTO authorResponseDTO;

}

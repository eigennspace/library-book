package com.harist.belajarspring.book;

import com.harist.belajarspring.author.AuthorRequestDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {
    @NotBlank(message = "title is mandatory")
    private String title;
    @Valid
    private AuthorRequestDTO authorRequestDTO;


    public Book convertToEntity() {
        return Book.builder().title(this.title).author(this.authorRequestDTO.convertToEntity())
                .build();
    }


}

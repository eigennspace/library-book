package com.harist.belajarspring.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthorResponseDTO {
    private Long id;
    private String name;
}

package com.harist.belajarspring.author;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDTO {
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;

    public Author convertToEntity() {
        return Author.builder().id(this.id).name(this.name).build();
    }
}

package com.harist.belajarspring.author;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<Author> authors = this.authorService.getAll();
        logger.info("Request Get All Author");
        List<AuthorResponseDTO> authorResponseDTOS = authors.stream()
                .map(author -> author.convertToResponse())
                .collect(Collectors.toList());

        return ResponseEntity.ok(authorResponseDTOS);
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDTO> createBook(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
        Author newAuthor = authorRequestDTO.convertToEntity();

        Author author = this.authorService.createAuthor(newAuthor);
        AuthorResponseDTO authorResponseDTO = author.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO);
    }

    @GetMapping("/authors{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@PathVariable("id") Long id) {
        Author author = this.authorService.findAuthorId(id);

        AuthorResponseDTO authorResponseDTO = author.convertToResponse();

        return ResponseEntity.ok(authorResponseDTO);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        Author author = authorRequestDTO.convertToEntity();
        author.setId(id);

        Author updateAuthor = this.authorService.updateAuthor(author);

        return ResponseEntity.ok(updateAuthor.convertToResponse());
    }

    @DeleteMapping("/authors/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        Author author = new Author();
        author.setId(id);

        this.authorService.deleteAuthor(author);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

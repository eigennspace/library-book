package com.harist.belajarspring.book;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<Book> books = this.bookService.getAll();
        List<BookResponseDTO> bookResponseDTOS = books.stream()
                .map(book -> book.convertToResponse())
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookResponseDTOS);
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        Book newBook = bookRequestDTO.convertToEntity();

        Book book = this.bookService.createBook(newBook);
        BookResponseDTO bookResponseDTO = book.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getId(@PathVariable("id") Long id) {
        Book book = this.bookService.findBookID(id);

        BookResponseDTO bookResponseDTO = book.convertToResponse();

        return ResponseEntity.ok(bookResponseDTO);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        Book book = bookRequestDTO.convertToEntity();
        book.setId(id);

        Book updatedBook = this.bookService.updateBook(book);

        return ResponseEntity.ok(updatedBook.convertToResponse());
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        Book book = new Book();
        book.setId(id);

        this.bookService.deleteBook(book);
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

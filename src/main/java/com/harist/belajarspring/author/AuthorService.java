package com.harist.belajarspring.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    public Author createAuthor(Author newAuthor) {
        return this.authorRepository.save(newAuthor);
    }

    public Author findAuthorId(Long id) {
        Optional<Author> optionalAuthor = this.authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }
        throw new AuthorNotFoundException();
    }

    public Author updateAuthor(Author author) {
        this.findAuthorId(author.getId());

        return this.authorRepository.save(author);
    }

    public void deleteAuthor(Author author) {
        Author foundAuthor = this.findAuthorId(author.getId());

        this.authorRepository.delete(foundAuthor);
    }
}

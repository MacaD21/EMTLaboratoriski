package ukim.finki.emtlabs.service;

import ukim.finki.emtlabs.model.Author;
import ukim.finki.emtlabs.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> update(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}

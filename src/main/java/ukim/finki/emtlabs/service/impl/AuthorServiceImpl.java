package ukim.finki.emtlabs.service.impl;

import org.springframework.stereotype.Service;
import ukim.finki.emtlabs.model.Author;
import ukim.finki.emtlabs.model.Country;
import ukim.finki.emtlabs.model.dto.AuthorDto;
import ukim.finki.emtlabs.model.exceptions.AuthorNotFoundException;
import ukim.finki.emtlabs.model.exceptions.CountryNotFoundException;
import ukim.finki.emtlabs.model.exceptions.InvalidArgumentsException;
import ukim.finki.emtlabs.repository.AuthorRepository;
import ukim.finki.emtlabs.service.AuthorService;
import ukim.finki.emtlabs.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        if (authorDto.getName() == null || authorDto.getName().isEmpty() || authorDto.getSurname() == null || authorDto.getSurname().isEmpty())
            throw new InvalidArgumentsException();
        Country country = this.countryService.findById(authorDto.getCountryId()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto authorDto) {
        Author author = this.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = this.countryService.findById(authorDto.getCountryId()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

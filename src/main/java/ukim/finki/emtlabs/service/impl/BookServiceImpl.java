package ukim.finki.emtlabs.service.impl;

import org.springframework.stereotype.Service;
import ukim.finki.emtlabs.model.Author;
import ukim.finki.emtlabs.model.Book;
import ukim.finki.emtlabs.model.dto.BookDto;
import ukim.finki.emtlabs.model.exceptions.AuthorNotFoundException;
import ukim.finki.emtlabs.model.exceptions.BookNotFoundException;
import ukim.finki.emtlabs.model.exceptions.InvalidArgumentsException;
import ukim.finki.emtlabs.model.exceptions.NoMoreAvailableBookCopiesException;
import ukim.finki.emtlabs.repository.BookRepository;
import ukim.finki.emtlabs.service.AuthorService;
import ukim.finki.emtlabs.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        if (bookDto.getName() == null || bookDto.getName().isEmpty())
            throw new InvalidArgumentsException();
        Author author = this.authorService.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Author author = this.authorService.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            return Optional.of(this.bookRepository.save(book));
        }
        throw new NoMoreAvailableBookCopiesException(id);
    }
}

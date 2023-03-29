package ukim.finki.emtlabs.initializers;

import org.springframework.stereotype.Component;
import ukim.finki.emtlabs.model.Author;
import ukim.finki.emtlabs.model.Country;
import ukim.finki.emtlabs.model.dto.AuthorDto;
import ukim.finki.emtlabs.model.dto.BookDto;
import ukim.finki.emtlabs.model.dto.CountryDto;
import ukim.finki.emtlabs.model.enumerations.Category;
import ukim.finki.emtlabs.service.AuthorService;
import ukim.finki.emtlabs.service.BookService;
import ukim.finki.emtlabs.service.CountryService;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData()
    {
        this.countryService.save(new CountryDto("Macedonia", "Europe"));
        this.countryService.save(new CountryDto("Serbia", "Europe"));
        this.countryService.save(new CountryDto("USA", "North America"));

        List<Country> countryList = this.countryService.findAll();

        this.authorService.save(new AuthorDto("Marija","Dimitrova",countryList.get(0).getId()));
        this.authorService.save(new AuthorDto("Teo","Teov",countryList.get(1).getId()));
        this.authorService.save(new AuthorDto("Lea","Ristevska",countryList.get(2).getId()));

        List<Author> authors = this.authorService.findAll();

        this.bookService.save(new BookDto("I Know Why The Caged Bird Sings",Category.BIOGRAPHY,authors.get(0).getId(),5));
        this.bookService.save(new BookDto("East of Eden",Category.NOVEL,authors.get(1).getId(),4));
        this.bookService.save(new BookDto("The Sun Also Rises",Category.NOVEL,authors.get(2).getId(),2));
    }
}

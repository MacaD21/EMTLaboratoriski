package ukim.finki.emtlabs.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukim.finki.emtlabs.model.enumerations.Category;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"/api/categories"})
public class CategoryRestController {

    @GetMapping
    public List<Category> findAll() {
        return Arrays.asList(Category.values().clone());
    }
}

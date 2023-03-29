package ukim.finki.emtlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukim.finki.emtlabs.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

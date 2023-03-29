package ukim.finki.emtlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukim.finki.emtlabs.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}

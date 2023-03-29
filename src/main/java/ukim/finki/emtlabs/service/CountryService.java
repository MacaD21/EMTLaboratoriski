package ukim.finki.emtlabs.service;

import ukim.finki.emtlabs.model.Country;
import ukim.finki.emtlabs.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto countryDto);

    Optional<Country> update(Long id, CountryDto countryDto);

    void deleteById(Long id);
}

package ukim.finki.emtlabs.service.impl;

import org.springframework.stereotype.Service;
import ukim.finki.emtlabs.model.Country;
import ukim.finki.emtlabs.model.dto.CountryDto;
import ukim.finki.emtlabs.model.exceptions.CountryNotFoundException;
import ukim.finki.emtlabs.model.exceptions.InvalidArgumentsException;
import ukim.finki.emtlabs.repository.CountryRepository;
import ukim.finki.emtlabs.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        if (countryDto.getName() == null || countryDto.getContinent() == null || countryDto.getName().isEmpty() || countryDto.getContinent().isEmpty())
            throw new InvalidArgumentsException();
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, CountryDto countryDto) {
        Country country = this.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}

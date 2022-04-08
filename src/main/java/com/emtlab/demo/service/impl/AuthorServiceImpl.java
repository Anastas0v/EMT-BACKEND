package com.emtlab.demo.service.impl;

import com.emtlab.demo.model.Author;
import com.emtlab.demo.model.Country;
import com.emtlab.demo.model.dto.AuthorDTO;
import com.emtlab.demo.model.exceptions.InvalidAuthorException;
import com.emtlab.demo.model.exceptions.InvalidCountryException;
import com.emtlab.demo.repository.AuthorRepository;
import com.emtlab.demo.repository.CountryRepository;
import com.emtlab.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void saveAuthor(AuthorDTO authorDTO) throws InvalidCountryException {
        Country country = countryRepository.findById(authorDTO.getCountryId())
                .orElseThrow(() -> new InvalidCountryException("Invalid Country ID!"));
        Author author = new Author();
        author.setCountry(country);
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());

        authorRepository.save(author);
    }

    @Override
    public void editAuthor(Long id, AuthorDTO authorDTO) throws InvalidAuthorException, InvalidCountryException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new InvalidAuthorException("Invalid Author ID!"));
        Country country = countryRepository.findById(authorDTO.getCountryId())
                .orElseThrow(() -> new InvalidCountryException("Invalid Country ID!"));
        author.setSurname(authorDTO.getSurname());
        author.setCountry(country);
        author.setName(authorDTO.getName());

        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }


}

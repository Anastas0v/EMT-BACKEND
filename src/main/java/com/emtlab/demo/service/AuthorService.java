package com.emtlab.demo.service;

import com.emtlab.demo.model.Author;
import com.emtlab.demo.model.dto.AuthorDTO;
import com.emtlab.demo.model.exceptions.InvalidAuthorException;
import com.emtlab.demo.model.exceptions.InvalidCountryException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    void saveAuthor(AuthorDTO authorDTO) throws InvalidCountryException;
    void editAuthor(Long id, AuthorDTO authorDTO) throws InvalidAuthorException, InvalidCountryException;
    void deleteAuthor(Long id);
    Optional<Author> findAuthorById(Long id);
}

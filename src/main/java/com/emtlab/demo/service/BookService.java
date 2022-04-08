package com.emtlab.demo.service;

import com.emtlab.demo.model.Book;
import com.emtlab.demo.model.dto.BookDTO;
import com.emtlab.demo.model.exceptions.InvalidAuthorException;
import com.emtlab.demo.model.exceptions.InvalidBookException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    void saveBook(BookDTO bookDTO) throws InvalidAuthorException;
    void editBook(Long id, BookDTO bookDTO) throws InvalidBookException, InvalidAuthorException;
    void deleteBook(Long id);
    void markAsTaken(Long id) throws InvalidBookException;
    Optional<Book> findBookById(Long id);
}

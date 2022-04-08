package com.emtlab.demo.service.impl;

import com.emtlab.demo.model.Author;
import com.emtlab.demo.model.Book;
import com.emtlab.demo.model.dto.BookDTO;
import com.emtlab.demo.model.exceptions.InvalidAuthorException;
import com.emtlab.demo.model.exceptions.InvalidBookException;
import com.emtlab.demo.repository.AuthorRepository;
import com.emtlab.demo.repository.BookRepository;
import com.emtlab.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(BookDTO bookDTO) throws InvalidAuthorException {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new InvalidAuthorException("Invalid Author ID!"));

        Book book = new Book();
        book.setAuthor(author);
        book.setCategory(bookDTO.getCategory());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setName(bookDTO.getName());
        bookRepository.save(book);
    }

    @Override
    public void editBook(Long id, BookDTO bookDTO) throws InvalidBookException, InvalidAuthorException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new InvalidBookException("Invalid Book ID!"));
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new InvalidAuthorException("Invalid Author ID!"));

        book.setAuthor(author);
        book.setCategory(bookDTO.getCategory());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setName(bookDTO.getName());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) throws InvalidBookException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new InvalidBookException("Invalid Book ID!"));

        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }

        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }
}

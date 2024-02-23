package ru.maxima.libraryspringdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.maxima.libraryspringdemo.dao.BookMapper;
import ru.maxima.libraryspringdemo.model.Book;
import ru.maxima.libraryspringdemo.repositories.BookRepositories;
import ru.maxima.libraryspringdemo.repositories.PeopleRepositories;


import java.util.List;

@Service
public class BookService {
    private final BookRepositories repositories;
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public BookService(BookRepositories repositories, PeopleRepositories peopleRepositories) {
        this.repositories = repositories;
        this.peopleRepositories = peopleRepositories;
    }


    public List<Book> getAllBook(){
        return repositories.findAll();
    }

    public Book getIdBook(Long id) {
        return repositories.findById(id).orElse(null);
    }


    public List<Book> getOwnerId(Long ownerId){
        return repositories.findByOwner_Id(ownerId);
    }



    public void saveBook(Book book){
        repositories.save(book);
    }


    public void updateBook(Long id , Book editedBook){
        editedBook.setId(id);
        repositories.save(editedBook);
    }


    public void deleteBook(Long id){
        repositories.deleteById(id);
    }

    public void addOwner(Long id , Long ownerId){
        Book book = repositories.findById(id).orElse(null);
        if (book != null){
            book.setOwner(peopleRepositories.findById(ownerId).orElse(null));
        }
        repositories.save(book);
    }

    public void deleteOfPersonBook(Long id){
        Book deleteBook = repositories.findById(id).orElse(null);
        deleteBook.setOwner(null);
        repositories.save(deleteBook);
    }

}

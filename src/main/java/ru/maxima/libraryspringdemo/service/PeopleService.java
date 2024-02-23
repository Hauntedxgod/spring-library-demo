package ru.maxima.libraryspringdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.maxima.libraryspringdemo.model.Book;
import ru.maxima.libraryspringdemo.model.Person;
import ru.maxima.libraryspringdemo.repositories.BookRepositories;
import ru.maxima.libraryspringdemo.repositories.PeopleRepositories;

import java.util.List;


@Service
public class PeopleService {

    private final PeopleRepositories repositories;
    private final BookRepositories bookRepositories;
    @Autowired
    public PeopleService(PeopleRepositories repositories, BookRepositories bookRepositories) {
        this.repositories = repositories;
        this.bookRepositories = bookRepositories;
    }


    public List<Person> getAllPeople(){
        return repositories.findAll();
    }

    public Person getPersonId(Long id){
        return repositories.findById(id).orElse(null);
    }

    public void save(Person person){
        repositories.save(person);
    }

    public void updatePerson(Person editedPerson , Long id){
        editedPerson.setId(id);
        repositories.save(editedPerson);
    }


    public void deletePerson(Long id){
        repositories.deleteById(id);
    }


    public void addOwner(Long id , Long ownerId){
        Person people = repositories.findById(id).orElse(null);
        if (people != null){
            people.setBooks(bookRepositories.findByOwner_Id(ownerId));
        }
        repositories.save(people);

    }

    public void deleteBookFromPerson(Long id){
        Person deletePerson = repositories.findById(id).orElse(null);
        deletePerson.setBooks(null);
        repositories.save(deletePerson);
//        Book deleteBook = bookRepositories.findById(id).orElse(null);
//        deleteBook.setOwner(null);
//        bookRepositories.save(deleteBook);
    }


}

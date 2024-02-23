package ru.maxima.libraryspringdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.libraryspringdemo.model.Person;


@Repository
public interface PeopleRepositories extends JpaRepository<Person, Long> {

}

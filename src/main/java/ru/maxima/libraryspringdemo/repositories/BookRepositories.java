package ru.maxima.libraryspringdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.libraryspringdemo.model.Book;


import java.util.List;

@Repository
public interface BookRepositories extends JpaRepository<Book, Long> {


    List<Book> findByOwner_Id(Long id);

}

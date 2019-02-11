package org.reyabreu.bootstrap.persistence.repo;

import org.reyabreu.bootstrap.persistence.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List findByTitle(String title);

}

package org.reyabreu.bootstrap.persistence.repo;

import org.reyabreu.bootstrap.persistence.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List findByLastName(String lastName);

}

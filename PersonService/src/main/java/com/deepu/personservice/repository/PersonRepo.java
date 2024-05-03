package com.deepu.personservice.repository;

import com.deepu.personservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    List<Person> findByAge(int age);
}

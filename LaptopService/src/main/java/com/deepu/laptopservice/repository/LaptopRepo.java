package com.deepu.laptopservice.repository;

import com.deepu.laptopservice.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LaptopRepo extends MongoRepository<Laptop, String> {
    List<Laptop> findByProcessor(String processor);
    Laptop findByLaptopId(Long id);
}
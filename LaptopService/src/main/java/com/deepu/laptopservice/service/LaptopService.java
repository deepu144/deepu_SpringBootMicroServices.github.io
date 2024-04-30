package com.deepu.laptopservice.service;

import com.deepu.laptopservice.dto.LaptopDto;
import com.deepu.laptopservice.model.Laptop;
import org.springframework.http.ResponseEntity;
import javax.naming.directory.InvalidAttributesException;
import java.util.List;

public interface LaptopService {
    ResponseEntity<List<LaptopDto>> getAllLaptop(String processor);
    ResponseEntity<LaptopDto> addLaptop(Laptop laptop) throws InvalidAttributesException;
    ResponseEntity<LaptopDto> deleteAll();
    ResponseEntity<LaptopDto> getLaptop(Long id);
    ResponseEntity<LaptopDto> updateLaptop(Long id, Laptop laptop) throws InvalidAttributesException;
    ResponseEntity<LaptopDto> deleteLaptop(Long id);
}

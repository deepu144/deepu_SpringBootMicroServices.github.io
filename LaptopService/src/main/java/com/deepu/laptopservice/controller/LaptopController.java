package com.deepu.laptopservice.controller;

import com.deepu.laptopservice.dto.LaptopDto;
import com.deepu.laptopservice.model.Laptop;
import com.deepu.laptopservice.repository.LaptopRepo;
import com.deepu.laptopservice.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private LaptopRepo repo;

    @GetMapping("/laptops")
    public ResponseEntity<List<LaptopDto>> getAllLaptop(@RequestParam(required = false) String processor) {
        try {
            return laptopService.getAllLaptop(processor);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/laptop")
    public ResponseEntity<LaptopDto> addLaptop(@RequestBody Laptop laptop) {
        try {
            return laptopService.addLaptop(laptop);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<LaptopDto> deleteAllLaptop() {
        try {
            return laptopService.deleteAll();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<LaptopDto> getLaptop(@PathVariable Long id) {
        try {
            return laptopService.getLaptop(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/laptops/{id}")
    public ResponseEntity<LaptopDto> updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop) {
        try {
            return laptopService.updateLaptop(id, laptop);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<LaptopDto> deleteLaptop(@PathVariable Long id) {
        try {
            return laptopService.deleteLaptop(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addAll")
    public void addAll(@RequestBody List<Laptop> lap){
        repo.saveAll(lap);
    }


}

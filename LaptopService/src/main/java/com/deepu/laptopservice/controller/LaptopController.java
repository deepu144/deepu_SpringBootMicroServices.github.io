package com.deepu.laptopservice.controller;

import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.enumeration.ResponseStatus;
import com.deepu.laptopservice.response.CommonResponse;
import com.deepu.laptopservice.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @GetMapping("/laptops")
    public ResponseEntity<CommonResponse> getAllLaptop(@RequestParam(required = false) String processor) {
        try {
            return new ResponseEntity<>(laptopService.getAllLaptop(processor),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/laptop")
    public ResponseEntity<CommonResponse> addLaptop(@RequestBody LaptopWrapper laptopWrapper) {
        try {
            return new ResponseEntity<>(laptopService.addLaptop(laptopWrapper),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<CommonResponse> deleteAllLaptop() {
        try {
            return new ResponseEntity<>(laptopService.deleteAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<CommonResponse> getLaptop(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(laptopService.getLaptop(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/laptops/{id}")
    public ResponseEntity<CommonResponse> updateLaptop(@PathVariable Long id, @RequestBody LaptopWrapper laptopWrapper) {
        try {
            return new ResponseEntity<>(laptopService.updateLaptop(id, laptopWrapper),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<CommonResponse> deleteLaptop(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(laptopService.deleteLaptop(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(setServerError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public CommonResponse setServerError(Exception e){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(500);
        commonResponse.setStatus(ResponseStatus.FAILED);
        commonResponse.setData(e.getMessage());
        commonResponse.setErrorMessage("Something went wrong. Please try again later");
        return commonResponse;
    }
}
package com.deepu.laptopservice.service;

import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import javax.naming.directory.InvalidAttributesException;

public interface LaptopService {
    ResponseEntity<CommonResponse> getAllLaptop(String processor);
    ResponseEntity<CommonResponse> addLaptop(LaptopWrapper laptopWrapper) throws InvalidAttributesException;
    ResponseEntity<CommonResponse> deleteAll();
    ResponseEntity<CommonResponse> getLaptop(Long id);
    ResponseEntity<CommonResponse> updateLaptop(Long id, LaptopWrapper laptopWrapper) throws InvalidAttributesException;
    ResponseEntity<CommonResponse> deleteLaptop(Long id);
}
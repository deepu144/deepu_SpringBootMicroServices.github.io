package com.deepu.laptopservice.service;

import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.response.CommonResponse;
import jakarta.ws.rs.NotFoundException;

import javax.naming.directory.InvalidAttributesException;
public interface LaptopService {
    CommonResponse getAllLaptop(String processor)throws NotFoundException;
    CommonResponse addLaptop(LaptopWrapper laptopWrapper) throws InvalidAttributesException;
    CommonResponse deleteAll();
    CommonResponse getLaptop(Long id) throws NotFoundException;
    CommonResponse updateLaptop(Long id, LaptopWrapper laptopWrapper) throws InvalidAttributesException,NotFoundException;
    CommonResponse deleteLaptop(Long id) throws NotFoundException;
}
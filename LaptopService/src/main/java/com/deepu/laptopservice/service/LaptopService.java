package com.deepu.laptopservice.service;

import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.response.CommonResponse;

import javax.naming.directory.InvalidAttributesException;

public interface LaptopService {
    CommonResponse getAllLaptop(String processor);
    CommonResponse addLaptop(LaptopWrapper laptopWrapper) throws InvalidAttributesException;
    CommonResponse deleteAll();
    CommonResponse getLaptop(Long id);
    CommonResponse updateLaptop(Long id, LaptopWrapper laptopWrapper) throws InvalidAttributesException;
    CommonResponse deleteLaptop(Long id);
}

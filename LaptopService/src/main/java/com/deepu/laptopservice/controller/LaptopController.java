package com.deepu.laptopservice.controller;

import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.enumeration.ResponseStatus;
import com.deepu.laptopservice.response.CommonResponse;
import com.deepu.laptopservice.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @GetMapping("/laptops")
    public CommonResponse getAllLaptop(@RequestParam(required = false) String processor) {
        try {
            return laptopService.getAllLaptop(processor);
        } catch (Exception e) {
            e.printStackTrace();
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }

    @PostMapping
            ("/laptop")
    public CommonResponse addLaptop(@RequestBody LaptopWrapper laptopWrapper) {
        try {
            return laptopService.addLaptop(laptopWrapper);
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }

    @DeleteMapping("/laptops")
    public CommonResponse deleteAllLaptop() {
        try {
            return laptopService.deleteAll();
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }

    @GetMapping("/laptops/{id}")
    public CommonResponse getLaptop(@PathVariable Long id) {
        try {
            return laptopService.getLaptop(id);
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }

    @PutMapping("/laptops/{id}")
    public CommonResponse updateLaptop(@PathVariable Long id, @RequestBody LaptopWrapper laptopWrapper) {
        try {
            return laptopService.updateLaptop(id, laptopWrapper);
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }

    @DeleteMapping("/laptops/{id}")
    public CommonResponse deleteLaptop(@PathVariable Long id) {
        try {
            return laptopService.deleteLaptop(id);
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(500);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setData(e.getMessage());
            commonResponse.setErrorMessage("Something went wrong. Please try again later");
            return commonResponse;
        }
    }
}
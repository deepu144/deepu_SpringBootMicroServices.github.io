package com.deepu.laptopservice.util;

import com.deepu.laptopservice.request.LaptopRequest;
import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.model.Laptop;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public LaptopRequest convertToDto(Laptop laptop, String personName) {
        LaptopRequest laptopRequest = new LaptopRequest();
        laptopRequest.setName(laptop.getName());
        laptopRequest.setProcessor(laptop.getProcessor());
        laptopRequest.setPerson(personName);
        laptopRequest.setLaptopId(laptop.getLaptopId());
        return laptopRequest;
    }
    public Laptop convertToModel(LaptopWrapper laptopWrapper){
        Laptop laptop = new Laptop();
        laptop.setLaptopId(laptopWrapper.getLaptopId());
        laptop.setName(laptopWrapper.getName());
        laptop.setProcessor(laptopWrapper.getProcessor());
        laptop.setPersonId(laptopWrapper.getPersonId());
        return laptop;
    }
}

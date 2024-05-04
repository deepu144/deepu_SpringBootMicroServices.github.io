package com.deepu.laptopservice.util;

import com.deepu.laptopservice.request.LaptopObject;
import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.model.Laptop;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public LaptopObject convertToDto(Laptop laptop, String personName) {
        LaptopObject laptopObject = new LaptopObject();
        laptopObject.setName(laptop.getName());
        laptopObject.setProcessor(laptop.getProcessor());
        laptopObject.setPerson(personName);
        laptopObject.setLaptopId(laptop.getLaptopId());
        return laptopObject;
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
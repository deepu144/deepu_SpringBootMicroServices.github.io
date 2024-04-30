package com.deepu.laptopservice.util;

import com.deepu.laptopservice.dto.LaptopDto;
import com.deepu.laptopservice.model.Laptop;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    public LaptopDto convertToDto(Laptop laptop,String personName) {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setName(laptop.getName());
        laptopDto.setProcessor(laptop.getProcessor());
        laptopDto.setPerson(personName);
        laptopDto.setLaptopId(laptop.getLaptopId());
        return laptopDto;
    }
}

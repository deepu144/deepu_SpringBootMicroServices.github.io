package com.deepu.personservice.util;

import com.deepu.personservice.dto.PersonDto;
import com.deepu.personservice.model.Person;
import org.springframework.stereotype.Component;


@Component
public class DtoMapper {
    public PersonDto convertToDto(Person person) {
    	PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
    	personDto.setName(person.getName());
    	personDto.setAge(person.getAge());
        personDto.setLaptops(person.getLaptops());
        return personDto;
    }
}

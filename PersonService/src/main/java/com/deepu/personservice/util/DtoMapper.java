package com.deepu.personservice.util;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.model.Person;
import org.springframework.stereotype.Component;


@Component
public class DtoMapper {
    public PersonRequest convertToDto(Person person) {
        PersonRequest personRequest = new PersonRequest();
        personRequest.setId(person.getId());
        personRequest.setName(person.getName());
        personRequest.setAge(person.getAge());
        personRequest.setLaptops(person.getLaptops());
        return personRequest;
    }
    public Person convertToModel(PersonRequest personRequest){
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setAge(personRequest.getAge());
        person.setLaptops(personRequest.getLaptops());
        return person;
    }
}

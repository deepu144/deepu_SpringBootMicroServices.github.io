package com.deepu.personservice.service;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.response.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.NotFoundException;

import javax.naming.directory.InvalidAttributesException;

public interface PersonService {
    CommonResponse addPerson(PersonRequest personRequest);
    CommonResponse getAllPerson(Integer age) throws NotFoundException;
    CommonResponse deleteAllPerson();
    CommonResponse getPerson(Long id) throws NotFoundException;
    CommonResponse updatePerson(Long id, PersonRequest personRequest) throws InvalidAttributesException,NotFoundException;
    CommonResponse deletePerson(Long id) throws NotFoundException;
    CommonResponse findAllLaptop(Long id) throws JsonProcessingException,NotFoundException;
}

package com.deepu.personservice.service;

import com.deepu.personservice.exception.CustomException;
import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.response.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.naming.directory.InvalidAttributesException;

public interface PersonService {
    CommonResponse addPerson(PersonRequest personRequest);
    CommonResponse getAllPerson(Integer age);
    CommonResponse deleteAllPerson();
    CommonResponse getPerson(Long id);
    CommonResponse updatePerson(Long id, PersonRequest personRequest) throws InvalidAttributesException, CustomException;
    CommonResponse deletePerson(Long id);
    CommonResponse findAllLaptop(Long id) throws JsonProcessingException;
}

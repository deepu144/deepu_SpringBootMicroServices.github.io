package com.deepu.personservice.service;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import javax.naming.directory.InvalidAttributesException;

public interface PersonService {
    ResponseEntity<CommonResponse> addPerson(PersonRequest personRequest);
    ResponseEntity<CommonResponse> getAllPerson(Integer age);
    ResponseEntity<CommonResponse> deleteAllPerson();
    ResponseEntity<CommonResponse> getPerson(Long id);
    ResponseEntity<CommonResponse> updatePerson(Long id, PersonRequest personRequest) throws InvalidAttributesException;
    ResponseEntity<CommonResponse> deletePerson(Long id);
    ResponseEntity<CommonResponse> findAllLaptop(Long id);
}

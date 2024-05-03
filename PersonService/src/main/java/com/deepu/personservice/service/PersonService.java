package com.deepu.personservice.service;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.response.CommonResponse;

import javax.naming.directory.InvalidAttributesException;

public interface PersonService {
	public CommonResponse addPerson(PersonRequest personRequest);
	public CommonResponse getAllPerson(Integer age);
	public CommonResponse deleteAllPerson();
	public CommonResponse getPerson(Long id);
	public CommonResponse updatePerson(Long id , PersonRequest personRequest) throws InvalidAttributesException;
	public CommonResponse deletePerson(Long id);
	public CommonResponse findAllLaptop(Long id);
}

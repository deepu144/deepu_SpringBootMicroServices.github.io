package com.deepu.personservice.service;

import com.deepu.personservice.dto.LaptopDto;
import com.deepu.personservice.dto.PersonDto;
import com.deepu.personservice.model.Person;
import org.springframework.http.ResponseEntity;
import javax.naming.directory.InvalidAttributesException;
import java.util.List;

public interface PersonService {
	public ResponseEntity<PersonDto> addPerson(Person person);
	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age);
	public ResponseEntity<PersonDto> deleteAllPerson();
	public ResponseEntity<PersonDto> getPerson(Long id);
	public ResponseEntity<PersonDto> updatePerson(Long id , Person person) throws InvalidAttributesException;
	public ResponseEntity<PersonDto> deletePerson(Long id);
	public ResponseEntity<List<LaptopDto>> findAllLaptop(Long id);
}

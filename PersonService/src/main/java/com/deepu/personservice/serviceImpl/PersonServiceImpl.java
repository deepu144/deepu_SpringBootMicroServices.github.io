package com.deepu.personservice.serviceImpl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.naming.directory.InvalidAttributesException;
import com.deepu.personservice.dto.LaptopDto;
import com.deepu.personservice.dto.PersonDto;
import com.deepu.personservice.feign.LaptopClient;
import com.deepu.personservice.model.Person;
import com.deepu.personservice.repository.PersonRepo;
import com.deepu.personservice.service.PersonService;
import com.deepu.personservice.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private DtoMapper dtoMapper;
	@Autowired
	private LaptopClient laptopClient;

	public ResponseEntity<PersonDto> addPerson(Person person) throws InvalidParameterException{
		if(person.getAge()==null || person.getName()==null) {
			throw new InvalidParameterException();
		}
		personRepo.save(person);
		PersonDto personDto = dtoMapper.convertToDto(person);
		return new ResponseEntity<>(personDto, HttpStatus.CREATED);
	}

	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age) {
		if (age != null) {
			List<Person> li = personRepo.findByAge(age);
			if (li.size() > 0) {
				List<PersonDto> pdList = personRepo.findByAge(age).stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
				return new ResponseEntity<>(pdList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			List<PersonDto> pdList = personRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
			return new ResponseEntity<>(pdList, HttpStatus.OK);
		}
	}

	public ResponseEntity<PersonDto> deleteAllPerson() {
		personRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<PersonDto> getPerson(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonDto personDto = dtoMapper.convertToDto(person.get());
			return new ResponseEntity<>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> updatePerson(Long id, Person person) throws InvalidAttributesException {
		if(person.getAge()==null || person.getName()==null) {
			throw new InvalidAttributesException();
		}
		Optional<Person> personTest = personRepo.findById(id);
		if (personTest.isPresent()) {
			Person obj = personRepo.findById(id).get();
			obj.setAge(person.getAge());
			obj.setName(person.getName());
			personRepo.save(obj);
			PersonDto personDto = dtoMapper.convertToDto(obj);
			return new ResponseEntity<>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> deletePerson(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonDto personDto = dtoMapper.convertToDto(person.get());
			personRepo.delete(person.get());
			return new ResponseEntity<>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<LaptopDto>> findAllLaptop(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			List<Long> laptops = person.get().getLaptops();
			List<LaptopDto> laps = laptops.stream().map(n->laptopClient.getLaptop(n).getBody()).collect(Collectors.toList());
			return new ResponseEntity<>(laps,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}

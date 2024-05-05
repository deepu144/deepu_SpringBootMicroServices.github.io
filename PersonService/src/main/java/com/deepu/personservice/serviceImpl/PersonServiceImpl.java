package com.deepu.personservice.serviceImpl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.naming.directory.InvalidAttributesException;
import com.deepu.personservice.request.LaptopWrapper;
import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.enumeration.ResponseStatus;
import com.deepu.personservice.feign.LaptopClient;
import com.deepu.personservice.model.Person;
import com.deepu.personservice.repository.PersonRepo;
import com.deepu.personservice.response.CommonResponse;
import com.deepu.personservice.service.PersonService;
import com.deepu.personservice.util.DtoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private DtoMapper dtoMapper;
	@Autowired
	private LaptopClient laptopClient;

	public CommonResponse addPerson(PersonRequest personRequest) throws InvalidParameterException{
		if(personRequest.getAge()!=null && personRequest.getName()!=null) {
			Person person = dtoMapper.convertToModel(personRequest);
			personRepo.save(person);
			PersonRequest personRequest1 = dtoMapper.convertToDto(person);
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(201);
			commonResponse.setStatus(ResponseStatus.CREATED);
			commonResponse.setData(personRequest1);
			commonResponse.setSuccessMessage("Person Created Successfully");
			return commonResponse;
		}else{
			throw new InvalidParameterException();
		}
	}

	public CommonResponse getAllPerson(Integer age) {
		if (age != null) {
			List<Person> li = personRepo.findByAge(age);
			if (li.size() > 0) {
				List<PersonRequest> pdList = personRepo.findByAge(age).stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
				CommonResponse commonResponse = new CommonResponse();
				commonResponse.setCode(200);
				commonResponse.setStatus(ResponseStatus.SUCCESS);
				commonResponse.setData(pdList);
				commonResponse.setSuccessMessage("Person has been fetched successfully");
				return commonResponse;
			} else {
				CommonResponse commonResponse = new CommonResponse();
				commonResponse.setCode(204);
				commonResponse.setStatus(ResponseStatus.FAILED);
				commonResponse.setErrorMessage("No Person Exists!");
				return commonResponse;
			}
		} else {
			List<PersonRequest> pdList = personRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(200);
			commonResponse.setStatus(ResponseStatus.SUCCESS);
			commonResponse.setData(pdList);
			commonResponse.setSuccessMessage("All Persons has been fetched successfully");
			return commonResponse;
		}
	}
	public CommonResponse deleteAllPerson() {
		personRepo.deleteAll();
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(200);
		commonResponse.setStatus(ResponseStatus.SUCCESS);
		commonResponse.setSuccessMessage("All Person has been Deleted Successfully");
		return commonResponse;
	}
	public CommonResponse getPerson(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonRequest personRequest = dtoMapper.convertToDto(person.get());
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(200);
			commonResponse.setStatus(ResponseStatus.SUCCESS);
			commonResponse.setData(personRequest);
			commonResponse.setSuccessMessage("Person has been fetched Successfully");
			return commonResponse;
		} else {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(204);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setErrorMessage("Person doesn't Exists!");
			return commonResponse;
		}
	}
	public CommonResponse updatePerson(Long id, PersonRequest personRequest) throws InvalidAttributesException {
		if(personRequest.getAge()!=null && personRequest.getName()!=null) {
			Optional<Person> personTest = personRepo.findById(id);
			if (personTest.isPresent()) {
				Person person = personTest.get();
				person.setAge(personRequest.getAge());
				person.setName(personRequest.getName());
				personRepo.save(person);
				PersonRequest personRequest2 = dtoMapper.convertToDto(person);
				CommonResponse commonResponse = new CommonResponse();
				commonResponse.setCode(200);
				commonResponse.setStatus(ResponseStatus.SUCCESS);
				commonResponse.setData(personRequest2);
				commonResponse.setSuccessMessage("Person has been Updated Successfully");
				return commonResponse;
			} else {
				CommonResponse commonResponse = new CommonResponse();
				commonResponse.setCode(204);
				commonResponse.setStatus(ResponseStatus.FAILED);
				commonResponse.setErrorMessage("Person Update Failed!");
				return commonResponse;
			}
		}else{
			throw new InvalidAttributesException();
		}
	}

	public CommonResponse deletePerson(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonRequest personRequest = dtoMapper.convertToDto(person.get());
			personRepo.delete(person.get());
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(200);
			commonResponse.setStatus(ResponseStatus.SUCCESS);
			commonResponse.setData(personRequest);
			commonResponse.setSuccessMessage("Person has been Deleted Successfully!");
			return commonResponse;
		} else {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(204);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setErrorMessage("Failed to Delete Person!");
			return commonResponse;
		}
	}

	public CommonResponse findAllLaptop(Long id) throws JsonProcessingException {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			List<Long> laptops = person.get().getLaptops();
			List<LaptopWrapper> laps = new ArrayList<>();
			for(Long value : laptops){
				LaptopWrapper laptopWrapper = mapper(value);
				laps.add(laptopWrapper);
			}
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(200);
			commonResponse.setStatus(ResponseStatus.SUCCESS);
			commonResponse.setData(laps);
			commonResponse.setSuccessMessage("Person's Laptop has been fetched Successfully");
			return commonResponse;
		} else {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(204);
			commonResponse.setStatus(ResponseStatus.SUCCESS);
			commonResponse.setErrorMessage("Laptop doesn't Exists!");
			return commonResponse;
		}
	}

	private LaptopWrapper mapper(Long n) throws JsonProcessingException {
		CommonResponse response = laptopClient.getLaptop(n).getBody();
		TypeReference<LaptopWrapper> type = new TypeReference<>() {
		};
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(mapper.writeValueAsString(response != null ? response.getData() : null), type);
	}
}

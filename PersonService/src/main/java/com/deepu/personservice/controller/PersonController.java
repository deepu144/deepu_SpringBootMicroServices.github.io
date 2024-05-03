package com.deepu.personservice.controller;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.enumeration.ResponseStatus;
import com.deepu.personservice.response.CommonResponse;
import com.deepu.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@PostMapping("/person")
	public CommonResponse addPerson(@RequestBody PersonRequest personRequest){
		CommonResponse commonResponse = new CommonResponse();
		try {
			commonResponse = personService.addPerson(personRequest);
		} catch (Exception e) {
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
		}
		return commonResponse;
	}
	
	@GetMapping("/persons")
	public CommonResponse getAllPerson(@RequestParam(required = false) Integer age){
		try {
			return personService.getAllPerson(age);			
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
	
	@DeleteMapping("/persons")
	public CommonResponse deleteAllPerson(){
		try {
			return personService.deleteAllPerson();			
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
	
	@GetMapping("/persons/{id}")
	public CommonResponse getPerson(@PathVariable Long id){
		try {
			return personService.getPerson(id);			
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
	
	@PutMapping("/persons/{id}")
	public CommonResponse updatePerson(@PathVariable Long id , @RequestBody PersonRequest personRequest){
		try {
			return personService.updatePerson(id, personRequest);
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
	
	@DeleteMapping("/persons/{id}")
	public CommonResponse deletePerson(@PathVariable Long id){
		try {
			return personService.deletePerson(id);			
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
	
	@GetMapping("/persons/{id}/laptops")
	public CommonResponse findAllLaptop(@PathVariable Long id){
		try {
			return personService.findAllLaptop(id);			
		} catch (Exception e) {
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setCode(500);
			commonResponse.setStatus(ResponseStatus.FAILED);
			commonResponse.setData(e.getMessage());
			commonResponse.setErrorMessage("Something went wrong. Please try again later");
			return commonResponse;
		}
	}
}
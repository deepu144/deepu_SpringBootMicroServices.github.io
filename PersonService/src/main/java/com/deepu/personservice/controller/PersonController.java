package com.deepu.personservice.controller;

import com.deepu.personservice.request.PersonRequest;
import com.deepu.personservice.enumeration.ResponseStatus;
import com.deepu.personservice.response.CommonResponse;
import com.deepu.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<CommonResponse> addPerson(@RequestBody PersonRequest personRequest){
		try {
			return new ResponseEntity<>(personService.addPerson(personRequest),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons")
	public ResponseEntity<CommonResponse> getAllPerson(@RequestParam(required = false) Integer age){
		try {
			return new ResponseEntity<>(personService.getAllPerson(age),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/persons")
	public ResponseEntity<CommonResponse> deleteAllPerson(){
		try {
			return new ResponseEntity<>(personService.deleteAllPerson(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<CommonResponse> getPerson(@PathVariable Long id){
		try {
			return new ResponseEntity<>(personService.getPerson(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<CommonResponse> updatePerson(@PathVariable Long id , @RequestBody PersonRequest personRequest){
		try {
			return new ResponseEntity<>(personService.updatePerson(id, personRequest),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<CommonResponse> deletePerson(@PathVariable Long id){
		try {
			return new ResponseEntity<>(personService.deletePerson(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons/{id}/laptops")
	public ResponseEntity<CommonResponse> findAllLaptop(@PathVariable Long id){
		try {
			return new ResponseEntity<>(personService.findAllLaptop(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(setServerError(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public CommonResponse setServerError(Exception e){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(500);
		commonResponse.setStatus(ResponseStatus.FAILED);
		commonResponse.setData(e.getMessage());
		commonResponse.setErrorMessage("Something went wrong. Please try again later");
		return commonResponse;
	}

}
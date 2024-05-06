package com.deepu.laptopservice.serviceImpl;

import com.deepu.laptopservice.request.LaptopObject;
import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.request.PersonObject;
import com.deepu.laptopservice.enumeration.ResponseStatus;
import com.deepu.laptopservice.feign.PersonClient;
import com.deepu.laptopservice.model.Laptop;
import com.deepu.laptopservice.repository.LaptopRepo;
import com.deepu.laptopservice.response.CommonResponse;
import com.deepu.laptopservice.service.LaptopService;
import com.deepu.laptopservice.util.DtoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private DtoMapper dtoMapper;
    @Autowired
    private PersonClient personClient;
    public CommonResponse getAllLaptop(String processor) throws NotFoundException{
        if (processor == null) {
            List<LaptopObject> li = laptopRepo
                    .findAll()
                    .stream()
                    .map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId())))
                    .collect(Collectors.toList());
            if(li.size()>0){
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setCode(200);
                commonResponse.setStatus(ResponseStatus.SUCCESS);
                commonResponse.setData(li);
                commonResponse.setSuccessMessage("Laptops has been Fetched Successfully");
                return commonResponse;
            }else{
                throw new NotFoundException("Laptop doesn't Exists!");
            }
        } else {
            List<LaptopObject> li = laptopRepo.findByProcessor(processor)
                    .stream()
                    .map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId())))
                    .collect(Collectors.toList());
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(li);
            commonResponse.setSuccessMessage("All Laptops has been Fetched Successfully");
            return commonResponse;
        }
    }
    public CommonResponse addLaptop(LaptopWrapper laptopWrapper) throws InvalidAttributesException {
        if (laptopWrapper.getName() != null && laptopWrapper.getProcessor() != null && laptopWrapper.getLaptopId() != null) {
            Laptop laptop = dtoMapper.convertToModel(laptopWrapper);
            laptopRepo.save(laptop);
            LaptopObject laptopObject = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopObject);
            commonResponse.setSuccessMessage("Laptops has been Added Successfully");
            return commonResponse;
        }else{
            throw new InvalidAttributesException();
        }
    }
    public CommonResponse deleteAll() {
        laptopRepo.deleteAll();
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(204);
        commonResponse.setStatus(ResponseStatus.FAILED);
        commonResponse.setSuccessMessage("Laptops has been Deleted Successfully");
        return commonResponse;
    }
    public CommonResponse getLaptop(Long id) throws NotFoundException {
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopObject laptopObject = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopObject);
            commonResponse.setSuccessMessage("Laptop has been Fetched Successfully");
            return commonResponse;
        } else {
            throw new NotFoundException("Laptop doesn't Exists!");
        }
    }
    public CommonResponse updateLaptop(Long id, LaptopWrapper laptopWrapper) throws InvalidAttributesException,NotFoundException {
        if (laptopWrapper.getName() != null && laptopWrapper.getProcessor() != null) {
            Laptop laptop1 = laptopRepo.findByLaptopId(id);
            if (laptop1 != null) {
                laptop1.setName(laptopWrapper.getName());
                laptop1.setProcessor(laptopWrapper.getProcessor());
                laptopRepo.save(laptop1);
                LaptopObject laptopObject = dtoMapper.convertToDto(laptop1,getPersonName(laptop1.getPersonId()));
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setCode(200);
                commonResponse.setStatus(ResponseStatus.SUCCESS);
                commonResponse.setData(laptopObject);
                commonResponse.setSuccessMessage("Laptop has been Updated Successfully!");
                return commonResponse;
            } else {
                throw new NotFoundException("Laptop doesn't Exists!");
            }
        }else{
            throw new InvalidAttributesException();
        }
    }
    public CommonResponse deleteLaptop(Long id) throws NotFoundException{
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopObject laptopObject = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            laptopRepo.delete(laptop);
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopObject);
            commonResponse.setSuccessMessage("Laptops has been Deleted Successfully");
            return commonResponse;
        } else {
            throw new NotFoundException("Laptop doesn't Exists!");
        }
    }
    public String getPersonName(Long id) {
        if(id==null || id==0){
            return null;
        }
        CommonResponse commonResponse =  personClient.getPerson(id).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<PersonObject> typeReference = new TypeReference<>() {};
        PersonObject personObject = null;
        try {
            personObject = objectMapper.readValue(objectMapper.writeValueAsString(commonResponse != null ? commonResponse.getData() : null),typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return personObject != null ? personObject.getName() : "";
    }
}
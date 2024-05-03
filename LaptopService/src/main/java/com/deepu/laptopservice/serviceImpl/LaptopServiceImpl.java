package com.deepu.laptopservice.serviceImpl;

import com.deepu.laptopservice.request.LaptopRequest;
import com.deepu.laptopservice.request.LaptopWrapper;
import com.deepu.laptopservice.request.PersonWrapper;
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
    public CommonResponse getAllLaptop(String processor) {
        if (processor == null) {
            List<LaptopRequest> li = laptopRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId()))).collect(Collectors.toList());
            if(li.size()>0){
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setCode(200);
                commonResponse.setStatus(ResponseStatus.SUCCESS);
                commonResponse.setData(li);
                commonResponse.setSuccessMessage("Laptops has been Fetched Successfully");
                return commonResponse;
            }else{
                CommonResponse commonResponse = new CommonResponse();
                commonResponse.setCode(204);
                commonResponse.setStatus(ResponseStatus.FAILED);
                commonResponse.setErrorMessage("Laptop doesn't Exists!");
                return commonResponse;
            }
        } else {
            List<LaptopRequest> li = laptopRepo.findByProcessor(processor).stream().map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId())))
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
        if (laptopWrapper.getName() == null || laptopWrapper.getProcessor() == null || laptopWrapper.getLaptopId()==null) {
            throw new InvalidAttributesException();
        }
        Laptop laptop = dtoMapper.convertToModel(laptopWrapper);
        laptopRepo.save(laptop);
        LaptopRequest laptopRequest = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(200);
        commonResponse.setStatus(ResponseStatus.SUCCESS);
        commonResponse.setData(laptopRequest);
        commonResponse.setSuccessMessage("Laptops has been Added Successfully");
        return commonResponse;
    }
    public CommonResponse deleteAll() {
        laptopRepo.deleteAll();
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(204);
        commonResponse.setStatus(ResponseStatus.FAILED);
        commonResponse.setSuccessMessage("Laptops has been Deleted Successfully");
        return commonResponse;
    }
    public CommonResponse getLaptop(Long id) {
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopRequest laptopRequest = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopRequest);
            commonResponse.setSuccessMessage("Laptop has been Fetched Successfully");
            return commonResponse;
        } else {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(204);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setErrorMessage("Laptop doesn't Exists!");
            return commonResponse;
        }
    }
    public CommonResponse updateLaptop(Long id, LaptopWrapper laptopWrapper) throws InvalidAttributesException {
        if (laptopWrapper.getName() == null || laptopWrapper.getProcessor() == null) {
            throw new InvalidAttributesException();
        }
        Laptop laptop1 = laptopRepo.findByLaptopId(id);
        if (laptop1 != null) {
            laptop1.setName(laptopWrapper.getName());
            laptop1.setProcessor(laptopWrapper.getProcessor());
            laptopRepo.save(laptop1);
            LaptopRequest laptopRequest = dtoMapper.convertToDto(laptop1,getPersonName(laptop1.getPersonId()));
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopRequest);
            commonResponse.setSuccessMessage("Laptop has been Updated Successfully!");
            return commonResponse;
        } else {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(204);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setErrorMessage("Laptop doesn't Exists!");
            return commonResponse;
        }
    }
    public CommonResponse deleteLaptop(Long id) {
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopRequest laptopRequest = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            laptopRepo.delete(laptop);
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(200);
            commonResponse.setStatus(ResponseStatus.SUCCESS);
            commonResponse.setData(laptopRequest);
            commonResponse.setSuccessMessage("Laptops has been Deleted Successfully");
            return commonResponse;
        } else {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(204);
            commonResponse.setStatus(ResponseStatus.FAILED);
            commonResponse.setSuccessMessage("Laptop doesn't Exists!");
            return commonResponse;
        }
    }
    public String getPersonName(Long id) {
        if(id==null || id==0){
            return null;
        }
        CommonResponse commonResponse =  personClient.getPerson(id);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<PersonWrapper> typeReference = new TypeReference<>() {};
        PersonWrapper personWrapper = null;
        try {
            personWrapper = objectMapper.readValue(objectMapper.writeValueAsString(commonResponse.getData()),typeReference);
        } catch (JsonProcessingException e) {}
        return personWrapper != null ? personWrapper.getName() : "";
    }
}
package com.deepu.laptopservice.serviceImpl;

import com.deepu.laptopservice.dto.LaptopDto;
import com.deepu.laptopservice.dto.PersonDto;
import com.deepu.laptopservice.feign.PersonClient;
import com.deepu.laptopservice.model.Laptop;
import com.deepu.laptopservice.repository.LaptopRepo;
import com.deepu.laptopservice.service.LaptopService;
import com.deepu.laptopservice.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<LaptopDto>> getAllLaptop(String processor) {
        if (processor == null) {
            List<LaptopDto> li = laptopRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId()))).collect(Collectors.toList());
            return li.size() > 0 ? new ResponseEntity<>(li, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<LaptopDto> li = laptopRepo.findByProcessor(processor).stream().map(n -> dtoMapper.convertToDto(n,getPersonName(n.getPersonId())))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(li, HttpStatus.OK);
        }
    }

    public ResponseEntity<LaptopDto> addLaptop(Laptop laptop) throws InvalidAttributesException {
        if (laptop.getName() == null || laptop.getProcessor() == null || laptop.getLaptopId()==null) {
            throw new InvalidAttributesException();
        }
        laptopRepo.save(laptop);
        LaptopDto laptopDto = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
        return new ResponseEntity<>(laptopDto,HttpStatus.OK);
    }

    public ResponseEntity<LaptopDto> deleteAll() {
        laptopRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<LaptopDto> getLaptop(Long id) {
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopDto laptopDto = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            System.out.println("................ "+laptopDto+" ....................");
            return new ResponseEntity<>(laptopDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<LaptopDto> updateLaptop(Long id, Laptop laptop) throws InvalidAttributesException {
        if (laptop.getName() == null || laptop.getProcessor() == null) {
            throw new InvalidAttributesException();
        }
        Laptop laptop1 = laptopRepo.findByLaptopId(id);
        if (laptop1 != null) {
            laptop1.setName(laptop.getName());
            laptop1.setProcessor(laptop.getProcessor());
            laptopRepo.save(laptop1);
            LaptopDto laptopDto = dtoMapper.convertToDto(laptop1,getPersonName(laptop1.getPersonId()));
            return new ResponseEntity<>(laptopDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<LaptopDto> deleteLaptop(Long id) {
        Laptop laptop = laptopRepo.findByLaptopId(id);
        if (laptop != null) {
            LaptopDto laptopDto = dtoMapper.convertToDto(laptop,getPersonName(laptop.getPersonId()));
            laptopRepo.delete(laptop);
            return new ResponseEntity<>(laptopDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String getPersonName(Long id){
        PersonDto person =  personClient.getPerson(id).getBody();
        return person != null ? person.getName() : "";
    }
}

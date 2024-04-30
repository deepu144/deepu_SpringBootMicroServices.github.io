package com.deepu.laptopservice.feign;

import com.deepu.laptopservice.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PERSONSERVICE")
public interface PersonClient {

    @GetMapping("/api/persons/{id}")
    ResponseEntity<PersonDto> getPerson(@PathVariable Long id);

}

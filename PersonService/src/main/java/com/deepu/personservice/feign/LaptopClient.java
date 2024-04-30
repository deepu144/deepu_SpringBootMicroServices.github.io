package com.deepu.personservice.feign;

import com.deepu.personservice.dto.LaptopDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("LAPTOPSERVICE")
public interface LaptopClient {
    @GetMapping("/api/laptops/{id}")
    ResponseEntity<LaptopDto> getLaptop(@PathVariable Long id);

}

package com.deepu.laptopservice.feign;

import com.deepu.laptopservice.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PERSON-SERVICE")
public interface PersonClient {
    @GetMapping("/api/persons/{id}")
    ResponseEntity<CommonResponse> getPerson(@PathVariable Long id);
}
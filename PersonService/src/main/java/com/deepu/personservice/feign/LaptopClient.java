package com.deepu.personservice.feign;

import com.deepu.personservice.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("LAPTOP-SERVICE")
public interface LaptopClient {
    @GetMapping("/api/laptops/{id}")
    CommonResponse getLaptop(@PathVariable Long id);

}

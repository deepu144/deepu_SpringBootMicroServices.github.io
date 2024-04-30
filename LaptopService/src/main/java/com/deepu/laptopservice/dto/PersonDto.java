package com.deepu.laptopservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private Long id;
    private String name;
    private Integer age;
    @JsonIgnore
    private List<String> laptops;
}

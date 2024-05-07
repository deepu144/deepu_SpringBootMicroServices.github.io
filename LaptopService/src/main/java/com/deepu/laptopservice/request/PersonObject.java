package com.deepu.laptopservice.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PersonObject {
    private Long id;
    private String name;
    private Integer age;
    @JsonIgnore
    private List<String> laptops;
}
package com.deepu.laptopservice.request;

import lombok.Data;

@Data
public class LaptopObject {
    private Long laptopId;
    private String name;
    private String processor;
    private String person;
}
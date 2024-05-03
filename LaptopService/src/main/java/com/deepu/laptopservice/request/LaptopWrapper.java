package com.deepu.laptopservice.request;

import lombok.Data;

@Data
public class LaptopWrapper {
    private Long laptopId;
    private String name;
    private String processor;
    private Long personId;
}

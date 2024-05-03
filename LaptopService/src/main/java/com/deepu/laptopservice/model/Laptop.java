package com.deepu.laptopservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "laptop")
public class Laptop {
    @Id
    private String id;
    @Indexed(unique = true)
    private Long laptopId;
    private String name;
    private String processor;
    private Long personId;
}
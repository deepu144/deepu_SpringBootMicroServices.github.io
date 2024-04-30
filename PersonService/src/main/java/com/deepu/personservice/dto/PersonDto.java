package com.deepu.personservice.dto;

import java.util.List;
import lombok.Data;
@Data
public class PersonDto {
	private Long id;
	private String name;
	private Integer age;
	private List<Long> laptops;
}

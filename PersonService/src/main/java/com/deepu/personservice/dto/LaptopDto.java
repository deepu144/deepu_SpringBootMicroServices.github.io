package com.deepu.personservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class LaptopDto {
	private Long laptopId;
	private String name;
	private String processor;
	private String person;
}

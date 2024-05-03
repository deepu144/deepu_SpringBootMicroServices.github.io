package com.deepu.personservice.request;

import java.util.List;
import lombok.Data;
@Data
public class PersonRequest {
	private Long id;
	private String name;
	private Integer age;
	private List<Long> laptops;
}

package com.deepu.laptopservice.response;

import com.deepu.laptopservice.enumeration.ResponseStatus;
import lombok.Data;
@Data
public class CommonResponse {
    private ResponseStatus status;
    private String errorMessage;
    private String successMessage;
    private Object data;
    private int code;
}
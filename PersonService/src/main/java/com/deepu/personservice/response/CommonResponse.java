package com.deepu.personservice.response;

import com.deepu.personservice.enumeration.ResponseStatus;
import lombok.Data;
@Data
public class CommonResponse {
    private ResponseStatus status;
    private String errorMessage;
    private String successMessage;
    private Object data;
    private int code;
}

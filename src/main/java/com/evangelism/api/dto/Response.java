package com.evangelism.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int errorCode;
    private boolean success;
    private String message;
    private Object data;
}

package com.evangelism.api.converter;

import com.evangelism.api.dto.response.Response;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter implements Converter<Object, Response> {
    @Override
    public Response convert(Object source) {
        return Response.builder()
                .errorCode(0)
                .success(true)
                .message("Success operation")
                .data(source)
                .build();
    }
}

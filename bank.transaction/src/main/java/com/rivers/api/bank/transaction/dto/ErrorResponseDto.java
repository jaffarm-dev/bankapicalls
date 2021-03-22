package com.rivers.api.bank.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto implements ResponseDto{

    @JsonProperty("message")
    private String message;

}

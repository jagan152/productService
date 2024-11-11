package dev.jagan.productserviceproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String errorMessage;

    public ErrorDto(String errorMessage){
        this.errorMessage = errorMessage;
    }

}

package com.oznur.finalcase.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
public class GenericErrorMessage {

    private LocalDateTime errorDate;
    private String message;
    private String detail;
}

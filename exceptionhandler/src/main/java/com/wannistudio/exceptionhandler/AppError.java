package com.wannistudio.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppError {
    private String message;
    private String reason;
}

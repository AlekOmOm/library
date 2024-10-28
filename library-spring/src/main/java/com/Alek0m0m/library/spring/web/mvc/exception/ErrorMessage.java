package com.Alek0m0m.library.spring.web.mvc.exception;

import java.util.Date;

public class ErrorMessage extends BaseErrorMessage {

        public ErrorMessage(int statusCode, Date timeStamp, String message, String description) {
            super(statusCode,timeStamp, message, description);
        }
}

package com.mycs.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiErrorResponse {
    //http status code
    private HttpStatus status;

    // customer error message to the client API
    private String message;

    // Time of the error.make sure to define a standard time zone to avoid any confusion.
    private LocalDateTime timeStamp;

    // getter and setters
    //Builder
    public static final class ApiErrorResponseBuilder {
        private HttpStatus status;
        private String message;
        private LocalDateTime timeStamp;

        public ApiErrorResponseBuilder() {}

        public ApiErrorResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorResponseBuilder atTime(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.message = this.message;
            apiErrorResponse.timeStamp = this.timeStamp;
            return apiErrorResponse;
        }
    }
}

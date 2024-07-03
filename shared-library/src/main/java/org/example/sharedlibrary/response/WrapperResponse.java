package org.example.sharedlibrary.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class WrapperResponse {

    private boolean isSuccessful;
    private String message;
    private int status;
    private Object data;

    public WrapperResponse() {
    }

    public WrapperResponse success(String message, Object data) {
        return new WrapperResponse(true, message
                , HttpStatus.OK.value(), data);
    }
    public WrapperResponse fail(String message, HttpStatus status) {
        return new WrapperResponse(false, message
                , status.value(), null);
    }
}

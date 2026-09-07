package com.example.tienda.exceotion;

import java.util.List;

public class ErrorResponseArray {

    private List<ResponseError> responseErrors;

    public ErrorResponseArray(List<ResponseError> err) {
        this.responseErrors = err;
    }

    public List<ResponseError> getResponseErrors() {
        return responseErrors;
    }

    public void setResponseErrors(List<ResponseError> responseErrors) {
        this.responseErrors = responseErrors;
    }

}

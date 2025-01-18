package com.tanvir.dems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceAlreadyExistsException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found %s: %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }


    public ResourceAlreadyExistsException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found %s: %d", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    public ResourceAlreadyExistsException(){
    }
}

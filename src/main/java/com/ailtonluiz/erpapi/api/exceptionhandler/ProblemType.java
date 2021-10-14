package com.ailtonluiz.erpapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/invalid-data","Invalid data"),
    ERROR_SYSTEM("/error-system","System error"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message","Incomprehensible message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    ERROR_TRANSACTION("/error-transaction","Business Rule Violation"),
    MAX_FILE_SIZE_EXCEEDED("/max-file-size","File size exceeded the maximum limit");


    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https:/fooddelivery.com" + path;
        this.title = title;
    }
}

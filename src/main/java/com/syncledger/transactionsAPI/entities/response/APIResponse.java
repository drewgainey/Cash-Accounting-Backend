package com.syncledger.transactionsAPI.entities.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse<T> {
    private String status;
    private String message;
    private T data;
    private Object metadata;

    public APIResponse(String status, String message, T data, Object metadata) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
    }
}

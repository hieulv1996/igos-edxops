package com.fsoft.igos.exception;

public enum ErrorCode {
    UNAUTHORIZED_ERROR(1, "Unauthorized"),
    BAD_REQUEST_ERROR(2, "Bad Request"),
    INTERNAL_SERVER_ERROR(3, "Internal Server Error");

    /**
     * The field to get errorCode value from enum
     */
    private int errorCode;
    /**
     * The field to get message value from enum
     */
    private String message;

    /**
     * @param errorCode
     * @param message
     */
    private ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}

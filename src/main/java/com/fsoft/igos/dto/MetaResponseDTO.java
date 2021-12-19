package com.fsoft.igos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsoft.igos.exception.ErrorCode;
import com.fsoft.igos.utils.ValidateUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;


@JsonInclude(Include.NON_NULL)
public abstract class MetaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1116091598299674086L;

    @JsonProperty(value = "errorCode")
    private Integer mErrorCode;

    @JsonProperty(value = "message")
    private String mMessage;

    @JsonIgnore
    private HttpStatus mHttpStatus = HttpStatus.OK;

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return mErrorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        mErrorCode = errorCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return mMessage;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        mMessage = message;
    }

    /**
     * @return the httpStatus
     */
    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return mHttpStatus;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        mHttpStatus = httpStatus;
        setError(HttpStatus.BAD_REQUEST == httpStatus ? ErrorCode.BAD_REQUEST_ERROR :
                HttpStatus.INTERNAL_SERVER_ERROR == httpStatus ? ErrorCode.INTERNAL_SERVER_ERROR :
                        null);
    }

    /**
     * Custom error code, to help response many difference error.
     *
     * @param httpStatus http status
     * @param errorCode  error detail
     */
    public void setHttpStatus(HttpStatus httpStatus, ErrorCode errorCode) {
        mHttpStatus = httpStatus;
        setError(errorCode);
    }

    /**
     * Update errorCode and message based on errorCode obj
     *
     * @param errorCode
     */
    public void setError(ErrorCode errorCode) {
        if (errorCode != null) {
            mErrorCode = errorCode.getErrorCode();
            mMessage = errorCode.getMessage();
        }
    }

    /**
     * Determine the response is error or not. The response is error if the errorId and message are
     * available
     *
     * @return
     */
    public boolean hasError() {
        return mErrorCode != null && mErrorCode > 0 && ValidateUtil.isNotNullOrEmpty(mMessage);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        // Never use instanceof or it will break subclasses' equals method
        if (obj != null && obj.getClass() != getClass()) return false;

        MetaResponseDTO res = (MetaResponseDTO) obj;

        return Objects.equals(mErrorCode, res.mErrorCode)
                && Objects.equals(mHttpStatus, res.mHttpStatus)
                && Objects.equals(mMessage, res.mMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mErrorCode, mHttpStatus, mHttpStatus);
    }

}

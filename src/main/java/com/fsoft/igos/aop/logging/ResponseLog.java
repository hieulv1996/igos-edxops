package com.fsoft.igos.aop.logging;


import java.util.Map;

public class ResponseLog {

    private int mHttpStatus;
    private long mResponseTime;
    private Map<String, String> mHeaders;
    private Object mBody;

    /**
     * @return the httpStatus
     */
    public int getHttpStatus() {
        return mHttpStatus;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(int httpStatus) {
        mHttpStatus = httpStatus;
    }

    /**
     * @return the responseTime
     */
    public long getResponseTime() {
        return mResponseTime;
    }

    /**
     * @param responseTime the responseTime to set
     */
    public void setResponseTime(long responseTime) {
        mResponseTime = responseTime;
    }

    /**
     * @return the headers
     */
    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    /**
     * @return the body
     */
    public Object getBody() {
        return mBody;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
        mBody = body;
    }
}

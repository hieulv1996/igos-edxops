package com.fsoft.igos.aop.logging;


import com.fsoft.igos.utils.HttpRequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {
    /**
     * logger
     */
    protected static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Create ascending number for requestId
        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        // Wrap the request and response to able to get body twice
        RequestWrapper requestWrapper = new RequestWrapper(requestId, (HttpServletRequest) request);
        ResponseWrapper responseWrapper = new ResponseWrapper(requestId, (HttpServletResponse) response);
        String requestUri = requestWrapper.getRequestURI();
        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } catch (Throwable e) {
            logger.error("Exception", e);
        } finally {
            // In case the request for getting status, login, ignore it.
            boolean showRequestBody = true;
            if (requestUri.contains("/status") || requestUri.contains("login")) {
                // Ignore these request
            } else {
                logRequestInfo(requestWrapper, showRequestBody);
                logResponseInfo(responseWrapper, System.currentTimeMillis() - startTime);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * Log the request info
     *
     * @param request
     */
    private void logRequestInfo(final HttpServletRequest request, final boolean showRequestBody) {
        StringBuilder msg = new StringBuilder();

        msg.append("\n====== Request Begin========\n");
        msg.append("IP         : ").append(request.getRemoteAddr()).append("\n");

        if (request instanceof RequestWrapper) {
            msg.append("RequestID  : ").append(((RequestWrapper) request).getId()).append("\n");
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            msg.append("SessionId  : ").append(session.getId()).append("\n");
        }
        if (request.getMethod() != null) {
            msg.append("Method     : ").append(request.getMethod()).append("\n");
        }
        if (request.getRequestURI() != null) {
            msg.append("URI        : ").append(request.getRequestURI()).append("\n");
        }
        if (request.getQueryString() != null) {
            msg.append("Query      : ").append(request.getQueryString()).append("\n");
        }
        String headersStr = HttpRequestUtils.getHeadersAsStringFromRequest(request);

        if (!StringUtils.isBlank(headersStr)) {
            msg.append("Headers    : ").append(headersStr).append("\n");
        }
        if (showRequestBody && request instanceof RequestWrapper && !isMultipart(request)
                && !isBinaryContent(request)) {
            String requestBody = getRequestBody(request);
            if (StringUtils.isNotBlank(requestBody)) {
                msg.append("Body       : ").append(requestBody).append("\n");
            }
        }
        msg.append("====== Request End==========");
        logger.info(msg.toString());
    }

    /**
     * Do log the response info
     *
     * @param request
     * @return
     */
    private boolean isBinaryContent(final HttpServletRequest request) {
        if (request.getContentType() == null) {
            return false;
        }
        return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
                || request.getContentType().startsWith("audio");
    }

    /**
     * check the content type is multipart
     *
     * @param request
     * @return
     */
    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }

    /**
     * Do log the response info
     *
     * @param response
     */
    private void logResponseInfo(final ResponseWrapper response, long processTimeInMillies) {
        StringBuilder msg = new StringBuilder();

        msg.append("\n====== Response Begin========\n");
        msg.append("RequestID  : ").append((response.getId())).append("\n");
        msg.append("Status     : ").append(response.getStatus()).append("\n");
        msg.append("ProcessedTime     : ").append(processTimeInMillies).append("\n");

        String headersStr = getHeadersAsStringFromResponse(response);
        if (!StringUtils.isBlank(headersStr)) {
            msg.append("Headers    :").append(headersStr).append("\n");
        }
        String responseBody = getResponseBody(response);
        if (StringUtils.isNotBlank(responseBody)) {
            msg.append("Body       : ").append(responseBody).append("\n");
        }
        msg.append("====== Response End==========\n");
        logger.info(msg.toString());
    }

    /**
     * Get the request body from ServletRequest
     *
     * @param request
     * @return
     */
    private String getRequestBody(HttpServletRequest request) {
        String body = null;
        RequestWrapper requestWrapper = (RequestWrapper) request;
        try {
            String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding()
                    : "UTF-8";
            body = new String(requestWrapper.toByteArray(), charEncoding);
        } catch (UnsupportedEncodingException e) {
            logger.warn("Failed to parse request payload", e);
        }
        return body;
    }

    /**
     * Get the response body from ServletResponse
     *
     * @param response
     * @return
     */
    private String getResponseBody(final ResponseWrapper response) {
        String body = null;
        try {
            body = new String(new String(response.toByteArray(), response.getCharacterEncoding()));
        } catch (UnsupportedEncodingException e) {
            logger.warn("Failed to parse response payload", e);
        }
        return body;
    }

    /**
     * Get the response headers as string
     *
     * @param response
     * @return
     */
    private String getHeadersAsStringFromResponse(ResponseWrapper response) {
        StringBuilder headersStr = new StringBuilder();
        Collection<String> headerNames = response.getHeaderNames();
        if (headerNames != null) {
            headersStr.append("[");
            for (Iterator<String> i = headerNames.iterator(); i.hasNext(); ) {
                String headerName = i.next();
                headersStr.append(headerName).append(": ");
                headersStr.append(response.getHeader(headerName));
                if (i.hasNext()) {
                    headersStr.append(", ");
                }
            }
            headersStr.append("]");
        }
        return headersStr.toString();
    }
}
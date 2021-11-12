package com.fsoft.igos.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class HttpRequestUtils {

    /**
     * Get the request headers as string
     *
     * @param request
     * @return
     */
    public static String getHeadersAsStringFromRequest(HttpServletRequest request) {
        StringBuilder headersStr = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            headersStr.append("[");
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headersStr.append(headerName).append(": ");
                headersStr.append(request.getHeader(headerName));
                if (headerNames.hasMoreElements()) {
                    headersStr.append(", ");
                }
            }
            headersStr.append("]");
        }
        return headersStr.toString();
    }

}

package com.devdenise.devdenisebe.utils;

import org.springframework.http.HttpHeaders;

public class ControllerUtil {

    public static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        // headers.setAccessControlAllowOrigin("https://devdenise.com");
        return headers;
    }
}

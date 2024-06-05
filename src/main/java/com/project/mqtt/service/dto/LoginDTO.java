package com.project.mqtt.service.dto;

import org.springframework.http.HttpHeaders;

import java.util.List;

public class LoginDTO {
    private HttpHeaders httpHeaders;
    private UserDTO customUserDetails;

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public UserDTO getCustomUserDetails() {
        return customUserDetails;
    }

    public void setCustomUserDetails(UserDTO customUserDetails) {
        this.customUserDetails = customUserDetails;
    }
}

package com.Import_Service.Import_Service.request;

import java.util.Map;

public class EditAPISourceRequest {
    private String name;

    private String url;

    private String method;

    private String authorization;

    private Map<String, String> parameters;

    public EditAPISourceRequest() {

    }

    public EditAPISourceRequest(String name, String url, String method, String authorization, Map<String, String> parameters) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.authorization = authorization;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
package com.hooray.common.utils.api;

public class ApiPushResult {
    private String requestId;
    private APIResult result;
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public APIResult getResult() {
        return result;
    }

    public void setResult(APIResult result) {
        this.result = result;
    }
}

package com.example.sanphamdemo;

public class ConfirmationRequest {
    private String message;
    private String requestId;
    private boolean isAccepted;

    public ConfirmationRequest(String requestId, boolean isAccepted) {
        this.requestId = requestId;
        this.isAccepted = isAccepted;
    }
}

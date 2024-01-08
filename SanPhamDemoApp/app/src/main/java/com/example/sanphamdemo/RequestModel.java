package com.example.sanphamdemo;

public class  RequestModel {
    private String requestId;
    private String message;
    private boolean isConfirmed;



    public RequestModel(String requestId, String message, boolean isConfirmed) {
        this.requestId = requestId;
        this.message = message;
        this.isConfirmed = isConfirmed;

    }



    public String getRequestId() {
        return requestId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }


}

package com.example.sanphamdemo;

public class ServerResponse {
    private String message;
    private String requestId;  // Giả sử có một thuộc tính requestId

    private String trangthai;



    // Constructor, getter và setter

    public String getTrangthai() {
        return trangthai;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getMessage() {
        return message;
    }
}
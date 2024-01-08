package com.example.sanphamdemo.interfacehoadon;

import com.example.sanphamdemo.RequestModel;
import com.example.sanphamdemo.user.Ban_User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_getlistYeuCau {
    @GET("api/getListyeucau")
    Call<List<RequestModel>> getListYeuCau();
}

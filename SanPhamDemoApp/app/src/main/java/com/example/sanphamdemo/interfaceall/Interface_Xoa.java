package com.example.sanphamdemo.interfaceall;

import com.example.sanphamdemo.user.DeleteBan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface_Xoa {
    @FormUrlEncoded
    @POST("/api/delitemban/:id")
    Call<DeleteBan> DelListProduct1(@Field("id")int id);
}

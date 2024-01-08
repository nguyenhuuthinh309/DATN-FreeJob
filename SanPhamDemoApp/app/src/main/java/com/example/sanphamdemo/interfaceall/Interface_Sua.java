package com.example.sanphamdemo.interfaceall;

import com.example.sanphamdemo.user.UpdateBan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Interface_Sua {
    @FormUrlEncoded
    @POST("api/updateitemban/:id")
    Call<UpdateBan> updateBan(@Field("id") int id, @Field("ten") String ten, @Field("trangthai") int trangthai );





}

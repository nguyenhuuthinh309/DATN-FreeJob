package com.example.sanphamdemo.interfaceall;

import com.example.sanphamdemo.ServerResponse;
import com.example.sanphamdemo.user.DeleteBan;
import com.example.sanphamdemo.user.Delete_YeuCau;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface_XoaYeuCau {
    @FormUrlEncoded
    @POST("api/delitemyeucau/:id")
    Call<Delete_YeuCau> deleteYeucau(@Field("id")String id);
}

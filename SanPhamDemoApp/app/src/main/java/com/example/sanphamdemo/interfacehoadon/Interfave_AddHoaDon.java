package com.example.sanphamdemo.interfacehoadon;

import com.example.sanphamdemo.ConfirmationRequest;
import com.example.sanphamdemo.RequestModel;
import com.example.sanphamdemo.ServerResponse;
import com.example.sanphamdemo.userhoadon.AddHoaDon;
import com.example.sanphamdemo.userhoadon.HoaDon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interfave_AddHoaDon {


    @POST("confirmRequest")
    Call<RequestModel> confirmRequest(@Body ConfirmationRequest confirmationRequest);
    @FormUrlEncoded
    @POST("api/addhoadon")
    Call<AddHoaDon> addHoaDon(@Field("idUngVien") int idUngVien, @Field("idCongTy") int idCongTy,
                           @Field("ngay") String ngay, @Field("noidung") String noidung, @Field("kihan") String kihan,@Field("trangthai") String trangthai,@Field("thanhtien") String thanhtien);
}

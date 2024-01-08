package com.example.sanphamdemo.interfaceall;

import com.example.sanphamdemo.user.Ban_User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Interface_ListBan {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    Interface_ListBan apiselectpeoduct1 = new Retrofit.Builder().baseUrl("http://192.168.1.2:3000/").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(Interface_ListBan.class);

    @GET("api/getlistban")
    Call<List<Ban_User>> getListProduct1();
}

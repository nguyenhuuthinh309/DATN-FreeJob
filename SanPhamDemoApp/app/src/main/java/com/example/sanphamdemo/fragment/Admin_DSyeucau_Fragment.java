package com.example.sanphamdemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sanphamdemo.DataListenerThongBao;
import com.example.sanphamdemo.R;
import com.example.sanphamdemo.RequestAdapter;
import com.example.sanphamdemo.RequestModel;
import com.example.sanphamdemo.ThongTinHoaDon;
import com.example.sanphamdemo.adapter.MyAdapter;
import com.example.sanphamdemo.interfacehoadon.Interface_getlistYeuCau;
import com.example.sanphamdemo.interfacehoadon.Interfave_AddHoaDon;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.ThongBao;
import com.example.sanphamdemo.userhoadon.AddHoaDon;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Admin_DSyeucau_Fragment extends Fragment  {


    Interfave_AddHoaDon apiService;
    Button button;
    RequestAdapter adapter1;

    ArrayList<RequestModel> requestList = new ArrayList<>();


     RecyclerView recyclerView;

    private ArrayList<RequestModel> arrayList = new ArrayList<>();



    public static Admin_DSyeucau_Fragment newInstance() {
        Admin_DSyeucau_Fragment fragment = new Admin_DSyeucau_Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin__d_syeucau_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.button);
        recyclerView = view.findViewById(R.id.rec);


        adapter1 = new RequestAdapter(getContext());
        adapter1.setData(arrayList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager2);
        recyclerView.setAdapter(adapter1);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        Interface_getlistYeuCau interfaceDelete = retrofit.create(Interface_getlistYeuCau.class);
        Call<List<RequestModel>> call = interfaceDelete.getListYeuCau();
        call.enqueue(new Callback<List<RequestModel>>() {
            @Override
            public void onResponse(Call<List<RequestModel>> call, Response<List<RequestModel>> response) {
                if (response.isSuccessful()) {

                    // Xử lý dữ liệu khi nhận được
                    List<RequestModel> responseData = response.body();

                    // Cập nhật dataList và thông báo thay đổi trong Adapter
                    arrayList.addAll(responseData);
                    adapter1.notifyDataSetChanged();





                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(getContext(), "add thất bại: " + errorBody, Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RequestModel>> call, Throwable t) {
                Toast.makeText(getContext(), " thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("zzzzzzzz",t.getMessage());
            }
        });




    }



}
package com.example.sanphamdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanphamdemo.activity.Test;
import com.example.sanphamdemo.fragment.Admin_DSyeucau_Fragment;
import com.example.sanphamdemo.interfaceall.Interface_Sua;
import com.example.sanphamdemo.interfacehoadon.Interface_AddYeucau;
import com.example.sanphamdemo.interfacehoadon.Interfave_AddHoaDon;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.UpdateBan;
import com.example.sanphamdemo.userhoadon.AddHoaDon;
import com.example.sanphamdemo.userhoadon.HoaDon;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThongTinHoaDon extends AppCompatActivity {

    private ImageView imageView;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView dathanhtoan;
    private RequestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_hoa_don);

        RecyclerView recyclerView = findViewById(R.id.rec);


        imageView = (ImageView) findViewById(R.id.imageView);
        dathanhtoan = (TextView) findViewById(R.id.dathanhtoan);




        imageView = (ImageView) findViewById(R.id.imageView);
        dathanhtoan = (TextView) findViewById(R.id.dathanhtoan);

        b = (TextView) findViewById(R.id.b);
        c = (TextView) findViewById(R.id.c);
        d = (TextView) findViewById(R.id.d);
        e = (TextView) findViewById(R.id.e);
        f = (TextView) findViewById(R.id.f);



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
     //   adapter.setdata(requestList);
        recyclerView.setAdapter(adapter);


        Bundle bundle = getIntent().getExtras();
        String msthue = bundle.getString("masothue");
        String tenct = bundle.getString("tenct");
        String diachi = bundle.getString("diachi");
        String linhvuc = bundle.getString("linhvuc");
        String kihan = bundle.getString("kihan");

             b.setText(tenct);
             c.setText(diachi);
             d.setText(linhvuc);
             e.setText(kihan);


        if (kihan == "") {

            Toast.makeText(this, "0 thang", Toast.LENGTH_SHORT).show();
        } else if (kihan.equals("1 Tháng")) {
            int tien = 85000;
            Toast.makeText(this, "1 thang", Toast.LENGTH_SHORT).show();
            String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%201 thang";
            Picasso.get().load(imageUrl).into(imageView);
        }else if (kihan.equals("3 Tháng")) {
            int tien = 150000;
            Toast.makeText(this, "3 thang", Toast.LENGTH_SHORT).show();
            String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%203 thang";
            Picasso.get().load(imageUrl).into(imageView);

        } else if (kihan.equals("6 Tháng")) {
            int tien = 220000;
            Toast.makeText(this, "6 thang", Toast.LENGTH_SHORT).show();
            String imageUrl = "https://api.vietqr.io/image/970422-999993092003-mpJvWLi.jpg?accountName=NGUYEN%20HUU%20THINH&amount="+tien+"&addInfo=Thanh%20toan%206 thang";
            Picasso.get().load(imageUrl).into(imageView);

        }else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

        dathanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                String msthue = bundle.getString("masothue");
                String requestId = msthue ;
                String trangthai = "0";
                            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();

            Interface_AddYeucau interfaceDelete = retrofit.create(Interface_AddYeucau.class);
                Call<ServerResponse> call = interfaceDelete.addYeuCau(requestId,trangthai);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response.isSuccessful()) {
                            ServerResponse svrResponseDelete = response.body();
                            Toast.makeText(ThongTinHoaDon.this, "add thành công 123 " + svrResponseDelete.getMessage(), Toast.LENGTH_SHORT).show();


                            Bundle bundle = getIntent().getExtras();
                            String msthue = bundle.getString("masothue");
                            String tenct = bundle.getString("tenct");
                            String diachi = bundle.getString("diachi");
                            String linhvuc = bundle.getString("linhvuc");
                            String kihan = bundle.getString("kihan");


                            Intent intent = new Intent();
                            intent.putExtra("msthue", msthue);
                            intent.putExtra("tenct", tenct);
                            intent.putExtra("diachi", diachi);
                            intent.putExtra("linhvuc", linhvuc);
                            intent.putExtra("kihan", kihan);

                            setResult(RESULT_OK, intent);
                            finish();

                            finish();
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Toast.makeText(ThongTinHoaDon.this, "add thất bại: " + errorBody, Toast.LENGTH_SHORT).show();
                                finish();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        showToast("Lỗi kết nối đến server.");
                    }
                });
            }
        });

//        dathanhtoan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HoaDon newUser = new HoaDon();
//                Bundle bundle = getIntent().getExtras();
//                String msthue = bundle.getString("masothue");
//                String tenct = bundle.getString("tenct");
//                String diachi = bundle.getString("diachi");
//                String linhvuc = bundle.getString("linhvuc");
//                String kihan = bundle.getString("kihan");
//        if (kihan.equals("1 Tháng")){
//            newUser.setIdUngVien(1);
//            newUser.setIdCongTy(Integer.parseInt(msthue));
//            newUser.setNgay("2222");
//            newUser.setNoidung("1 thang");
//            newUser.setKihan(kihan);
//            newUser.setTrangthai("cho xac nhan");
//            newUser.setThanhtien("80.000 Đ");
//// Kiểm tra giá trị của newUser
//            //   Log.d("UserInfo", "Tên: " + newUser.getTen() + ", Giá: " + newUser.getGia());
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.168.1.2:3000/")
//                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
//                    .build();
//
//            Interfave_AddHoaDon interfaceDelete = retrofit.create(Interfave_AddHoaDon.class);
//            Call<AddHoaDon> call = interfaceDelete.addHoaDon(newUser.getIdUngVien(),newUser.getIdCongTy(),newUser.getNgay(),newUser.getNoidung(),newUser.getKihan(),newUser.getTrangthai(),newUser.getThanhtien());
//
//            call.enqueue(new Callback<AddHoaDon>() {
//                @Override
//                public void onResponse(Call<AddHoaDon> call, Response<AddHoaDon> response) {
//                    if (response.isSuccessful()) {
//
//                        Toast.makeText(ThongTinHoaDon.this, "yêu cầu của bạn đã được gửi tới admin", Toast.LENGTH_SHORT).show();
//
//
//
//                    } else {
//                        try {
//                            String errorBody = response.errorBody().string();
//                            Toast.makeText(ThongTinHoaDon.this, "add thất bại: " + errorBody, Toast.LENGTH_SHORT).show();
//                            finish();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//
//                @Override
//                public void onFailure(Call<AddHoaDon> call, Throwable t) {
//                    Toast.makeText(ThongTinHoaDon.this, " thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("zzzzzzzz",t.getMessage());
//                    finish();
//                }
//            });
//        }else          if (kihan.equals("3 Tháng")){
//            newUser.setIdUngVien(1);
//            newUser.setIdCongTy(Integer.parseInt(msthue));
//            newUser.setNgay("2222");
//            newUser.setNoidung("3 thang");
//            newUser.setKihan(kihan);
//            newUser.setTrangthai("cho xac nhan");
//            newUser.setThanhtien("150.000 Đ");
//// Kiểm tra giá trị của newUser
//            //   Log.d("UserInfo", "Tên: " + newUser.getTen() + ", Giá: " + newUser.getGia());
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.168.1.2:3000/")
//                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
//                    .build();
//
//            Interfave_AddHoaDon interfaceDelete = retrofit.create(Interfave_AddHoaDon.class);
//            Call<AddHoaDon> call = interfaceDelete.addHoaDon(newUser.getIdUngVien(),newUser.getIdCongTy(),newUser.getNgay(),newUser.getNoidung(),newUser.getKihan(),newUser.getTrangthai(),newUser.getThanhtien());
//
//            call.enqueue(new Callback<AddHoaDon>() {
//                @Override
//                public void onResponse(Call<AddHoaDon> call, Response<AddHoaDon> response) {
//                    if (response.isSuccessful()) {
//                        AddHoaDon svrResponseDelete = response.body();
//                        Toast.makeText(ThongTinHoaDon.this, "add thành công " + svrResponseDelete.getMessage(), Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        try {
//                            String errorBody = response.errorBody().string();
//                            Toast.makeText(ThongTinHoaDon.this, "add thất bại: " + errorBody, Toast.LENGTH_SHORT).show();
//                            finish();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//
//                @Override
//                public void onFailure(Call<AddHoaDon> call, Throwable t) {
//                    Toast.makeText(ThongTinHoaDon.this, " thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("zzzzzzzz",t.getMessage());
//                    finish();
//                }
//            });
//        }else         if (kihan.equals("6 Tháng")){
//            newUser.setIdUngVien(1);
//            newUser.setIdCongTy(Integer.parseInt(msthue));
//            newUser.setNgay("2222");
//            newUser.setNoidung("6 thang");
//            newUser.setKihan(kihan);
//            newUser.setTrangthai("cho xac nhan");
//            newUser.setThanhtien("200.000 Đ");
//// Kiểm tra giá trị của newUser
//            //   Log.d("UserInfo", "Tên: " + newUser.getTen() + ", Giá: " + newUser.getGia());
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.168.1.2:3000/")
//                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
//                    .build();
//
//            Interfave_AddHoaDon interfaceDelete = retrofit.create(Interfave_AddHoaDon.class);
//            Call<AddHoaDon> call = interfaceDelete.addHoaDon(newUser.getIdUngVien(),newUser.getIdCongTy(),newUser.getNgay(),newUser.getNoidung(),newUser.getKihan(),newUser.getTrangthai(),newUser.getThanhtien());
//
//            call.enqueue(new Callback<AddHoaDon>() {
//                @Override
//                public void onResponse(Call<AddHoaDon> call, Response<AddHoaDon> response) {
//                    if (response.isSuccessful()) {
//                        AddHoaDon svrResponseDelete = response.body();
//                        Toast.makeText(ThongTinHoaDon.this, "add thành công " + svrResponseDelete.getMessage(), Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        try {
//                            String errorBody = response.errorBody().string();
//                            Toast.makeText(ThongTinHoaDon.this, "add thất bại: " + errorBody, Toast.LENGTH_SHORT).show();
//                            finish();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//
//                @Override
//                public void onFailure(Call<AddHoaDon> call, Throwable t) {
//                    Toast.makeText(ThongTinHoaDon.this, " thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("zzzzzzzz",t.getMessage());
//                    finish();
//                }
//            });
//        }
//
//
//                dathanhtoan.setText("Chờ Xác Nhận");
//
//
//            }
//        });

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public static String generateRandomRequestId() {
        // Sử dụng UUID để tạo requestId ngẫu nhiên
        UUID uuid = UUID.randomUUID();

        // Chuyển UUID thành chuỗi và loại bỏ dấu gạch ngang
        String requestId = uuid.toString().replace("-", "");

        // Đảm bảo requestId có độ dài mong muốn, ví dụ: 10 ký tự
        requestId = requestId.substring(0, 10);

        return requestId;
    }
}
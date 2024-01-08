package com.example.sanphamdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanphamdemo.user.DeleteBan;
import com.example.sanphamdemo.R;
import com.example.sanphamdemo.adapter.AdapterMonan;
import com.example.sanphamdemo.interfaceall.InteLoadData;
import com.example.sanphamdemo.interfaceall.Interface_Xoa;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.UserItem;
import com.example.sanphamdemo.user.UserMonAn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThucDonBan extends AppCompatActivity {

    TextView tvKQ;
    String kq = "";//chuoi ket qua
    Context context = this;
    String strKQ = "";
    private List<UserItem> productList;
    private List<Ban_User> dataList;

    TextView back;
    TextView tenban;
    Spinner caidatban1;
    ListView lv;
    Button addmonan;
    int nhanidxoa, nhangia, nhantrangthai;
    String nhanten, nhangiovao, nhanngaythang;

    Ban_User prd = new Ban_User();
    InteLoadData inteloadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ban);

        addmonan = findViewById(R.id.btn_addmonan);
        addmonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThucDonBan.this, BangMenu.class));
            }
        });
        back = (TextView) findViewById(R.id.back);
        tenban = (TextView) findViewById(R.id.tenban1);
        caidatban1 = (Spinner) findViewById(R.id.caidatbanspinner);
        lv = (ListView) findViewById(R.id.lv);


        Bundle bundle = getIntent().getExtras();
        String ten = bundle.getString("ten");
        String trangthai = bundle.getString("trangthai");
        nhanidxoa = Integer.parseInt(bundle.getString("idxoa"));
        tenban.setText(ten);


        AdapterMonan myAdapter = new AdapterMonan(ThucDonBan.this, getListData(), R.layout.item_thucdon);
        lv.setAdapter(myAdapter);


// Định nghĩa mảng giá trị cho Spinner
        String[] items = {"Cập Nhật", "Xóa", "Sửa"};

// Tạo ArrayAdapter để kết nối mảng giá trị với Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ThucDonBan.this, android.R.layout.simple_spinner_item, items);

// Thiết lập giao diện cho danh sách thả xuống
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// Đặt ArrayAdapter cho Spinner
        caidatban1.setAdapter(adapter);

        caidatban1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id1) {
                // Xử lý khi một mục được chọn
                String selectedValue = items[position];

                if (selectedValue == "Cập Nhật") {
                    Toast.makeText(getApplicationContext(), "Cập Nhật", Toast.LENGTH_SHORT).show();


                } else if (selectedValue == "Xóa") {
                    Toast.makeText(getApplicationContext(), selectedValue + " Thành Công", Toast.LENGTH_SHORT).show();
                    fetchData_xoa();
                    finish();
                } else if (selectedValue == "Sửa") {

                    Intent intent = new Intent(context, Test.class);
                    Bundle bundle = new Bundle();



                    bundle.putString("idxoa", String.valueOf(nhanidxoa));
                    bundle.putString("ten", ten);
                    bundle.putString("trangthai", trangthai);



                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có mục nào được chọn
            }
        });

    }

//    private void fetchData_sua() {
//
//        Dialog dialogEdit = new Dialog(ThucDonBan.this);
//        dialogEdit.setContentView(R.layout.dialogupdate);
//
//
//        EditText updatehoten = (EditText) dialogEdit.findViewById(R.id.updatehoten);
//
//
//        EditText updatetrangthai = (EditText) dialogEdit.findViewById(R.id.updatetrangthai);
//        Button btneditHV = (Button) dialogEdit.findViewById(R.id.btnedit_HV);
//        Button btnHuyEditHV = (Button) dialogEdit.findViewById(R.id.btnHuyEditHV);
//
//
//   //     updatehoten.setText(nhanten);
//    //    updatetrangthai.setText(nhantrangthai);
//        btneditHV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////
////                prd.setTen(updatehoten.getText().toString());
////                prd.setTrangthai(Integer.valueOf(updatetrangthai.getText().toString()));
////
////
////                //////////////////////////////////////////////////
////                // Tạo Retrofit instance
////                Retrofit retrofit = new Retrofit.Builder()
////                        .baseUrl("http://192.168.1.2:3000/")
////                        .addConverterFactory(GsonConverterFactory.create())
////                        .build();
////
////                Interface_Sua interfaceupdate = retrofit.create(Interface_Sua.class);
////                Call<DeleteBan> call = interfaceupdate.UpdateBan(prd.getTen(),prd.getTrangthai(),prd.getId());
////
////                call.enqueue(new Callback<DeleteBan>()
////
////                {
////                    @Override
////                    public void onResponse (Call < DeleteBan > call, Response < DeleteBan > response){
////                        DeleteBan svrResponseUpdate = response.body();
////                        inteloadData.loadData();
////                        Toast.makeText(context, "Đã sửa thành công!!!" + svrResponseUpdate.getMessage(), Toast.LENGTH_SHORT).show();
////
////
////                    }
////
////
////                    @Override
////                    public void onFailure (Call < DeleteBan > call, Throwable t){
////                        Toast.makeText(context, "xóa thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
////                    }
////                });
//
//                dialogEdit.dismiss();
//            }
//        });
//        btnHuyEditHV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialogEdit.dismiss();
//            }
//        });
//        dialogEdit.show();
//    }




    private void fetchData_xoa() {
        // Tạo Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface_Xoa interfaceDelete = retrofit.create(Interface_Xoa.class);
        Call<DeleteBan> call = interfaceDelete.DelListProduct1(nhanidxoa);

        call.enqueue(new Callback<DeleteBan>() {
            @Override
            public void onResponse(Call<DeleteBan> call, Response<DeleteBan> response) {
                DeleteBan svrResponseDelete = response.body(); // lay kq tu serrverr
                Toast.makeText(context, "xóa thành công " + svrResponseDelete.getMessage(), Toast.LENGTH_SHORT).show();
                // inteloadData.loadData();

            }


            @Override
            public void onFailure(Call<DeleteBan> call, Throwable t) {
                Toast.makeText(context, "xóa thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public ArrayList<UserMonAn> getListData() {
        ArrayList<UserMonAn> list = new ArrayList<>();
        list.add(new UserMonAn("thịt", "333", "12"));

        return list;
    }


}
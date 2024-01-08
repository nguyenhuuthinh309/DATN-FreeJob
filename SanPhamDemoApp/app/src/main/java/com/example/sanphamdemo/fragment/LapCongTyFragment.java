package com.example.sanphamdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.ThongTinHoaDon;
import com.example.sanphamdemo.activity.ThucDonBan;
import com.example.sanphamdemo.adapter.AdapterMonan;
import com.example.sanphamdemo.user.UserMonAn;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LapCongTyFragment extends Fragment {
    String selectedValue;
    public LapCongTyFragment() {
        // Required empty public constructor
    }

    public static LapCongTyFragment newInstance() {
        LapCongTyFragment fragment = new LapCongTyFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lapcongty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText   masothue = (TextInputEditText) view.findViewById(R.id.masothue);

        TextInputEditText   tenct = (TextInputEditText) view.findViewById(R.id.tenct);

        TextInputEditText   diachi = (TextInputEditText)  view.findViewById(R.id.diachi);

        TextInputEditText     edtLinhVuc = (TextInputEditText)  view.findViewById(R.id.edtLinhVuc);

     Spinner   caidatbanspinner = (Spinner)  view.findViewById(R.id.caidatbanspinner);

    TextView btnCreateCompany = (TextView)  view.findViewById(R.id.btn_create_company);

    ImageView    btnBack = (ImageView)  view.findViewById(R.id.btnBack);



        // Định nghĩa mảng giá trị cho Spinner
        String[] items = {"", "1 Tháng", "3 Tháng","6 Tháng"};

// Tạo ArrayAdapter để kết nối mảng giá trị với Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);

// Thiết lập giao diện cho danh sách thả xuống
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// Đặt ArrayAdapter cho Spinner
        caidatbanspinner.setAdapter(adapter);
        btnCreateCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        caidatbanspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id1) {
                // Xử lý khi một mục được chọn
                 selectedValue = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có mục nào được chọn
            }
        });

         btnCreateCompany.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(getContext(), ThongTinHoaDon.class);
                 Bundle bundle = new Bundle();
                 String masothuegui = masothue.getText().toString();
                 String tengui = tenct.getText().toString();
                 String diachigui = diachi.getText().toString();
                 String linhvucgui = edtLinhVuc.getText().toString();
                 String kihangui = selectedValue;




                 bundle.putString("masothue",masothuegui);
                 bundle.putString("tenct", tengui);
                 bundle.putString("diachi", diachigui);
                 bundle.putString("linhvuc", linhvucgui);
                 bundle.putString("kihan", kihangui);

                 intent.putExtras(bundle);
                 startActivity(intent);



             }
         });


    }


}
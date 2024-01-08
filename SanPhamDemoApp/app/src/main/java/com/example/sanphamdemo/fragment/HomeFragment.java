package com.example.sanphamdemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.adapter.AdapterItem;

import com.example.sanphamdemo.adapter.Adapter_of_ViewPage;
import com.example.sanphamdemo.adapter.MyAdapter;
import com.example.sanphamdemo.interfaceall.Interface_ListBan;
import com.example.sanphamdemo.user.Ban_User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    LinearLayout linearLayout6;
    ViewPager2 viewPagerSlide;
    ImageView textView18;
    ImageView textView19;
    LinearLayout linearMore;
    RelativeLayout relativeCty;
    ImageView imgCty;
    RelativeLayout relativeViecLam;
    ImageView imgViecLam;
    RelativeLayout relativeLuong;
    ImageView imgLuong;
    TextView tvTitleViecTot;
    RecyclerView recyclerView_ViecTot;


    TextView tvTitleViecGoiY;
    RecyclerView recyclerView_ViecGoiY;
    RecyclerView.LayoutManager layoutManager_ViecGoiY;
    RecyclerView.LayoutManager layoutManager;
   // AdapterItem adapter1;
    private GridView gridView;
    private ArrayList<Ban_User> arrayList = new ArrayList<>();
     MyAdapter adapter1;



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.linearLayout6);
        viewPagerSlide = (ViewPager2) view.findViewById(R.id.viewPagerSlide);
        textView18 = (ImageView) view.findViewById(R.id.textView18);
        textView19 = (ImageView) view.findViewById(R.id.textView19);
        linearMore = (LinearLayout) view.findViewById(R.id.linearMore);
        relativeCty = (RelativeLayout) view.findViewById(R.id.relative_Cty);
        imgCty = (ImageView) view.findViewById(R.id.img_cty);
        relativeViecLam = (RelativeLayout) view.findViewById(R.id.relative_ViecLam);
        imgViecLam = (ImageView) view.findViewById(R.id.img_viecLam);
        relativeLuong = (RelativeLayout) view.findViewById(R.id.relative_Luong);
        imgLuong = (ImageView) view.findViewById(R.id.img_luong);
        tvTitleViecTot = (TextView) view.findViewById(R.id.tvTitle_viecTot);
        recyclerView_ViecTot = (RecyclerView) view.findViewById(R.id.recyclerView_viecTot);



        adapter1 = new MyAdapter(getActivity());
       adapter1.setData(arrayList);
       LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView_ViecTot.setLayoutManager(linearLayoutManager2);
        recyclerView_ViecTot.setAdapter(adapter1);






        setupSlider();
     loadbaidang();

    }

    private void loadbaidang() {
        // Tạo Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/") // Điền địa chỉ cơ sở của API của bạn
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo instance của ApiService từ Retrofit
        Interface_ListBan apiService = retrofit.create(Interface_ListBan.class);

        // Gọi API để lấy dữ liệu
        Call<List<Ban_User>> call = apiService.getListProduct1();
        call.enqueue(new Callback<List<Ban_User>>() {
            @Override
            public void onResponse(Call<List<Ban_User>> call, Response<List<Ban_User>> response) {
                // Xử lý dữ liệu khi nhận được
                List<Ban_User> responseData = response.body();

                // Cập nhật dataList và thông báo thay đổi trong Adapter
                arrayList.addAll(responseData);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ban_User>> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }

    private void setupSlider() {
        List<Integer> imageList = Arrays.asList(
                R.drawable.card_image_1,
                R.drawable.card_image_2,
                R.drawable.card_image_3,
                R.drawable.card_image_4
        );
    }
}
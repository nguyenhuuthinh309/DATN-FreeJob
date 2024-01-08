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
import android.widget.LinearLayout;

import com.example.sanphamdemo.AdapterThongbao;
import com.example.sanphamdemo.DataListenerThongBao;
import com.example.sanphamdemo.R;
import com.example.sanphamdemo.RequestAdapter;
import com.example.sanphamdemo.adapter.MyAdapter;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.ThongBao;

import java.util.ArrayList;
import java.util.List;


public class ThongBaoFragment extends Fragment implements DataListenerThongBao {
    private LinearLayout detailItem;
    private RecyclerView recthongbao;

    ArrayList<ThongBao> list = new ArrayList<>();


RequestAdapter adapter1;
    private ArrayList<ThongBao> arrayList = new ArrayList<>();

    private List<ThongBao> fragmentData;


    public void setFragmentData(List<ThongBao> fragmentData) {
        this.fragmentData = fragmentData;
        // Cập nhật giao diện người dùng nếu cần
    }
    public ThongBaoFragment() {
        // Required empty public constructor
        this.fragmentData = new ArrayList<>();
    }

    public static ThongBaoFragment newInstance() {
        ThongBaoFragment fragment = new ThongBaoFragment();


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
        return inflater.inflate(R.layout.fragment_thong_bao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailItem = (LinearLayout) view.findViewById(R.id.detail_item);
        recthongbao = (RecyclerView) view.findViewById(R.id.recthongbao);

// Tạo danh sách các đối tượng Item (có thể lấy từ API, database, hoặc mock data)
        ArrayList<ThongBao> itemList = new ArrayList<>();
        itemList.add(new ThongBao("Thông Báo","Công Ty của bạn đã được phê duyệt"));


// Khởi tạo Adapter và chuyển DataListener (tức là chính Fragment này)









        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recthongbao.setLayoutManager(linearLayoutManager2);

        recthongbao.setAdapter(adapter1);



    }
    @Override
    public void onDataReceived(ThongBao dataList) {
        String itemName = dataList.getThongbao();

        Log.d("Fragment", "Received item name: " + itemName);
    }
}
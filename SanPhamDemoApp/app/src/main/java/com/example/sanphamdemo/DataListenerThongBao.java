package com.example.sanphamdemo;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sanphamdemo.user.ThongBao;

import java.util.List;

// Interface
public interface DataListenerThongBao {
    void onDataReceived(ThongBao dataList);
}

// Trong Adapter


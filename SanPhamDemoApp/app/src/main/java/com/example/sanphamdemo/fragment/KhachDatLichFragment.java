package com.example.sanphamdemo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanphamdemo.R;

public class KhachDatLichFragment extends Fragment {



    public KhachDatLichFragment() {
        // Required empty public constructor
    }


    public static KhachDatLichFragment newInstance() {
        KhachDatLichFragment fragment = new KhachDatLichFragment();
        Bundle args = new Bundle();

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
        return inflater.inflate(R.layout.fragment_khach_dat_lich, container, false);
    }
}
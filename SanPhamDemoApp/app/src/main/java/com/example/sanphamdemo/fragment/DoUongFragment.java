package com.example.sanphamdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sanphamdemo.R;

public class DoUongFragment extends Fragment {



    public DoUongFragment() {
        // Required empty public constructor
    }


    public static DoUongFragment newInstance() {
        DoUongFragment fragment = new DoUongFragment();
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
package com.example.sanphamdemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.adapter.Adapter_of_ViewPage;
import com.example.sanphamdemo.adapter.Adapter_of_menu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MenuFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private Adapter_of_menu adapter_of_viewPage;

    public MenuFragment() {
        // Required empty public constructor
    }


    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
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
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.id_tablayout);
        viewPager2 = view.findViewById(R.id.id_ViewPager);
        adapter_of_viewPage = new Adapter_of_menu(getActivity());
        viewPager2.setAdapter(adapter_of_viewPage);


        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Đồ Ăn");
                        break;
                    case 1:
                        tab.setText("Đồ Uống");
                        break;

                }
            }
        });
        mediator.attach();
    }
}
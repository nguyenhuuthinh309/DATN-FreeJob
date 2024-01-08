package com.example.sanphamdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.fragment.HomeFragment;
import com.example.sanphamdemo.fragment.MenuFragment;

public class BangMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_menu);
        replaceFragment(MenuFragment.newInstance());
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontend,fragment);
        transaction.commit();
    }
}
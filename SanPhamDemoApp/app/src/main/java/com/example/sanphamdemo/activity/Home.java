package com.example.sanphamdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.fragment.Admin_DSyeucau_Fragment;
import com.example.sanphamdemo.fragment.HomeFragment;
import com.example.sanphamdemo.fragment.LapCongTyFragment;
import com.example.sanphamdemo.fragment.ThongBaoFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private static final String TAG = "YourActivity";

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar); // add toolbar vào ứng dụng
        frameLayout = (FrameLayout) findViewById(R.id.id_layoutcontend);
        drawerLayout = findViewById(R.id.id_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,mToolbar,0,0);
        toggle.syncState();// tạo nút 3 gạch trên actionbar
        navigationView = (NavigationView) findViewById(R.id.id_navView);
        navigationView.setNavigationItemSelectedListener(this);
        //frameLayout = findViewById(R.id.id_layoutcontend);
        Log.d(TAG, "onCreate");
        replaceFragment(HomeFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.homeFragment){

            replaceFragment(HomeFragment.newInstance());

        }
        if(id == R.id.lapct){

            replaceFragment(LapCongTyFragment.newInstance());

        }
        if(id == R.id.admin_danhsachyeucau){

            replaceFragment(Admin_DSyeucau_Fragment.newInstance());

        }      if(id == R.id.thongbao){

            replaceFragment(ThongBaoFragment.newInstance());

        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontend,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id  == R.id.tiengviet){
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
            replaceFragment(HomeFragment.newInstance());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        replaceFragment(HomeFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");

    }


}
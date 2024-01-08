package com.example.sanphamdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.interfaceall.InteLoadData;
import com.example.sanphamdemo.interfaceall.Interface_Sua;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.UpdateBan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Test extends AppCompatActivity {
    private TextView textView7;
    private LinearLayout linearLayout;
    private EditText updatehoten;
    private TextView textView2;
    private EditText updatetrangthai;
    private Button btneditHV;
    private Button btnHuyEditHV;
    InteLoadData inteloadData;
    Ban_User prd = new Ban_User();
    private static final String BASE_URL = "http://192.168.1.2:3000/";
    int nhanidxoa;
    private Retrofit retrofit;
    private Interface_Sua apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView7 = (TextView) findViewById(R.id.textView7);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        updatehoten = (EditText) findViewById(R.id.updatehoten);
        textView2 = (TextView) findViewById(R.id.textView2);
        updatetrangthai = (EditText) findViewById(R.id.updatetrangthai);
        btneditHV = (Button) findViewById(R.id.cbtnedit_HV);
        btnHuyEditHV = (Button) findViewById(R.id.btnHuyEditHV);
        // Địa chỉ URL của API
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("idxoa");
        String ten = bundle.getString("ten");
        String trangthai = bundle.getString("trangthai");


//
             updatehoten.setText(ten);
             updatetrangthai.setText(trangthai);

        String updateten = updatehoten.getText().toString();
        int trangthai1 = Integer.parseInt(updatetrangthai.getText().toString());

        btneditHV.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                prd.setTen(updateten);
                prd.setTrangthai(trangthai1);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.2:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Interface_Sua interfaceUpdate = retrofit.create(Interface_Sua.class);
                Call<UpdateBan> call = interfaceUpdate.updateBan(333,"thinh123444",12344);

                call.enqueue(new Callback<UpdateBan>() {
                    @Override
                    public void onResponse(Call<UpdateBan> call, Response<UpdateBan> response) {
                        UpdateBan svrResponseUpdate = response.body();

                        Toast.makeText(Test.this, "Đã sửa thành công!!!"+svrResponseUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UpdateBan> call, Throwable t) {

                        Toast.makeText(Test.this, "Đã lỗi sửa!!!"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

}
}




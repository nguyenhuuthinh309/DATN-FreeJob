package com.example.sanphamdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.activity.ThucDonBan;
import com.example.sanphamdemo.user.Ban_User;

import java.util.List;

public class AdapterItem extends BaseAdapter {

    private List<Ban_User> dataList;
    private LayoutInflater inflater;

  Context context;
    Ban_User data;
    public AdapterItem(Context context, List<Ban_User> dataList) {
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public AdapterItem() {
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        ///////////
        if (view == null) {
            view = inflater.inflate(R.layout.itemhomefragment, null);
            holder = new ViewHolder();
            holder.detail_item = view.findViewById(R.id.detail_item);
            holder.tenbann = view.findViewById(R.id.tenban);
            holder.gioo = view.findViewById(R.id.gio);
            holder.giatien = view.findViewById(R.id.gia);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
         data = dataList.get(i);
        holder.tenbann.setText(data.getTen());
        holder.giatien.setText(data.getGia()+" Đ");
        holder.gioo.setText(data.getGiovao());
        if (data.getTrangthai() == 1){
            holder.tenbann.setTextColor(Color.parseColor("#00FF00"));


        }
//        holder.detail_item.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Dialog dialogEdit = new Dialog(context);
//                dialogEdit.setContentView(R.layout.dialogupdate);
//
//
//                ;
//
//                TextView     textView7 = (TextView) dialogEdit.findViewById(R.id.textView7);
//                LinearLayout   linearLayout = (LinearLayout)  dialogEdit.findViewById(R.id.linearLayout);
//                EditText updatehoten = (EditText)  dialogEdit.findViewById(R.id.updatehoten);
//                TextView   textView2 = (TextView)  dialogEdit.findViewById(R.id.textView2);
//                EditText   updatetrangthai = (EditText)  dialogEdit.findViewById(R.id.updatetrangthai);
//                Button btneditHV = (Button)  dialogEdit.findViewById(R.id.btnedit_HV);
//                Button   btnHuyEditHV = (Button)  dialogEdit.findViewById(R.id.btnHuyEditHV);
//
//
//
//
//
////                updatehoten.setText(data.getTen());
////                updatetrangthai.setText(data.getTrangthai());
//
//                String tenn = updatehoten.getText().toString();
//                String tt = updatetrangthai.getText().toString();
//                btneditHV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//
//                        data.setTen(tenn);
//
//                        data.setTrangthai(122);
//
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl("http://192.168.1.2:3000/")
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//                        Interface_Sua interfaceUpdate = retrofit.create(Interface_Sua.class);
//                        Call<DeleteBan> call = interfaceUpdate.updateBan(1008,"thinhupdate",13);
//
//                        call.enqueue(new Callback<DeleteBan>() {
//                            @Override
//                            public void onResponse(Call<DeleteBan> call, Response<DeleteBan> response) {
//                                DeleteBan svrResponseUpdate = response.body();
//
//                                Toast.makeText(context, "Đã sửa thành công!!!"+svrResponseUpdate.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<DeleteBan> call, Throwable t) {
//
//                                Toast.makeText(context, "Đã lỗi sửa!!!"+t.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                        dialogEdit.dismiss();
//                    }
//                });
//                btnHuyEditHV.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogEdit.dismiss();
//                    }
//                });
//                dialogEdit.show();
//                Toast.makeText(context, "ccc", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
        holder.detail_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThucDonBan.class);
                Bundle bundle = new Bundle();
                int idxoa = dataList.get(i).getId();
                 String ten  =dataList.get(i).getTen();
                String giovao = dataList.get(i).getGiovao();
                int gia = dataList.get(i).getGia();
                int trangthai = dataList.get(i).getTrangthai();
                String ngaythang  =dataList.get(i).getNgaythang();


                bundle.putString("idxoa", String.valueOf(idxoa));
                bundle.putString("ten", ten);
                bundle.putString("giovao", giovao);
                bundle.putString("gia", String.valueOf(gia));
                bundle.putString("trangthai", String.valueOf(trangthai));
                bundle.putString("ngaythang", ngaythang);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
        ////////


    }

    public static class ViewHolder  {

              LinearLayout detail_item;
         TextView tenbann, gioo, giatien;
    }
}

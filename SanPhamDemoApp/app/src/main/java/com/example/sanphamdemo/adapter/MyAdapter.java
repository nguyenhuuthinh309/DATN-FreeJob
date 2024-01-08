package com.example.sanphamdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanphamdemo.R;
import com.example.sanphamdemo.user.Ban_User;

import java.util.ArrayList;
import java.util.List;

//////////////////////////

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHoler>{
    private Context context;
    private ArrayList<Ban_User> arrayList;

    public MyAdapter(Context context) {

        this.context = context;
    }

    public void setData(ArrayList<Ban_User> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhomefragment,parent,false);
        return new UserViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoler holder, int position) {
        Ban_User item = arrayList.get(position);
        if(item == null) {
            return;
        }else {
            holder.tenbann.setText(item.getTen().toString());
            holder.gioo.setText(item.getGiovao().toString());
            holder.giatien.setText(item.getGia().toString());




        }

    }

    @Override
    public int getItemCount() {
        if(arrayList!=null)
            return  arrayList.size();
        return 0;
    }

    public class UserViewHoler extends RecyclerView.ViewHolder {

        LinearLayout detail_item;
        TextView tenbann, gioo, giatien;


        public UserViewHoler(@NonNull View itemView) {
            super(itemView);

            detail_item =itemView.findViewById(R.id.detail_item);
            tenbann = itemView.findViewById(R.id.tenban);
            gioo = itemView.findViewById(R.id.gio);
            giatien = itemView.findViewById(R.id.gia);

        }
    }
}

//////////////////////////////



package com.example.sanphamdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanphamdemo.fragment.ThongBaoFragment;
import com.example.sanphamdemo.interfaceall.Interface_Xoa;
import com.example.sanphamdemo.interfaceall.Interface_XoaYeuCau;
import com.example.sanphamdemo.interfacehoadon.Interfave_AddHoaDon;
import com.example.sanphamdemo.user.Ban_User;
import com.example.sanphamdemo.user.DeleteBan;
import com.example.sanphamdemo.user.Delete_YeuCau;
import com.example.sanphamdemo.user.ThongBao;
import com.example.sanphamdemo.userhoadon.AddHoaDon;
import com.example.sanphamdemo.userhoadon.HoaDon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {


    Interfave_AddHoaDon apiService;

     Context context;
    ThongBao currentItem;
    private ArrayList<RequestModel> arrayList;

    public void setData(ArrayList<RequestModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged(); // Cập nhật RecyclerView khi dữ liệu thay đổi
    }
    public RequestAdapter(Context context) {

        this.context = context;
    }

    private List<ThongBao> datathongbao;


    public RequestAdapter(List<ThongBao> datathongbao) {
        this.datathongbao = datathongbao;
    }

    public void setDataList(ArrayList<ThongBao> datathongbao) {
        this.datathongbao = datathongbao;
        notifyDataSetChanged(); // Cập nhật RecyclerView khi dữ liệu thay đổi
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RequestModel request = arrayList.get(position);
        holder.messageTextView.setText("Mã Số Thuế : "+request.getRequestId());
        int red = ContextCompat.getColor(context, R.color.red);
        int green = ContextCompat.getColor(context, R.color.green);
        // Xác nhận yêu cầu khi nhấn nút
        holder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Yêu cầu của id "+request.getRequestId()+" bạn đang chờ xác nhận. Chấp nhận hoặc từ chối?")
                        .setPositiveButton("Chấp nhận123", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Người dùng chọn "Chấp nhận", gửi yêu cầu xác nhận lên server
                                sendConfirmationRequest(request.getRequestId(), true);
                                holder.confirmButton.setText("Yêu Cầu Đã Được Chấp Nhận");
                                holder.confirmButton.setBackgroundColor(green);
                            }
                        })
                        .setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String a = request.getRequestId();
                                sendConfirmationRequest(request.getRequestId(), false);

                                holder.confirmButton.setText("Yêu Cầu Bị Từ Chối");
                                holder.confirmButton.setBackgroundColor(red);
                                // Người dùng chọn "Từ chối", gửi yêu cầu từ chối lên server
                                // Tạo Retrofit instance
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://192.168.1.2:3000/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                Interface_XoaYeuCau interfaceDelete = retrofit.create(Interface_XoaYeuCau.class);
                                Call<Delete_YeuCau> call = interfaceDelete.deleteYeucau(a);

                                call.enqueue(new Callback<Delete_YeuCau>() {
                                    @Override
                                    public void onResponse(Call<Delete_YeuCau> call, Response<Delete_YeuCau> response) {
                                        Delete_YeuCau svrResponseDelete = response.body(); // lay kq tu serrverr
                                        Toast.makeText(context, "xóa thành công " + svrResponseDelete.getMessage(), Toast.LENGTH_SHORT).show();
                                        // inteloadData.loadData();
                                    }

                                    @Override
                                    public void onFailure(Call<Delete_YeuCau> call, Throwable t) {
                                        Toast.makeText(context, "xóa thất bại " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                                // Gọi sendConfirmationRequest với isAccepted là false

                            }
                        });
                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTextView;
        private Button confirmButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            confirmButton = itemView.findViewById(R.id.confirmButton);
        }


    }

    // Hàm hiển thị dialog xác nhận



    private void sendConfirmationRequest(String requestId, boolean isAccepted) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/") // Địa chỉ của server
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(Interfave_AddHoaDon.class);
        ConfirmationRequest confirmationRequest = new ConfirmationRequest(requestId, isAccepted);
        Call<RequestModel> call = apiService.confirmRequest(confirmationRequest);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                if (response.isSuccessful()) {
                    // Xử lý khi xác nhận từ server thành công
         //           handleConfirmationResponse(response.body().getMessage());
                    Toast.makeText(context, "Yeu cau duoc chap nhan", Toast.LENGTH_SHORT).show();



                } else {
                    // Xử lý lỗi khi xác nhận từ server
                    showToast("Có lỗi xảy ra khi xác nhận yêu cầu.");
                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                // Xử lý lỗi kết nối
                showToast("Lỗi kết nối đến server.");
            }
        });
    }




    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void handleErrorResponse(Response<AddHoaDon> response) {
        // Xử lý lỗi khi thêm mới đối tượng
        if (response.code() == 400) {
            // Lỗi Bad Request - Yêu cầu không hợp lệ
            Toast.makeText(context, "Yêu cầu không hợp lệ.", Toast.LENGTH_SHORT).show();
        } else {
            // Xử lý các mã lỗi khác tùy theo yêu cầu của bạn
            Toast.makeText(context, "Có lỗi xảy ra khi thêm mới đối tượng.", Toast.LENGTH_SHORT).show();
        }
    }

}

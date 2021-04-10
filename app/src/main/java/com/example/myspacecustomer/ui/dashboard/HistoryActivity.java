package com.example.myspacecustomer.ui.dashboard;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.Network.Api;
import com.example.myspacecustomer.Network.AppConfig;
import com.example.myspacecustomer.adapters.BookingHistoryAdapter;
import com.example.myspacecustomer.adapters.ShopNameAdapter;
import com.example.myspacecustomer.data.Booking;

import com.example.myspacecustomer.databinding.ActivityHistoryBinding;
import com.example.myspacecustomer.databinding.LayoutQrDialogBinding;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.utils.Config;
import com.example.myspacevendor.data.Shop;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryActivity extends AppCompatActivity implements BookingHistoryAdapter.BookingInterface {


    private ActivityHistoryBinding binding;
    private final Context context = this;


    private static final String TAG = "HistoryActivity";

    private final List<Booking> bookingList = new ArrayList<>();
    private BookingHistoryAdapter bookingHistoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handleToolbar();
        init();
        clickListener();
        fetchBookingData();

    }

    /*--------------------------------- Handle Toolbar --------------------------------*/

    private void handleToolbar() {

        binding.includedToolbar.title.setText("Booking History");
        binding.includedToolbar.backBtn.setOnClickListener(v -> finish());
    }


    private void clickListener() {
//        binding.back.setOnClickListener(view -> openActivity(CustomerDashActivity.class));

    }


    /*--------------------------------- Init --------------------------------*/

    private void init() {
        bookingHistoryAdapter = new BookingHistoryAdapter(bookingList, this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(bookingHistoryAdapter);

    }


    /*--------------------------------- Fetch Booking Data --------------------------------*/

    private void fetchBookingData() {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.getBookingData(Config.user_id);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse response1 = response.body();

                    bookingList.clear();
                    bookingList.addAll(response1.getBookingList());
                    bookingHistoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    /*----------------------------------- Open Activity -----------------------------------------*/

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);

    }


    /*----------------------------- Handle Interface ---------------------------------*/


    @Override
    public void onClick(Booking booking) {
        displayQRCode(booking.getBookingId());
    }


    /*----------------------------- Display QR Dialog  ---------------------------------*/

    private void displayQRCode(String bookingId) {


        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("QR Code");

        LayoutQrDialogBinding qrDialogBinding = LayoutQrDialogBinding.inflate(getLayoutInflater(), null, false);
        builder.setView(qrDialogBinding.getRoot());

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(String.valueOf(bookingId), BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrDialogBinding.imageQR.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );


        } catch (WriterException e) {
            Config.showToast(context, e.getMessage());
            e.printStackTrace();
        }


        // add a button
        builder.setPositiveButton(
                "OK",
                (dialog, which) -> {

                    dialog.dismiss();
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

package com.example.myspacecustomer.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.Network.Api;
import com.example.myspacecustomer.Network.AppConfig;
import com.example.myspacecustomer.adapters.BookingHistoryAdapter;
import com.example.myspacecustomer.databinding.ActivityShopProfileBinding;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.ui.dashboard.CustomerDashActivity;
import com.example.myspacecustomer.ui.dashboard.HistoryActivity;
import com.example.myspacecustomer.utils.Config;
import com.example.myspacevendor.data.slot.SlotData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShopProfileActivity extends AppCompatActivity {
    private ActivityShopProfileBinding binding;
    private final Context context = this;

    private int shopId;

    private static final String TAG = "ShopProfileActivity";

    private final List<SlotData> slotDataList = new ArrayList<>();

    private ArrayAdapter<String> dropDownAdapter;

    private int slotId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityShopProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        shopId = intent.getIntExtra("shop_id", 0);


        handleToolbar();

        init();
        clickListener();

    }


    /*--------------------------------- Handle Toolbar --------------------------------*/

    private void handleToolbar() {

        binding.includedToolbar.title.setText("Shop Details");
        binding.includedToolbar.backBtn.setOnClickListener(v -> finish());
    }


    /*--------------------------------- Init --------------------------------*/

    private void init() {
        fetchSlots(shopId);

    }

    private void clickListener() {

        binding.edtSlot.setOnItemClickListener((parent, view, position, id) -> {
            slotId = slotDataList.get(position).getSlotId();
        });


        binding.book.setOnClickListener(v -> {

            if (slotId == 0) {
                Config.showToast(context, "Please Select Slot");
                return;

            }


            sendSlotDataTOServer(slotId, shopId);

        });

    }

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);

    }



    /*--------------------------------- Fetch Slots --------------------------------*/

    private void fetchSlots(int shopId) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.getSlotData(shopId);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse response1 = response.body();

                    slotDataList.clear();
                    slotDataList.addAll(response1.getSlotList());

                    handleSlotListHere();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }



    /*--------------------------------- Fetch Slots --------------------------------*/

    private void sendSlotDataTOServer(int slotId, int shopId) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.bookSlot(Config.user_id, shopId, slotId);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    Config.showToast(context, response.body().getMessage());

                    if (!response.body().getError()) {

                        openActivity(HistoryActivity.class);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    /*--------------------------------- Handling Shop Slot Dropdown --------------------------------*/

    private void handleSlotListHere() {

        if (!slotDataList.isEmpty()) {
            List<String> lData = new ArrayList<>();

            for (SlotData slot : slotDataList) {
                lData.add(slot.getSlotStart() + " - " + slot.getSlotEnd());
            }

            dropDownAdapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_list_item_1, lData);

            binding.edtSlot.setAdapter(dropDownAdapter);

            slotId = slotDataList.get(0).getSlotId();
        }

    }


}

package com.example.myspacecustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myspacecustomer.databinding.LayoutDashShopsBinding;
import com.example.myspacevendor.data.Shop;

import java.util.List;


    public class ShopNameAdapter extends RecyclerView.Adapter<ShopNameAdapter.ViewHolder> {

        private List<Shop> restaurantList;
        private RestaurantInterface restaurantInterface;

        public ShopNameAdapter(List<Shop> restaurantList, RestaurantInterface restaurantInterface) {
            this.restaurantList = restaurantList;
            this.restaurantInterface = restaurantInterface;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutDashShopsBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Shop shop = restaurantList.get(position);

            holder.binding.shopNa.setText(shop.getShopName());

            holder.binding.getRoot().setOnClickListener(view -> {
                restaurantInterface.onClick(shop);
            });

        }

        @Override
        public int getItemCount() {
            return restaurantList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            LayoutDashShopsBinding binding;

            public ViewHolder(@NonNull LayoutDashShopsBinding binding) {
                super(binding.getRoot());

                this.binding = binding;
            }
        }


        public interface RestaurantInterface {
            void onClick(Shop shop);
        }

    }




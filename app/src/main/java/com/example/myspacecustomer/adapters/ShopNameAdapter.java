package com.example.myspacecustomer.adapters;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myspacecustomer.databinding.LayoutDashShopsBinding;
import com.example.myspacevendor.data.Shop;

import java.util.ArrayList;
import java.util.List;


public class ShopNameAdapter extends RecyclerView.Adapter<ShopNameAdapter.ViewHolder> implements Filterable {

//    private List<ExampleItem> exampleList;
//    private List<ExampleItem> exampleListFull;

    private static final String TAG = "ShopNameAdapter";
    private List<Shop> shopListFull;
    private List<Shop> shopList;
    private ShopInterface shopInterface;


    public ShopNameAdapter(List<Shop> shopList, ShopInterface shopInterface) {
        this.shopList = shopList;
        this.shopInterface = shopInterface;
        shopListFull = new ArrayList<>(shopList);
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Shop> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (TextUtils.isEmpty(constraint)) {
                results.count = shopList.size();
                results.values = new ArrayList(shopList);
            }

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(shopListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Shop item : shopListFull) {
                    if (item.getShopName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }

                Log.d(TAG, "performFiltering: " + filteredList);
            }

            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            Log.d(TAG, "publishResults: " + results.values);

            shopList.clear();
            shopList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutDashShopsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Shop shop = shopList.get(position);

        holder.binding.shopNa.setText(shop.getShopName());

        holder.binding.getRoot().setOnClickListener(view -> {
            shopInterface.onClick(shop);
        });

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LayoutDashShopsBinding binding;

        public ViewHolder(@NonNull LayoutDashShopsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }


    public interface ShopInterface {
        void onClick(Shop shop);
    }

}




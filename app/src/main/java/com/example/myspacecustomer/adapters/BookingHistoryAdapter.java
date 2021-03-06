package com.example.myspacecustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myspacecustomer.data.Booking;
import com.example.myspacecustomer.databinding.LayoutHistoryBinding;

import java.util.List;


public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    private List<Booking> bookingList;
    private BookingInterface bookingInterface;

    public BookingHistoryAdapter(List<Booking> restaurantList, BookingInterface bookingInterface) {
        this.bookingList = restaurantList;
        this.bookingInterface = bookingInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Booking booking = bookingList.get(position);

        holder.binding.tvShopName.setText(booking.getShopName());

        if (booking.isVerified() == 0) {
            holder.binding.tvStatus.setText("Pending");
        } else if (booking.isVerified() == 1) {
            holder.binding.tvStatus.setText("Verified");
        } else if (booking.isDone() == 1) {
            holder.binding.tvStatus.setText("Status Closed");
        }

        holder.binding.tvStatus.setText(booking.getShopName());
        holder.binding.tvSlotStart.setText("Slot Start : " + booking.getSlotStart());
        holder.binding.tvSlotEnd.setText("Slot End : " + booking.getSlotEnd());

        holder.binding.btnShowQR.setOnClickListener(view -> {
            bookingInterface.onClick(booking);
        });

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LayoutHistoryBinding binding;

        public ViewHolder(@NonNull LayoutHistoryBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }


    public interface BookingInterface {
        void onClick(Booking booking);
    }

}




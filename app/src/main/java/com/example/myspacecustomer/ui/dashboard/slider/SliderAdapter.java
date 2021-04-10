package com.example.myspacecustomer.ui.dashboard.slider;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myspacecustomer.databinding.SliderListBinding;
import com.example.myspacecustomer.model.MySliderList;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<MySliderList> mSliderItems;


    public SliderAdapter(Context context, List<MySliderList> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new SliderAdapterVH(SliderListBinding.inflate(layoutInflater));

    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        MySliderList sliderItem = mSliderItems.get(position);

        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImage_url())
                .fitCenter()
                .into(viewHolder.binding.sliderImage);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private final SliderListBinding binding;

        public SliderAdapterVH(SliderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.myspacecustomer.model;
import com.google.gson.annotations.SerializedName;

public class MySliderList {



        @SerializedName("id")
        public int id;

        @SerializedName("image_url")
        public String image_url;

        @SerializedName("title")
        public String title;

        @SerializedName("desc")
        public String description;

        public MySliderList(int id, String image_url, String title, String description) {
            this.id = id;
            this.image_url = image_url;
            this.title = title;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }






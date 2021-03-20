package com.example.myspacecustomer.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.databinding.ActivityQrcodeBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCode extends AppCompatActivity {

    private ActivityQrcodeBinding binding;
    private final Context context = this;
    EditText etinput;
    Button btgenerate;
    ImageView ivoutput;


    private static final String TAG = "QRCode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQrcodeBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        handleToolbar();
        init();
        clickListener();

    }


    private void init() {

        etinput=binding.etInput;
        btgenerate=binding.btGenerate;
        ivoutput=binding.ivOutput;


        }

    /*--------------------------------- Handle Toolbar --------------------------------*/

    private void handleToolbar() {

        binding.includedToolbar.title.setText("QR Code");
        binding.includedToolbar.backBtn.setOnClickListener(v -> finish());
    }



    private void clickListener() {
        binding.btGenerate.setOnClickListener(view -> {

            String sText = binding.etInput.getText().toString().trim();
            if (TextUtils.isEmpty(sText)) {
                binding.etInput.setError("Enter Text to generate QR Code!!");
                return;
            }
            doActivity(sText);
        });


    }

    private void doActivity(String sText) {
        {
            MultiFormatWriter writer=new MultiFormatWriter();

            try {
                //Initialize bit matrix
                BitMatrix matrix=writer.encode(sText, BarcodeFormat.QR_CODE,350,350);
                //Initialize barcode encoder
                BarcodeEncoder encoder= new BarcodeEncoder();
                //Initialize bitmap
                Bitmap bitmap=encoder.createBitmap(matrix);
                //Set bitmap on image view
                ivoutput.setImageBitmap(bitmap);
                //Initial input Manager
                InputMethodManager manager=(InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE
                );
                //Hide soft keyboard
                manager.hideSoftInputFromWindow(etinput.getApplicationWindowToken(),0);



            } catch (WriterException e) {
                e.printStackTrace();
            }


        }
    }


}

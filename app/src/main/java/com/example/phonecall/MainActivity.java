package com.example.phonecall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn);
        editText=findViewById(R.id.ed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callphonenumber();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==101)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                callphonenumber();
            }
        }
    }


    private void callphonenumber() {


        if(Build.VERSION.SDK_INT>22) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 101);


                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + editText.getText().toString().trim()));
                startActivity(call);
            }


             else{


                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:" + editText.getText().toString().trim()));
                    startActivity(call);

                }

        }
    }
}


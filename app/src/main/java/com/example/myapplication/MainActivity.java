package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    boolean isStartProcess = false;
    LinearLayout styleProcessLinearLayout;
    public static final int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        styleProcessLinearLayout = findViewById(R.id.linear_layout_start_process);

        // setup button to start style transfer process
        final Button startProcessBtn = (Button) findViewById(R.id.btn_start_style_process);

        startProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isStartProcess = !isStartProcess;

                if (isStartProcess) {

                    // set startProcess button text to blue :P
                    startProcessBtn.setTextColor(getResources().getColor(R.color.colorAccent));

                    // create and add buttons for where to select image from - take photo or select from files/galary
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    Button takePhotoBtn = new Button(MainActivity.this);
                    takePhotoBtn.setId(R.id.buttonTakePhotot);
                    takePhotoBtn.setLayoutParams(params);
                    takePhotoBtn.setTextSize(12);
                    takePhotoBtn.setText(R.string.take_photo);
                    takePhotoBtn.setTextColor(getResources().getColor(R.color.black));
                    takePhotoBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    styleProcessLinearLayout.addView(takePhotoBtn);
                    styleProcessLinearLayout.invalidate();
                    takePhotoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // let user take photo using camera

                        }
                    });

                    Button selectFromFilesBtn = new Button(MainActivity.this);
                    selectFromFilesBtn.setId(R.id.buttonSelectFromDevice);
                    selectFromFilesBtn.setLayoutParams(params);
                    selectFromFilesBtn.setTextSize(12);
                    selectFromFilesBtn.setText(R.string.select_image_from_device);
                    selectFromFilesBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    selectFromFilesBtn.setTextColor(getResources().getColor(R.color.black));
                    styleProcessLinearLayout.addView(selectFromFilesBtn);
                    styleProcessLinearLayout.invalidate();
                    selectFromFilesBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                            photoPickerIntent.setType("image/*");
                            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                        }
                    });

                } else {
                    // start process button de-selected - remove all select and take photo views
                    startProcessBtn.setTextColor(getResources().getColor(R.color.black));
                    styleProcessLinearLayout.removeView(findViewById(R.id.buttonSelectFromDevice));
                    styleProcessLinearLayout.removeView(findViewById(R.id.buttonTakePhotot));
                }
            }
        });

        // setup button to exit application
        Button exitBtn = (Button) findViewById(R.id.btn_exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                // get image irl
                final Uri imageUri = data.getData();
                // go to style image activity
                Intent intent = new Intent(MainActivity.this, StyleProcess.class);
                intent.putExtra("selected_image_url", imageUri.toString());
                MainActivity.this.startActivity(intent);

            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }




}
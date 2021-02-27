package com.example.myapplication;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


enum StyleCategory {
    CUSTOM,
    SEASONAL,
    NIGHT,
    NATURE,
    OCEAN,
    SUNSET,
    WEATHER,
    SPACE,
    ARTISTIC,
    NUM_STYLE_CATEGORIES
}

public class StyleProcess extends AppCompatActivity {

    // variables to reference layout elements
    LinearLayout displayStyleCategories;
    LinearLayout displayStyleImages;
    ImageView displayImageView;
    Bitmap sourceImage;

    HashMap<StyleCategory, ArrayList<Integer>> styleMap; // style category-image mappings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_process);

        // get selected image url from previous activity
        displayImageView = findViewById(R.id.display_image);
        Intent intent = getIntent();
        Uri imageUri = Uri.parse(intent.getStringExtra("selected_image_url"));
        try {
            // read image
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            // display selected image
            displayImageView.setImageBitmap(selectedImage);
            sourceImage = ((BitmapDrawable) displayImageView.getDrawable()).getBitmap();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(StyleProcess.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        // get references to layout elements
        displayStyleCategories = findViewById(R.id.display_style_categories);
        displayStyleImages = findViewById(R.id.display_style_images);

        // init style mappings
        styleMap = new HashMap<>(StyleCategory.NUM_STYLE_CATEGORIES.ordinal());
        for (StyleCategory styleCategory : StyleCategory.values())
            if (styleCategory != StyleCategory.NUM_STYLE_CATEGORIES)
            styleMap.put(styleCategory, new ArrayList<Integer>());

        // --------------------- add style images per category -----------------------
        // night
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_1);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_2);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_3);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_4);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_5);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_6);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_7);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_8);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_9);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_10);
        styleMap.get(StyleCategory.NIGHT).add(R.drawable.night_11);

        // seasonal
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_1);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_2);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_3);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_4);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_5);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.summer_1);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.winter_2);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_1);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_2);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_3);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_4);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_5);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_6);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.autumn_7);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_1);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_2);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_3);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_4);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_5);
        styleMap.get(StyleCategory.SEASONAL).add(R.drawable.spring_6);

        // nature
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_1);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_2);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_3);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_4);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_5);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_6);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_7);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_8);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_9);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_10);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_11);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_14);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_12);
        styleMap.get(StyleCategory.NATURE).add(R.drawable.nature_13);

        // sunrise/sunset
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunset_1);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunset_2);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunset_3);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunset_4);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunset_5);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunrise_1);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunrise_2);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunrise_3);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunrise_4);
        styleMap.get(StyleCategory.SUNSET).add(R.drawable.sunrise_5);

        // weather
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_1);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_2);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_3);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_4);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_5);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_6);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_7);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_8);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_9);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_10);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_11);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_12);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_13);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_14);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_15);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_16);
        styleMap.get(StyleCategory.WEATHER).add(R.drawable.weather_17);

        // artistic
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_1);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_2);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_3);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_4);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_5);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_6);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_7);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_8);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_10);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_11);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_12);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_13);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_14);
        styleMap.get(StyleCategory.ARTISTIC).add(R.drawable.art_15);

        // ocean
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_1);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_2);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_3);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_4);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_5);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_6);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_7);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_8);
        styleMap.get(StyleCategory.OCEAN).add(R.drawable.ocean_9);

        // astronomy
        styleMap.get(StyleCategory.SPACE).add(R.drawable.astronomy_4);
        styleMap.get(StyleCategory.SPACE).add(R.drawable.astronomy_1);
        styleMap.get(StyleCategory.SPACE).add(R.drawable.astronomy_2);
        styleMap.get(StyleCategory.SPACE).add(R.drawable.astronomy_3);
        styleMap.get(StyleCategory.SPACE).add(R.drawable.astronomy_5);

        // custom
        // TODO: add functionality to let user choose images to be displayed into this category
        // TODO: custom images to be saved to SQLite and read into styleMap on app launch

        // display style categories
        for (final StyleCategory styleCategory : styleMap.keySet()) {
            TextView categoryTextView = new TextView(this);
            categoryTextView.setText(styleCategory.toString());
            categoryTextView.setTextColor(getResources().getColor(R.color.white));
            categoryTextView.setGravity(Gravity.CENTER);
            categoryTextView.setClickable(true);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(20, 0, 20, 0);
            categoryTextView.setLayoutParams(params);
            displayStyleCategories.addView(categoryTextView);

            // when category is selected, display corresponding images
            categoryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // remove current style images from 'display' layout
                    displayStyleImages.removeAllViews();

                    // add images to 'display' layout for this category
                    for (int styleImageId : styleMap.get(styleCategory)) {
                        final ImageView styleImageView = new ImageView(getBaseContext());
                        styleImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        styleImageView.setImageResource(styleImageId);
                        styleImageView.setClickable(true);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.setMargins(1, 0, 1, 0);
                        styleImageView.setLayoutParams(params);
                        displayStyleImages.addView(styleImageView);

                        // apply style to source image on select
                        styleImageView.setClickable(true);
                        styleImageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bitmap sourceImage = ((BitmapDrawable) displayImageView.getDrawable()).getBitmap();
                                Bitmap targetImage = ((BitmapDrawable) styleImageView.getDrawable()).getBitmap();
                                HistogramMatchingStyleTransfer styleTransfer = new HistogramMatchingStyleTransfer(sourceImage, targetImage);
                                Bitmap styledImage = styleTransfer.styleImage();
                                displayImageView.setImageBitmap(styledImage);
                            }
                        });

                    }
                }
            });

        }



    }

}
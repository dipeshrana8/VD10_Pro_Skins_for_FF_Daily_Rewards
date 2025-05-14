package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ffskin.vault.elitecustomizer.R;
import com.ffskin.vault.elitecustomizer.databinding.ActivityDetailsBinding;

import java.io.IOException;
import java.io.InputStream;

public class A21_DetailsActivity extends BaseActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        showSettings = true;

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );

        binding.btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        binding.btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A21_DetailsActivity.this, RPLevelActivity.class);
                startActivity(intent);
                finish();

            }
        });
        SharedPreferences prefs = getSharedPreferences("bundle_data", MODE_PRIVATE);

        String str_name = prefs.getString("name", "");
        String str_desc = prefs.getString("desc", "");
        String str_ability = prefs.getString("ability", "");
        String imagePath = prefs.getString("imagePath", "");


        binding.toolbarLayout.headerTitle.setText(str_name);
        binding.txtNames.setText(str_name);
        binding.txtAbilities.setText(str_ability);
        binding.txtDesc.setText(str_desc);


        if (imagePath.length() != 0) {
            try {
                InputStream is = getAssets().open(imagePath);
                Drawable drawable = Drawable.createFromStream(is, null);

                Glide.with(this)
                        .load(drawable)
                        .into(binding.imgPreview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            binding.imgPreview.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
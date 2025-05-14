package com.ffskin.vault.elitecustomizer.spalsh;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ffskin.vault.elitecustomizer.databinding.ActivityGuideDetailBinding;


public class GuideDetailActivity extends BaseActivity {
    ActivityGuideDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get data from Intent
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        // Set toolbar title and buttons
        toolbarHeaderText = title;
        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        binding.toolbarLayout.headerTitle.setText(title);
        binding.toolbarLayout.headerTitle.setSelected(true);

        // Set title and description
        binding.textViewDescription.setText(description);
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
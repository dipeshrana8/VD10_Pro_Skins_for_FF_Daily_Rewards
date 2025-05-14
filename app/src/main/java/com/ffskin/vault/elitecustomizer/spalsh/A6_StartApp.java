package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ffskin.vault.elitecustomizer.databinding.ActivitySettingBinding;

import java.util.HashMap;
import java.util.Map;

public class A6_StartApp extends BaseActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Get Diamonds Guide";


        super.onCreate(savedInstanceState);


        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(binding.toolbarLayout.headerTitle, binding.toolbarLayout.btnBack, binding.toolbarLayout.btnSettings);
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        Map<View, Runnable> actionMap = new HashMap<>();

        actionMap.put(binding.btnPrivacy, this::openPrivacyPolicy);
        actionMap.put(binding.btnRate, this::rateApp);
        actionMap.put(binding.btnShareApp, this::shareApp);

        View.OnClickListener smartClick = v -> {
            if (actionMap.containsKey(v)) {
                showInterstitialAds = false;
                actionMap.get(v).run();
            }
        };


        for (View view : actionMap.keySet()) {
            view.setOnClickListener(smartClick);
        }


        binding.btnStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A6_StartApp.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        myBackActivity();


    }


}
package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ffskin.vault.elitecustomizer.databinding.ActivityCalEliteBinding;

public class CalEliteActivity extends BaseActivity {


    ActivityCalEliteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbarHeaderText = "Diamond Calculator";


        binding = ActivityCalEliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        binding.btnDiamondCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CalEliteActivity.this, BasiCalculatorActivity.class);
                startActivity(intent);
            }
        });
        binding.btnElitePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalEliteActivity.this, DiamondCalActivity.class);
                intent.putExtra("CALCULATOR_NAME", "Elite Pass Calculator");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ffskin.vault.elitecustomizer.databinding.ActivityBasiCalculatorBinding;

public class BasiCalculatorActivity extends BaseActivity {

    ActivityBasiCalculatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbarHeaderText = "Basic Calculator";


        binding = ActivityBasiCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        binding.btnBasicCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BasiCalculatorActivity.this, DiamondCalActivity.class);
                intent.putExtra("CALCULATOR_NAME", "Basic Calculator");
                startActivity(intent);
            }
        });
        binding.btnNormalCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasiCalculatorActivity.this, DiamondCalActivity.class);
                intent.putExtra("CALCULATOR_NAME", "Normal Calculator");
                startActivity(intent);
            }
        });
        binding.btnAdvanceCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasiCalculatorActivity.this, DiamondCalActivity.class);
                intent.putExtra("CALCULATOR_NAME", "Advance Calculator");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
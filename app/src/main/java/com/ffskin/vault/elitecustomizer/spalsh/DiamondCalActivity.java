package com.ffskin.vault.elitecustomizer.spalsh;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.ffskin.vault.elitecustomizer.R;
import com.ffskin.vault.elitecustomizer.databinding.ActivityCalculatorPreviewBinding;
import com.ffskin.vault.elitecustomizer.myAds.WebNavigationUtils;

public class DiamondCalActivity extends BaseActivity {

    ActivityCalculatorPreviewBinding binding;
    String CALCULATOR_NAME = "Diamond Calculator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CALCULATOR_NAME = getIntent().getStringExtra("CALCULATOR_NAME");
        toolbarHeaderText = CALCULATOR_NAME;
        binding = ActivityCalculatorPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        if (CALCULATOR_NAME != null) {

            toolbarHeaderText = CALCULATOR_NAME;
        } else {
            toolbarHeaderText = CALCULATOR_NAME;
        }

        binding.etCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    binding.imgBtnCalculator.setImageResource(R.drawable.img_calculator_unselected);
                } else {
                    binding.imgBtnCalculator.setImageResource(R.drawable.img_calculator);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        showSettings = true;

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );


        setupListeners();

    }


    private void setupListeners() {
        binding.imgBtnCalculator.setOnClickListener(v -> calculateDiamonds(CALCULATOR_NAME));
    }

    private void calculateDiamonds(String calculatorName) {


        String input1 = binding.etCal.getText() != null ? binding.etCal.getText().toString().trim() : "";

        if (input1.isEmpty()) {
            Toast.makeText(this, "Enter Value Please !", Toast.LENGTH_SHORT).show();
            return;
        }
        View dialogView = LayoutInflater.from(DiamondCalActivity.this).inflate(R.layout.dialog_active, null);
        AlertDialog dialog = new AlertDialog.Builder(DiamondCalActivity.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(DiamondCalActivity.this);
                dialog.dismiss();
            }
        });

        TextView etCal = dialogView.findViewById(R.id.txtCollect);

        dialog.show();
        String input = binding.etCal.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double diamonds = Double.parseDouble(input);
            double result = diamonds * 35;

            etCal.setText(String.format("Buying This Much Diamonds Will Cost\n " + result + " Dollars"));
        } catch (NumberFormatException e) {
            etCal.setText("Invalid input");

        }

    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

}
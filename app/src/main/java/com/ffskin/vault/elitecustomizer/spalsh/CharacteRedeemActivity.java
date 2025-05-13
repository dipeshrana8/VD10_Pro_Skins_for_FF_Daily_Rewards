package com.ffskin.vault.elitecustomizer.spalsh;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.ffskin.vault.elitecustomizer.R;
import com.ffskin.vault.elitecustomizer.databinding.ActivityCodeBinding;
import com.ffskin.vault.elitecustomizer.myAds.WebNavigationUtils;

public class CharacteRedeemActivity extends BaseActivity {

    ActivityCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Character Redeem";


        super.onCreate(savedInstanceState);
        binding = ActivityCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        binding.btnFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redeemDialog();
            }
        });
    }

    private void redeemDialog() {

        View dialogView = LayoutInflater.from(CharacteRedeemActivity.this).inflate(R.layout.dialog_redeem, null);
        AlertDialog dialog = new AlertDialog.Builder(CharacteRedeemActivity.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnCollect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                WebNavigationUtils.WebInterstitial(CharacteRedeemActivity.this);
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
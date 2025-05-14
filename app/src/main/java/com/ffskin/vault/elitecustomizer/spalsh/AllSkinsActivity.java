package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ffskin.vault.elitecustomizer.databinding.ActivityAllSkinBinding;

public class AllSkinsActivity extends BaseActivity {


    ActivityAllSkinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "All Skins";


        super.onCreate(savedInstanceState);
        binding = ActivityAllSkinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        binding.btnCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
                intent.putExtra("category", "Character");
                startActivity(intent);
            }
        });

        binding.btnPet.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Pets");
            startActivity(intent);
        });
        binding.btnWeapons.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Weapons");
            startActivity(intent);
        });

        binding.btnVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Vehicles");
            startActivity(intent);
        });
        binding.btnEmotes.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Emotes");
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
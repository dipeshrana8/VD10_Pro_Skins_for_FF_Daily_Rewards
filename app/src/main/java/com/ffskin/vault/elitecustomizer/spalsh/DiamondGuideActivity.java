package com.ffskin.vault.elitecustomizer.spalsh;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.ffskin.vault.elitecustomizer.databinding.ActivityGuideBinding;
import com.ffskin.vault.elitecustomizer.model.GuideCategory;
import com.ffskin.vault.elitecustomizer.model.GuidesAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DiamondGuideActivity extends BaseActivity {
    ActivityGuideBinding binding;
    private List<GuideCategory> filteredCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String category = getIntent().getStringExtra("category");
        toolbarHeaderText = category != null && category.equals("Guide") ? "Daily Diamond Guide" : "Tips & Tricks";

        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );

        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        loadGuidesData(category);

        binding.recyclerViewTips.setLayoutManager(new LinearLayoutManager(this));
        GuidesAdapter adapter = new GuidesAdapter(this, filteredCategories);
        binding.recyclerViewTips.setAdapter(adapter);
    }

    private void loadGuidesData(String selectedCategory) {
        try {
            InputStream inputStream = getAssets().open("guides_and_tips.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            inputStream.close();

            Gson gson = new Gson();
            GuidesWrapper wrapper = gson.fromJson(jsonString.toString(), GuidesWrapper.class);
            GuideCategory[] allCategories = wrapper.getCategories();

            filteredCategories = new ArrayList<>();
            for (GuideCategory category : allCategories) {
                if (category.getName().equals(selectedCategory)) {
                    filteredCategories.add(category);
                    break;
                }
            }

            if (filteredCategories.isEmpty()) {
                filteredCategories = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            filteredCategories = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    private static class GuidesWrapper {
        private GuideCategory[] categories;

        public GuideCategory[] getCategories() {
            return categories;
        }
    }
}
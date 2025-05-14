package com.ffskin.vault.elitecustomizer.spalsh;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ffskin.vault.elitecustomizer.databinding.ActivityCharacterListBinding;
import com.ffskin.vault.elitecustomizer.model.Character;
import com.ffskin.vault.elitecustomizer.model.CharacterAdapter;
import com.ffskin.vault.elitecustomizer.myAds.WebNavigationUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CharacterListActivity extends BaseActivity {
    String category = "";
    private ActivityCharacterListBinding binding;
    private ArrayList<Character> characterList;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        category = getIntent().getStringExtra("category");
        toolbarHeaderText = category;
        showSettings = true;
        super.onCreate(savedInstanceState);

        binding = ActivityCharacterListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        setupToolbar(binding.toolbarLayout.headerTitle, binding.toolbarLayout.btnBack, binding.toolbarLayout.btnSettings);


        characterList = loadCharactersFromJson(category);
        setupRecyclerView();

        binding.btnManChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(CharacterListActivity.this);
                binding.btnManChar.setVisibility(GONE);
                characterList = loadCharactersFromJson("Men");
                setupRecyclerView();

            }
        });


        binding.btnProjectiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(CharacterListActivity.this);
                binding.btnManChar.setVisibility(GONE);
                characterList = loadCharactersFromJson("Projectiles");
                setupRecyclerView();

            }
        });

        binding.btnRareEmotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(CharacterListActivity.this);
                binding.btnManChar.setVisibility(GONE);
                characterList = loadCharactersFromJson("Rare");
                setupRecyclerView();

            }
        });


        binding.btnAdvanceEmotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(CharacterListActivity.this);
                binding.btnManChar.setVisibility(GONE);
                characterList = loadCharactersFromJson("Advance");
                setupRecyclerView();

            }
        });
        if (category.equals("Emotes")) {
            binding.btnRareEmotes.setVisibility(VISIBLE);
            binding.btnAdvanceEmotes.setVisibility(VISIBLE);
        } else if (category.equals("Weapons")) {
            binding.btnProjectiles.setVisibility(VISIBLE);
        } else if (category.equals("Character")) {
            binding.btnManChar.setVisibility(VISIBLE);
        }
    }

//    private ArrayList<Character> loadCharactersFromJson(String category) {
//        ArrayList<Character> characters = new ArrayList<>();
//        try {
//            String json = loadJSONFromAsset("skintols.json");
//            JSONObject jsonObject = new JSONObject(json);
//            JSONArray categories = jsonObject.getJSONArray("Characters");
//            for (int i = 0; i < categories.length(); i++) {
//                JSONObject cat = categories.getJSONObject(i);
//                if (cat.getString("name").equals(category)) {
//                    JSONArray chars = cat.getJSONArray("items");
//                    for (int j = 0; j < chars.length(); j++) {
//                        JSONObject character = chars.getJSONObject(j);
//                        characters.add(new Character(
//                                character.getString("name"),
//                                character.getString("description"),
//                                character.getString("ability"),
//                                character.getString("imagePath")
//                        ));
//                    }
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return characters;
//    }


    private ArrayList<Character> loadCharactersFromJson(String category) {
        ArrayList<Character> characters = new ArrayList<>();
        try {
            String json = loadJSONFromAsset("skintols.json");
            JSONObject jsonObject = new JSONObject(json);

            // Check if the category is under "Characters" (for Character or Men)
            if (category.equals("Character") || category.equals("Men")) {
                JSONObject charactersObj = jsonObject.getJSONObject("Characters");
                JSONArray chars = charactersObj.getJSONArray(category);
                for (int i = 0; i < chars.length(); i++) {
                    JSONObject character = chars.getJSONObject(i);
                    // Convert abilities array to a single string
                    JSONArray abilitiesArray = character.getJSONArray("abilities");
                    StringBuilder abilitiesString = new StringBuilder();
                    for (int j = 0; j < abilitiesArray.length(); j++) {
                        abilitiesString.append(abilitiesArray.getString(j));
                        if (j < abilitiesArray.length() - 1) {
                            abilitiesString.append(", ");
                        }
                    }
                    characters.add(new Character(
                            character.getString("name"),
                            character.getString("description"),
                            abilitiesString.toString(),
                            character.getString("imagePath")
                    ));
                }
            } else {
                // Handle other top-level categories (Pets, Weapons, Projectiles, etc.)
                JSONArray items = jsonObject.getJSONArray(category);
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    // Convert abilities array to a single string
                    JSONArray abilitiesArray = item.getJSONArray("abilities");
                    StringBuilder abilitiesString = new StringBuilder();
                    for (int j = 0; j < abilitiesArray.length(); j++) {
                        abilitiesString.append(abilitiesArray.getString(j));
                        if (j < abilitiesArray.length() - 1) {
                            abilitiesString.append(", ");
                        }
                    }
                    characters.add(new Character(
                            item.getString("name"),
                            item.getString("description"),
                            abilitiesString.toString(),
                            item.getString("imagePath")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, show a Toast or log the error for debugging
            Log.e("CharacterListActivity", "Error loading JSON: " + e.getMessage());
        }
        return characters;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;

        if (category.equals("Maps")) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        adapter = new CharacterAdapter(characterList, character -> {
            SharedPreferences prefs = getSharedPreferences("bundle_data", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", character.getName())
                    .putString("desc", character.getDescription())
                    .putString("ability", character.getAbility())
                    .putString("imagePath", character.getImagePath())
                    .apply();
            Intent intent = new Intent(CharacterListActivity.this, A21_DetailsActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (category.equals("Emotes")) {
            binding.btnRareEmotes.setVisibility(VISIBLE);
            binding.btnAdvanceEmotes.setVisibility(VISIBLE);
        } else if (category.equals("Weapons")) {
            binding.btnProjectiles.setVisibility(VISIBLE);
        } else if (category.equals("Character")) {
            binding.btnManChar.setVisibility(VISIBLE);
        }
    }
}
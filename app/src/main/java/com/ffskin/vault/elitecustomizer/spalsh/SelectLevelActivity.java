package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ffskin.vault.elitecustomizer.R;
import com.ffskin.vault.elitecustomizer.databinding.ActivitySelectModeBinding;

import java.util.Arrays;
import java.util.List;

public class SelectLevelActivity extends BaseActivity {

    List<String> gameModes = Arrays.asList(
            "0-25",
            "26-40",
            "41-55",
            "56-65",
            "60 Above"
    );

    com.ffskin.vault.elitecustomizer.databinding.ActivitySelectModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Select ID Level";
        super.onCreate(savedInstanceState);

        binding = ActivitySelectModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> myBackActivity());

        binding.recyclerModes.setLayoutManager(new GridLayoutManager(this, 1));

        GameModeAdapter adapter = new GameModeAdapter(gameModes);
        binding.recyclerModes.setAdapter(adapter);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectLevelActivity.this, PlayerCategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    public class GameModeAdapter extends RecyclerView.Adapter<GameModeAdapter.ViewHolder> {

        private final List<String> modeList;
        private int selectedPosition = 0;

        public GameModeAdapter(List<String> modeList) {
            this.modeList = modeList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String mode = modeList.get(position);
            holder.txtMode.setText(mode);

            if (selectedPosition == position) {
                holder.itemLayout.setBackgroundResource(R.drawable.bg_country_select);
                holder.imgSelect.setImageResource(R.drawable.ic_selected);

            } else {
                holder.itemLayout.setBackgroundResource(R.drawable.bg_country_unselect);
                holder.imgSelect.setImageResource(R.drawable.ic_unselected);

            }

            holder.itemLayout.setOnClickListener(v -> {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return modeList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtMode;
            LinearLayout itemLayout;
            ImageView imgSelect;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtMode = itemView.findViewById(R.id.languageName);
                itemLayout = itemView.findViewById(R.id.itemLayout);
                imgSelect = itemView.findViewById(R.id.imgSelect);
            }
        }
    }
}
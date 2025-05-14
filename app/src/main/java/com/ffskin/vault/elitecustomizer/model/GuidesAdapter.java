package com.ffskin.vault.elitecustomizer.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ffskin.vault.elitecustomizer.databinding.ItemGuideBinding;
import com.ffskin.vault.elitecustomizer.spalsh.GuideDetailActivity;

import java.util.List;

public class GuidesAdapter extends RecyclerView.Adapter<GuidesAdapter.GuideViewHolder> {
    private final Context context;
    private final List<GuideCategory> categories;

    public GuidesAdapter(Context context, List<GuideCategory> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGuideBinding binding = ItemGuideBinding.inflate(LayoutInflater.from(context), parent, false);
        return new GuideViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        GuideCategory category = categories.get(0);
        GuideItem item = category.getItems().get(position);
        holder.binding.txtTitle.setText(item.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GuideDetailActivity.class);
            intent.putExtra("title", item.getTitle());
            intent.putExtra("description", item.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categories.isEmpty() ? 0 : categories.get(0).getItems().size();
    }

    static class GuideViewHolder extends RecyclerView.ViewHolder {
        ItemGuideBinding binding;

        public GuideViewHolder(@NonNull ItemGuideBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
package com.example.finvest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OnboardingAdapter
        extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    private final List<OnboardingModel> items;

    public OnboardingAdapter() {

        items = new ArrayList<>();

        items.add(
                new OnboardingModel(
                        R.drawable.ic_launcher_foreground,
                        "Your Money, Your Future",
                        "Manage your banking and investments from one simple application."
                )
        );

        items.add(
                new OnboardingModel(
                        R.drawable.ic_launcher_foreground,
                        "Track Your Investments",
                        "Monitor stocks, portfolio performance and market movements easily."
                )
        );

        items.add(
                new OnboardingModel(
                        R.drawable.ic_launcher_foreground,
                        "Invest Smarter",
                        "Get a complete view of your money and make informed investment decisions."
                )
        );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_onboarding,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        OnboardingModel item = items.get(position);

        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.ivOnboarding);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
        }
    }
}
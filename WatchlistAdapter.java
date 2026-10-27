package com.example.finvest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class WatchlistAdapter
        extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {

    private final List<WatchlistModel> stockList;

    public WatchlistAdapter(List<WatchlistModel> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_watchlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        WatchlistModel stock = stockList.get(position);

        holder.tvSymbol.setText(stock.getSymbol());

        holder.tvCompanyName.setText(
                stock.getCompanyName()
        );

        holder.tvPrice.setText(
                String.format(
                        Locale.getDefault(),
                        "₹%,.2f",
                        stock.getPrice()
                )
        );

        holder.tvChange.setText(
                String.format(
                        Locale.getDefault(),
                        "%+.2f%%",
                        stock.getChangePercentage()
                )
        );
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvSymbol;
        TextView tvCompanyName;
        TextView tvPrice;
        TextView tvChange;

        ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvSymbol =
                    itemView.findViewById(R.id.tvSymbol);

            tvCompanyName =
                    itemView.findViewById(R.id.tvCompanyName);

            tvPrice =
                    itemView.findViewById(R.id.tvPrice);

            tvChange =
                    itemView.findViewById(R.id.tvChange);
        }
    }
}
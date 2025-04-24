package com.example.shoppingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Activity.DetailActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.domain.PopularDomain;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    private ArrayList<PopularDomain> wishlistItems;
    private Context context;

    public WishlistAdapter(ArrayList<PopularDomain> wishlistItems, Context context) {
        this.wishlistItems = wishlistItems;
        this.context = context;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new WishlistViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        PopularDomain item = wishlistItems.get(position);

        holder.titleTxt.setText(item.getTitle());
        holder.priceTxt.setText("$" + item.getPrice());
        holder.ratingTxt.setText(String.valueOf(item.getScore()));

        int drawableResourceId = context.getResources().getIdentifier(
                item.getPicUrl(), "drawable", context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.itemPic);

        holder.addToCartBtn.setOnClickListener(view -> {
            // Add to cart functionality will be implemented later
            Toast.makeText(context, "Added to cart: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        });

        // Make the whole item clickable to view details
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, ratingTxt;
        ImageView itemPic;
        Button addToCartBtn;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            ratingTxt = itemView.findViewById(R.id.ratingTxt);
            itemPic = itemView.findViewById(R.id.itemPic);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
        }
    }
}
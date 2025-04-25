package com.example.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Adapter.WishlistAdapter;
import com.example.shoppingapp.Helper.ManagmentWishlist;
import com.example.shoppingapp.R;
import com.example.shoppingapp.domain.PopularDomain;

import java.util.ArrayList;

public class Wishlist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView emptyWishlistText;
    private ManagmentWishlist managmentWishlist;
    private WishlistAdapter adapter;
    private ImageView backBtn;
    private LinearLayout explorerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        initView();
        setupRecyclerView();
        setupBottomNavigation();

        backBtn.setOnClickListener(view -> {
            finish();
        });

        explorerLayout.setOnClickListener(view -> {
            Intent intent = new Intent(Wishlist.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.wishlistRecyclerView);
        emptyWishlistText = findViewById(R.id.emptyWishlistText);
        backBtn = findViewById(R.id.backBtn);
        explorerLayout = findViewById(R.id.explorerLayout);
        managmentWishlist = new ManagmentWishlist(this);
    }

    private void setupRecyclerView() {
        ArrayList<PopularDomain> wishlistItems = managmentWishlist.getWishlistList();

        if (wishlistItems.isEmpty()) {
            emptyWishlistText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyWishlistText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            adapter = new WishlistAdapter(wishlistItems, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    private void setupBottomNavigation() {
        LinearLayout profileLayout = findViewById(R.id.profileLayout);
        if (profileLayout != null) {
            profileLayout.setOnClickListener(view -> {
                Intent intent = new Intent(Wishlist.this, ProfilePage.class);
                startActivity(intent);
            });
        }

        LinearLayout cartLayout = findViewById(R.id.cartLayout);
        if (cartLayout != null) {
            cartLayout.setOnClickListener(view -> {
                // Add cart activity navigation here when you have one
                // Intent intent = new Intent(Wishlist.this, CartActivity.class);
                // startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh wishlist data when returning to this screen
        setupRecyclerView();
    }
}
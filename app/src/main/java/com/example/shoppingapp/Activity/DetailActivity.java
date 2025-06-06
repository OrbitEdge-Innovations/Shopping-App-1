package com.example.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Helper.ManagmentCart;
import com.example.shoppingapp.Helper.ManagmentWishlist;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityDetailBinding;
import com.example.shoppingapp.domain.PopularDomain;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;
    private ManagmentWishlist managmentWishlist;
    private boolean isBookmarked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        managmentWishlist = new ManagmentWishlist(this);

        getBundles();
        setupBottomNavigation();
    }

    private void getBundles() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.itemPic);

        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("$" + object.getPrice());
        binding.descriptionTxt.setText(object.getDescription());
        binding.reviewTxt.setText(object.getReview() + "");
        binding.ratingTxt.setText(object.getScore() + "");

        // Check if the item is already in wishlist and update bookmark button
        isBookmarked = managmentWishlist.isInWishlist(object);
        updateBookmarkButton();

        // Add to cart button functionality
        binding.addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managmentCart.insertFood(object);
                Toast.makeText(DetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Back button functionality
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add share button functionality
        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProduct();
            }
        });

        // Add bookmark button functionality
        binding.bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBookmark();
            }
        });
    }

    private void toggleBookmark() {
        if (isBookmarked) {
            // Remove from wishlist
            managmentWishlist.removeItem(object);
            isBookmarked = false;
        } else {
            // Add to wishlist
            managmentWishlist.insertItem(object);
            isBookmarked = true;
        }
        updateBookmarkButton();
    }

    private void updateBookmarkButton() {
        if (isBookmarked) {
            // Show filled/black bookmark icon
            binding.bookmarkBtn.setColorFilter(getResources().getColor(android.R.color.black));
        } else {
            // Show outline/grey bookmark icon
            binding.bookmarkBtn.setColorFilter(null);
        }
    }

    // Share product details method
    private void shareProduct() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        // Prepare the content to share
        String shareMessage = "Check out this awesome product: " + object.getTitle() +
                "\nPrice: $" + object.getPrice() +
                "\n\n" + object.getDescription();

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        // Create and start the chooser
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void setupBottomNavigation() {
        // Find the profile layout in the bottom navigation bar
        View profileLayout = findViewById(R.id.profileLayout);
        if (profileLayout != null) {
            profileLayout.setOnClickListener(view -> {
                openProfilePage();
            });
        }

        // You can also set click listeners directly on the imageView and textView if needed
        binding.imageView2.setOnClickListener(view -> {
            openProfilePage();
        });

        binding.textView100.setOnClickListener(view -> {
            openProfilePage();
        });

        // Add wishlist button functionality
        LinearLayout wishlistLayout = findViewById(R.id.wishlistLayout);
        if (wishlistLayout != null) {
            wishlistLayout.setOnClickListener(view -> {
                openWishlistPage();
            });
        }
    }

    private void openProfilePage() {
        Intent intent = new Intent(DetailActivity.this, ProfilePage.class);
        startActivity(intent);
    }

    private void openWishlistPage() {
        Intent intent = new Intent(DetailActivity.this, Wishlist.class);
        startActivity(intent);
    }
}
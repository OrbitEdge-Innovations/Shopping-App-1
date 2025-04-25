package com.example.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shoppingapp.Adapter.PopularAdapter;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityMainBinding;
import com.example.shoppingapp.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();
        setupBottomNavigation();
        bottomNavigation();
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.DarkBlueGreen));
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRecyclerView(){
        ArrayList<PopularDomain> items = new ArrayList();
        items.add(new PopularDomain("T-shirt black", "item_1", 100, 4.5, 1, 20,"FABRIC: Cotton-polyester blend for perfect shape retention and easy maintenance.\n" +
                "PRODUCT DETAILS: Regular fit, featuring a ribbed knit collar and cuff, 2 -button placket, half sleeves and side slits for movement.\n" +
                "WORKMANSHIP: Crafted with high quality stitching and full Lycra collar & cuff for added comfort and durability.\n" +
                "STYLE TIPS: Pair with chinos and loafers for a smart-casual look or dress down with jeans and sneakers.\n" +
                "FIND YOUR PERFECT FIT: Please refer to the 5th image in our catalogue for a detailed size and fit guide.\n" +
                "SUSTAINABILITY COMMITMENT: This product is made in a certified factory that is committed to ethical labour practices. We use FSC certified tags and re-cycled packaging."));
        items.add(new PopularDomain("Smart Watch", "item_2", 200, 4.7, 2, 100,"Large 1.85-inch Touchscreen Display: Immerse yourself in vivid visuals and effortless navigation with a crystal-clear 1.85-inch display, designed for an enhanced user experience and seamless accessibility\n" +
                "Bluetooth Calling on the Go: Make and receive calls directly from your wrist with the built-in Bluetooth call feature, ensuring you stay connected wherever you are without reaching for your phone.\n" +
                "120+ Sports Modes: Achieve your fitness goals with over 120 sports modes, providing accurate tracking for activities like running, cycling, swimming, yoga, and more, tailored to your active lifestyle\n" +
                "Sleek Premium Metal Body: Flaunt a modern, durable metal body design that combines style and strength, making the smartwatch perfect for workouts, office settings, and casual wear\n" +
                "Comprehensive Health Monitoring: Stay on top of your health with features like Blood Oxygen (SpO2) monitoring, advanced heart rate tracking, and sleep analysis for a 24/7 wellness overview."));
        items.add(new PopularDomain("iPhone", "item_3", 150, 4.6, 3, 699,"DYNAMIC ISLAND COMES TO IPHONE 15 — Dynamic Island bubbles up alerts and Live Activities — so you don't miss them while you're doing something else. You can see who's calling, track your next ride, check your flight status, and so much more.\n" +
                "INNOVATIVE DESIGN — iPhone 15 features a durable color-infused glass and aluminum design. It's splash, water, and dust resistant. The Ceramic Shield front is tougher than any smartphone glass. And the 6.1\" Super Retina XDR display is up to 2x brighter in the sun compared to iPhone 14.\n" +
                "48MP MAIN CAMERA WITH 2X TELEPHOTO — The 48MP Main camera shoots in super-high resolution. So it's easier than ever to take standout photos with amazing detail. The 2x optical-quality Telephoto lets you frame the perfect close-up.\n" +
                "NEXT-GENERATION PORTRAITS — Capture portraits with dramatically more detail and color. Just tap to shift the focus between subjects — even after you take the shot.\n" +
                "POWERHOUSE A16 BIONIC CHIP — The superfast chip powers advanced features like computational photography, fluid Dynamic Island transitions, and Voice Isolation for phone calls. And A16 Bionic is incredibly efficient to help deliver great all-day battery life."));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }

    private void setupBottomNavigation() {
        // Profile button click listener
        View profileLayout = binding.getRoot().findViewById(R.id.profileLayout);
        if (profileLayout != null) {
            profileLayout.setOnClickListener(view -> {
                openProfilePage();
            });
        }

        // Wishlist button click listener
        View wishlistLayout = binding.getRoot().findViewById(R.id.wishlistLayout);
        if (wishlistLayout != null) {
            wishlistLayout.setOnClickListener(view -> {
                openWishlistPage();
            });
        }

        // You can also set click listeners directly on the imageView and textView if needed
        binding.imageView2.setOnClickListener(view -> {
            openProfilePage();
        });

        binding.textView100.setOnClickListener(view -> {
            openProfilePage();
        });
    }

    private void openWishlistPage() {
        Intent intent = new Intent(MainActivity.this, Wishlist.class);
        startActivity(intent);
    }

    private void openProfilePage() {
        Intent intent = new Intent(MainActivity.this, ProfilePage.class);
        startActivity(intent);
    }
}
package com.example.shoppingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Helper.ManagmentCart;
import com.example.shoppingapp.databinding.ActivityDetailBinding;
import com.example.shoppingapp.domain.PopularDomain;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles();
        managmentCart=new ManagmentCart(this);
    }

    private void getBundles() {
        object=(PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.itemPic);
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("$"+object.getPrice());
        binding.descriptionTxt.setText(object.getDescription());
        binding.reviewTxt.setText(object.getReview()+"");
        binding.ratingTxt.setText(object.getScore()+"");

        // Add to cart button functionality
        binding.addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managmentCart.insertFood(object);
            }
        });

        // Back button functionality
        binding.backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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

        // Add bookmark button functionality (optional)
        binding.bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement bookmark functionality if needed
                // For now, just show a message
                android.widget.Toast.makeText(DetailActivity.this, "Added to bookmarks", android.widget.Toast.LENGTH_SHORT).show();
            }
        });
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
}
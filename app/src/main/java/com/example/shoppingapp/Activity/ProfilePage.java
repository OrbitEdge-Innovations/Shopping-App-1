package com.example.shoppingapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.ActivityProfilePageBinding;

public class ProfilePage extends AppCompatActivity {
    private ActivityProfilePageBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the image picker launcher
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            // Load the selected image into the profile image view with circular crop
                            Glide.with(this)
                                    .load(selectedImageUri)
                                    .circleCrop()
                                    .into(binding.profileImage);

                            // Save image URI to shared preferences (optional)
                            saveProfileImageUri(selectedImageUri.toString());
                        }
                    }
                }
        );

        // Set up back button click listener
        binding.backBtn.setOnClickListener(view -> {
            finish(); // Go back to previous activity
        });

        // Set up profile image click for image selection
        binding.profileImage.setOnClickListener(view -> {
            openImagePicker();
        });

        // Load profile image if available
        loadProfileImage();
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
        Toast.makeText(this, "Select a profile picture", Toast.LENGTH_SHORT).show();
    }

    private void saveProfileImageUri(String uriString) {
        getSharedPreferences("UserProfile", MODE_PRIVATE)
                .edit()
                .putString("profileImageUri", uriString)
                .apply();
    }

    private void loadProfileImage() {
        // Check if we have a saved profile image
        String savedImageUri = getSharedPreferences("UserProfile", MODE_PRIVATE)
                .getString("profileImageUri", null);

        if (savedImageUri != null) {
            // Load the saved image with circular crop
            Glide.with(this)
                    .load(Uri.parse(savedImageUri))
                    .circleCrop()
                    .into(binding.profileImage);
        } else {
            // Load default profile image
            Glide.with(this)
                    .load(R.drawable.default_profile)
                    .circleCrop()
                    .into(binding.profileImage);
        }
    }
}
package com.example.shoppingapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.shoppingapp.domain.PopularDomain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManagmentWishlist {
    private Context context;
    private SharedPreferences sharedPreferences;
    private final String WISHLIST_KEY = "wishlist_items";

    public ManagmentWishlist(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Wishlist", Context.MODE_PRIVATE);
    }

    public void insertItem(PopularDomain item) {
        ArrayList<PopularDomain> wishlistList = getWishlistList();
        boolean existAlready = false;

        // Check if item already exists in wishlist
        for (int i = 0; i < wishlistList.size(); i++) {
            if (wishlistList.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                break;
            }
        }

        if (!existAlready) {
            wishlistList.add(item);
            saveWishlist(wishlistList);
            Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Already in wishlist", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeItem(PopularDomain item) {
        ArrayList<PopularDomain> wishlistList = getWishlistList();
        int itemPosition = -1;

        for (int i = 0; i < wishlistList.size(); i++) {
            if (wishlistList.get(i).getTitle().equals(item.getTitle())) {
                itemPosition = i;
                break;
            }
        }

        if (itemPosition != -1) {
            wishlistList.remove(itemPosition);
            saveWishlist(wishlistList);
            Toast.makeText(context, "Removed from wishlist", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<PopularDomain> getWishlistList() {
        String json = sharedPreferences.getString(WISHLIST_KEY, "");

        if (json.isEmpty()) {
            return new ArrayList<>();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<PopularDomain>>() {}.getType();
            return gson.fromJson(json, type);
        }
    }

    private void saveWishlist(ArrayList<PopularDomain> wishlistList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(wishlistList);
        editor.putString(WISHLIST_KEY, json);
        editor.apply();
    }

    public boolean isInWishlist(PopularDomain item) {
        ArrayList<PopularDomain> wishlistList = getWishlistList();

        for (PopularDomain wishlistItem : wishlistList) {
            if (wishlistItem.getTitle().equals(item.getTitle())) {
                return true;
            }
        }

        return false;
    }
}
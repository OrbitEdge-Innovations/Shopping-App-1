package com.example.shoppingapp.Activity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shoppingapp.Adapter.CartAdapter;
import com.example.shoppingapp.Helper.ChangeNumberItemsListener;
import com.example.shoppingapp.Helper.ManagmentCart;
import com.example.shoppingapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ManagmentCart managementCart;
    ActivityCartBinding binding;
    double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managementCart = new ManagmentCart(this);

        setVariable();
        initList();
    }

    private void initList() {
        if(managementCart.getListCart().isEmpty()){
            binding.textView2.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        }else{
            binding.textView2.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managementCart.getListCart(), () -> calculatorCart()));
    }

    private void calculatorCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round(managementCart.getTotalFee() * percentTax * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;
        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
    private void setVariable() {
        binding.backBin.setOnClickListener(v -> finish());
    }
}

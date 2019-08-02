package com.example.fetchshopdata;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView nameText = findViewById(R.id.itemName);
        TextView shortDesText = findViewById(R.id.itemShortDes);
        TextView priceText = findViewById(R.id.itemPrice);
        ImageView imgPath = findViewById(R.id.imgView);



        Bundle bundle = getIntent().getExtras();
        final Item item = bundle.getParcelable("com.example.fetchshopdata.Item");


        nameText.setText(item.getName());
        shortDesText.setText(item.getShortDescription());
        priceText.setText(String.valueOf(item.getPrice()));
        String url = "https://shopapp2.azurewebsites.net" + item.getImgPath();

        Picasso.with(getApplicationContext()).load(url).into(imgPath);


        Button fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sepete Eklendi!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });






    }

}

package com.example.marclazolatorre.betapp.Activity;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marclazolatorre.betapp.R;


public class MainActivity extends AppCompatActivity {

    Button goToBetClicSite;
    Button seeOdd;

    String betClicURL = "https://www.betclic.fr/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToBetClicSite = (Button) findViewById(R.id.betClic_Sit);
        seeOdd = (Button) findViewById(R.id.VoirCote);

        goToBetClicSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(betClicURL));
                startActivity(i);
            }
        });

        seeOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SportActivity.class);
                startActivity(intent);
            }
        });

    }


}

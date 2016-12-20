package com.example.marclazolatorre.betapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.marclazolatorre.betapp.R;

import com.example.marclazolatorre.betapp.Adapter.SportAdapter;
import com.example.marclazolatorre.betapp.Bet.Bet;
import com.example.marclazolatorre.betapp.SimpleDividerItemDecoration;
import com.example.marclazolatorre.betapp.xmlPullParser.BetsXmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */
public class BetsActivity extends Activity {

    private static final String TAG = EventActivity.class.getSimpleName();

    private List<Bet> bets = new ArrayList<>();

    private RecyclerView recyclerView;
    private SportAdapter sAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        bets = BetsXmlPullParser.getBetsFromFile(getApplicationContext(), name, null,true);
        final List<String> betsString = new ArrayList<>();

        for (Bet bet: bets) {
            betsString.add(bet.toString());
        }

        sAdapter = new SportAdapter(betsString);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String bet = betsString.get(position);
                Intent intent = new Intent(BetsActivity.this, BetActivity.class);
                intent.putExtra("teams", name);
                intent.putExtra("startParse", bet);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}

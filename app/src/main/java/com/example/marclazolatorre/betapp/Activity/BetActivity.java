package com.example.marclazolatorre.betapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.marclazolatorre.betapp.R;

import com.example.marclazolatorre.betapp.Adapter.BetAdapter;
import com.example.marclazolatorre.betapp.Bet.Bet;
import com.example.marclazolatorre.betapp.SimpleDividerItemDecoration;
import com.example.marclazolatorre.betapp.xmlPullParser.BetsXmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */
public class BetActivity extends Activity {

    private static final String TAG = BetActivity.class.getSimpleName();

    private List<Bet> bets = new ArrayList<>();

    private RecyclerView recyclerView;
    private BetAdapter bAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        final Intent intent = getIntent();
        String startParse = intent.getStringExtra("startParse");
        String teams = intent.getStringExtra("teams");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        bets = BetsXmlPullParser.getBetsFromFile(getApplicationContext(), startParse, teams, false);

        bAdapter = new BetAdapter(bets);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}

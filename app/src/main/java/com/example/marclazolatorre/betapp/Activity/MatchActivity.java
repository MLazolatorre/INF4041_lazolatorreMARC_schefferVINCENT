package com.example.marclazolatorre.betapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.marclazolatorre.betapp.R;

import com.example.marclazolatorre.betapp.Adapter.SportAdapter;
import com.example.marclazolatorre.betapp.SimpleDividerItemDecoration;
import com.example.marclazolatorre.betapp.xmlPullParser.SportXmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */
public class MatchActivity extends Activity {

    private static final String TAG = MatchActivity.class.getSimpleName();

    private List<String> sports = new ArrayList<>();

    private RecyclerView recyclerView;
    private SportAdapter sAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        sports = SportXmlPullParser.getSportFromFile(getApplicationContext(), name);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        sAdapter = new SportAdapter(sports);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String sport = sports.get(position);
                Intent intent = new Intent(MatchActivity.this, BetsActivity.class);
                intent.putExtra("name", sport);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}


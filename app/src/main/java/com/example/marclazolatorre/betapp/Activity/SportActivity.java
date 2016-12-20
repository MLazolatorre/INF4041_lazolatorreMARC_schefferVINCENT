package com.example.marclazolatorre.betapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marclazolatorre.betapp.Adapter.SportAdapter;
import com.example.marclazolatorre.betapp.R;

//import com.example.marclazolatorre.betapp.Adapter.SportAdapter;
import com.example.marclazolatorre.betapp.Downloader;
import com.example.marclazolatorre.betapp.SimpleDividerItemDecoration;
import com.example.marclazolatorre.betapp.xmlPullParser.SportXmlPullParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */
public class SportActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<String> sports = new ArrayList<>();

    private RecyclerView recyclerView;
    private SportAdapter sAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        BetsDownloadTask betsDownloadTask = new BetsDownloadTask();
        betsDownloadTask.execute();
    }

    private class BetsDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                Downloader.DownloadFromUrl(openFileOutput("Bets.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            Log.i(TAG, "Download finished");
                Toast.makeText(getApplicationContext(), getString(R.string.downlodFinished) , Toast.LENGTH_LONG).show();

            progressBar.setVisibility(View.INVISIBLE);

            sports = SportXmlPullParser.getSportFromFile(getApplicationContext());

            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

            sAdapter = new SportAdapter(sports);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(sAdapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    String sport = sports.get(position);
                    Intent intent = new Intent(SportActivity.this, EventActivity.class);
                    intent.putExtra("name", sport);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            Log.i(TAG, "AsyncTask finished");
        }
    }
}


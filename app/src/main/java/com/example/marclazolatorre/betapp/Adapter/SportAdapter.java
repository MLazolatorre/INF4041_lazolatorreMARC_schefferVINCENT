package com.example.marclazolatorre.betapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marclazolatorre.betapp.R;

import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 19/12/2016.
 */

public class SportAdapter extends RecyclerView.Adapter <SportAdapter.SportViewHolder> {

    private List<String> sportList;

    public class SportViewHolder extends RecyclerView.ViewHolder{
        TextView sport;

        public SportViewHolder(final View itemView) {
            super(itemView);
            sport = (TextView) itemView.findViewById(R.id.sport);
        }

        public void display (String tag){
            sport.setText(tag);
        }
    }

    public SportAdapter (List<String> sportList){ this.sportList = sportList; }

    @Override
    public SportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sport_list, parent, false);
        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportViewHolder holder, int position) {
        holder.display(sportList.get(position));
    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }
}

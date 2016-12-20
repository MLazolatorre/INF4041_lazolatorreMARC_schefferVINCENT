package com.example.marclazolatorre.betapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marclazolatorre.betapp.R;

import com.example.marclazolatorre.betapp.Bet.Bet;

import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */

public class BetAdapter extends RecyclerView.Adapter <BetAdapter.BetViewHolder>{

    private List<Bet> betList;

    public class BetViewHolder extends RecyclerView.ViewHolder{
        TextView bet, odd;

        public BetViewHolder(final View itemView) {
            super(itemView);
            bet = (TextView) itemView.findViewById(R.id.sport);
            odd = (TextView) itemView.findViewById(R.id.odd);
        }

        public void display (Bet bet){
            this.bet.setText(bet.getBet());
            this.odd.setText("odd : " + bet.getOdd());
        }
    }

    public BetAdapter (List<Bet> betList){ this.betList = betList; }

    @Override
    public BetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bet_list, parent, false);
        return new BetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BetViewHolder holder, int position) {
        holder.display(betList.get(position));
    }

    @Override
    public int getItemCount() {
        return betList.size();
    }
}

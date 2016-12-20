package com.example.marclazolatorre.betapp.Bet;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */

public class Bet {

    private String bet;
    private String odd;

    public Bet(String teams, String bet) {
        String[] teamsArray = teams.split(" - ", 2);
        this.bet = bet.replace("%1%", teamsArray[0]);

        if (teamsArray.length == 2) {
            this.bet = this.bet.replace("%2%", teamsArray[1]);
        }
    }

    public Bet(String teams, String bet, String odd) {
        String[] teamsArray = teams.split(" - ", 2);
        this.bet = bet.replace("%1%", teamsArray[0]);

        if (teamsArray.length == 2) {
            this.bet = this.bet.replace("%2%", teamsArray[1]);
        }
        this.odd = odd;
    }

    public String getBet() {
        return bet;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    @Override
    public String toString() {
        return bet;
    }
}

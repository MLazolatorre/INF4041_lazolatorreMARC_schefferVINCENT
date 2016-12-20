package com.example.marclazolatorre.betapp.xmlPullParser;

import android.content.Context;
import android.util.Log;

import com.example.marclazolatorre.betapp.xmlPullParser.SportXmlPullParser;

import com.example.marclazolatorre.betapp.Bet.Bet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 20/12/2016.
 */

public class BetsXmlPullParser {
    private static final String TAG = SportXmlPullParser.class.getSimpleName();

    static final String KEY_SPORT = "sport";
    static final String KEY_EVENT = "event";
    static final String KEY_MATCH = "match";
    static final String KEY_BETS = "bets";
    static final String KEY_BET = "bet";
    static final String KEY_CHOICE = "choice";


    public static List<Bet> getBetsFromFile(Context context, String startParse, String teams , boolean bet) {

        List<Bet> betsList = new ArrayList<>();
        String tagAsked = bet ? KEY_BET : KEY_CHOICE;
        String stopperTag = bet ? KEY_BETS : KEY_BET;

        Log.i(TAG, "Le startParse = " + startParse);

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            FileInputStream fis = context.openFileInput("Bets.xml");

            xpp.setInput(fis, "UTF-8");

            int eventType = xpp.next();

            String curName;
            String curTagName;
            String newTeams = teams;


            do {
                curTagName = xpp.getName();
                curName = xpp.getAttributeValue(null, "name");
                if (teams == null && eventType == XmlPullParser.START_TAG && curTagName.equalsIgnoreCase(KEY_MATCH)) {
                    newTeams = curName;
                }
                eventType = xpp.next();
            } while (curName == null || !curName.equalsIgnoreCase(startParse));

            if (bet == true)
                eventType = xpp.next();

            Log.i(TAG, "Le tag étudié est : " + tagAsked
                    + " et son 'name' est : " + xpp.getAttributeValue(null, "name")
                    + " et le tag d'arret est : " + stopperTag
                    + " les équipes qui jouent sont : " + newTeams);


            while (eventType != XmlPullParser.END_DOCUMENT) {

                curTagName = xpp.getName();
                curName = xpp.getAttributeValue(null, "name");

                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        if (curTagName.equalsIgnoreCase(tagAsked)) {
                            Bet newBet = new Bet(newTeams, curName);
                            if (curTagName.equalsIgnoreCase(KEY_CHOICE)) {
                                newBet.setOdd(xpp.getAttributeValue(null, "odd"));
                            }
                            betsList.add(newBet);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (curTagName.equalsIgnoreCase(stopperTag))
                            return betsList;
                        break;

                    default:
                        break;
                }

                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return betsList;
    }


}

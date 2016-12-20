package com.example.marclazolatorre.betapp.xmlPullParser;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 19/12/2016.
 */

public class SportXmlPullParser {

    private static final String TAG = SportXmlPullParser.class.getSimpleName();

    static final String KEY_SPORT   = "sport";
    static final String KEY_EVENT   = "event";
    static final String KEY_MATCH   = "match";
    static final String KEY_BETS    = "bets";
    static final String KEY_BET     = "bet";
    static final String KEY_CHOICE  = "choice";

    public static List<String> getSportFromFile(Context context) {
        return getSportFromFile(context,null);
    }

    public static List<String> getSportFromFile (Context context, String lastTagSelected){

        List<String> sportList = new ArrayList<>();

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            FileInputStream fis = context.openFileInput("Bets.xml");

            xpp.setInput(fis, "UTF-8");

            int eventType = xpp.next();

            String curName;
            String tagAsked;
            String stopperTag = xpp.getName();

            if (lastTagSelected != null){
                Log.i(TAG, "On va sauter des balises");

                do {
                    stopperTag = xpp.getName();
                    curName = xpp.getAttributeValue(null, "name");
                    eventType = xpp.next();
                }while (curName == null || !curName.equalsIgnoreCase(lastTagSelected));
            }
            else {
                eventType = xpp.next();
            }
            tagAsked = xpp.getName();

            Log.i(TAG, "Le tag étudié est : " + tagAsked + " et son 'name' est : " + xpp.getAttributeValue(null, "name") + " et le tag d'arret est : " + stopperTag);

            while (eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();


                switch (eventType){

                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(tagAsked)){
                            curName = xpp.getAttributeValue(null, "name");
                            sportList.add(curName);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(stopperTag))
                            return sportList;
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


        return sportList;
    }


}

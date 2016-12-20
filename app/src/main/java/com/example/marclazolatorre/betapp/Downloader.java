package com.example.marclazolatorre.betapp;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.net.URL;

/**
 * Created by MARC LAZOLA TORRE on 11/12/2016.
 */

public class Downloader {

    private static final String TAG = Downloader.class.getSimpleName();
    private static String urlString = "http://xml.cdn.betclic.com/odds_fr.xml";

    public static void Download(FileOutputStream fos) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                copyInputStreamToFile(conn.getInputStream(), fos);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyInputStreamToFile(InputStream in, FileOutputStream fos) {
        try {
            OutputStream out = fos;
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DownloadFromUrl(FileOutputStream fos) {  //this is the downloader method
        try {

            URL url = new URL(urlString); //URL of the file

            long startTime = System.currentTimeMillis();
            Log.d(TAG, "download begining");

            URLConnection ucon = url.openConnection();

            Log.i(TAG, "Opened Connection");

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.i(TAG, "Got InputStream and BufferedInputStream");

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Log.i(TAG, "Got FileOutputStream and BufferedOutputStream");

            byte data[] = new byte[1024];
            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            bos.close();

            Log.d(TAG, "download ready in "
                    + ((System.currentTimeMillis() - startTime))
                    + " milisec");
        } catch (IOException e) {
            Log.d(TAG, "Error: " + e);
        }
    }
}
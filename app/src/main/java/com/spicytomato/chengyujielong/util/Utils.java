package com.spicytomato.chengyujielong.util;

import android.util.Log;

import com.spicytomato.chengyujielong.Idiom;
import com.spicytomato.chengyujielong.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static Idiom[] getWords(String jsonWords) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonWords);
        Idiom[] idioms = new Idiom[jsonArray.length()];

        int j = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject word = jsonArray.getJSONObject(i);
            String idiom = word.getString("word");
            if (idiom.length() == 4){
                idioms[j] = new Idiom(idiom.charAt(0),idiom.charAt(1),idiom.charAt(2),idiom.charAt(3),idiom);
                j++;
            }
        }
        return idioms;
    }
}

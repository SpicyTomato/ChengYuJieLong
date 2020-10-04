package com.spicytomato.chengyujielong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.spicytomato.chengyujielong.util.Utils;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[][] mWords;
    private boolean[][] mHasExisted;
    private Idiom[] mIdioms;

    private final static int WIDTH = 12;
    private final static int Height = 9;
    private List<String> mCurrentWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringBuilder stringBuilder = new StringBuilder();
        InputStream stream = getResources().openRawResource(R.raw.idiom);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(line);
        }

        Log.d("TAG", "readJsonFile: " + stringBuilder.toString());

        try {
            mIdioms = Utils.getWords(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mWords = new String[12][10];
        mHasExisted = new boolean[12][10];

        int j = 0;

        Random random = new Random();
        int target = random.nextInt(mIdioms.length);
        for (int i = 4; i <= 7; i++) {

            mWords[i][4] = String.valueOf(mIdioms[target].idiom.charAt(j));
            Log.d("", "123: " +mIdioms[target].idiom.charAt(j));
            j++;

        }
        Log.d("TAG", "idiom: " + mIdioms[target].idiom);


//        int result = Judge.selectNumber(WIDTH, Height, Judge.HORIZONTAL, 4, 4, mWords);


        generate(4, 4, Judge.HORIZONTAL);
        generate(6, 4, Judge.HORIZONTAL);


        Log.d("TAG", "Words: " + mWords.toString());
    }

    public void generate(int x, int y, boolean orientation) {
        mHasExisted[x][y] = true;
        int result = Judge.selectNumber(WIDTH, Height, orientation, x, y, mWords);

        Log.d("TAG", "result: " + result);
        if (result == -1) {
            return;
        }
        int[] cross = paint(x, y, result, !orientation);

        Log.d("TAG", "generate1: " + cross[0] + cross[1]);
        generate(cross[0], cross[1], !orientation);
//        if (!orientation == Judge.HORIZONTAL) {
//            if (mHasExisted[cross[0]][cross[1] + 1] && mWords[cross[0]][cross[1] + 1] != null) {
//                generate(cross[0], cross[1] + 1 ,!orientation);
//            }
//        }
    }

    /**
     * @param x
     * @param y           交叉点的坐标
     * @param result
     * @param orientation
     * @return 返回生成下一个成语的位置
     */

    public int[] paint(int x, int y, int result, boolean orientation) {

        int[] next = new int[2];
        String word = null;
        Log.d("TAG", "words x y : " + mWords[x][y] + "result :" + result);
        if (orientation) {
            switch (result) {
                case 1:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.one).equals(mWords[x][y])) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                        }
                    }
                    mWords[x][y] = String.valueOf(word.charAt(0));
                    mWords[x + 1][y] = String.valueOf(word.charAt(1));
                    mWords[x + 2][y] = String.valueOf(word.charAt(2));
                    mWords[x + 3][y] = String.valueOf(word.charAt(3));

                    x = x + 3;
                    break;
                case 2:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.two).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                        }
                    }
                    mWords[x - 1][y] = String.valueOf(word.charAt(0));
                    mWords[x][y] = String.valueOf(word.charAt(1));
                    mWords[x + 1][y] = String.valueOf(word.charAt(2));
                    mWords[x + 2][y] = String.valueOf(word.charAt(3));
                    x = x + 2;
                    break;

                case 3:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.three).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                        }
                    }
                    mWords[x - 2][y] = String.valueOf(word.charAt(0));
                    mWords[x - 1][y] = String.valueOf(word.charAt(1));
                    mWords[x][y] = String.valueOf(word.charAt(2));
                    mWords[x + 1][y] = String.valueOf(word.charAt(3));
                    x = x - 2;
                    break;
                case 4:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.four).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                    }
                    mWords[x - 3][y] = String.valueOf(word.charAt(0));
                    mWords[x - 2][y] = String.valueOf(word.charAt(1));
                    mWords[x - 1][y] = String.valueOf(word.charAt(2));
                    mWords[x][y] = String.valueOf(word.charAt(3));
                    x = x - 3;
                    break;
            }
        } else {
            switch (result) {
                case 1:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.one).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                    }
                    mWords[x][y] = String.valueOf(word.charAt(0));
                    mWords[x][y + 1] = String.valueOf(word.charAt(1));
                    mWords[x][y + 2] = String.valueOf(word.charAt(2));
                    mWords[x][y + 3] = String.valueOf(word.charAt(3));

                    y = y + 3;
                    break;
                case 2:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.two).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                        }
                    }
                    mWords[x][y - 1] = String.valueOf(word.charAt(0));
                    mWords[x][y] = String.valueOf(word.charAt(1));
                    mWords[x][y + 1] = String.valueOf(word.charAt(2));
                    mWords[x][y + 2] = String.valueOf(word.charAt(3));
                    y = y + 2;
                    break;

                case 3:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.three).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                    }
                    mWords[x][y - 2] = String.valueOf(word.charAt(0));
                    mWords[x][y - 1] = String.valueOf(word.charAt(1));
                    mWords[x][y] = String.valueOf(word.charAt(2));
                    mWords[x][y + 1] = String.valueOf(word.charAt(3));
                    y = y - 2;
                    break;
                case 4:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.four).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)){
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue){
                                    break;
                                }
                            }
                    }
                    mWords[x][y - 3] = String.valueOf(word.charAt(0));
                    mWords[x][y - 2] = String.valueOf(word.charAt(1));
                    mWords[x][y - 1] = String.valueOf(word.charAt(2));
                    mWords[x][y] = String.valueOf(word.charAt(3));
                    y = y - 3;
                    break;
            }
        }

        mCurrentWords.add(word);


        Log.d("TAG", "paint: " + word);
        next[0] = x;
        next[1] = y;
        return next;
    }


}

package com.spicytomato.chengyujielong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String[][] mWords;
    private boolean[][] mHasExisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWords = new String[12][9];
        mHasExisted = new boolean[12][9];

        for (int i = 4; i <= 7; i++) {
            mWords[4][i] = "1";
        }


        int result = Judge.selectNumber(12, 9, Judge.HORIZONTAL, 4, 4, mWords);


//        generate(4, 4);
    }

    public void generate(int x, int y, boolean orientation) {
        mHasExisted[x][y] = true;
        int result = Judge.selectNumber(12, 9, orientation, x, y, mWords);
        int[] cross = paint(result, !orientation);
        generate(cross[0], cross[1], !orientation);
//        if (!orientation == Judge.HORIZONTAL) {
//            if (mHasExisted[cross[0]][cross[1] + 1] && mWords[cross[0]][cross[1] + 1] != null) {
//                generate(cross[0], cross[1] + 1 ,!orientation);
//            }
//        }
    }

    /**
     * @param result
     * @param orientation
     * @return 返回生成下一个成语的位置
     */

    public int[] paint(int result, boolean orientation) {

        return new int[2];
    }


}

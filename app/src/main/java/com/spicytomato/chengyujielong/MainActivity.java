package com.spicytomato.chengyujielong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private boolean[][] mHasExisted = {{false}};
    private Idiom[] mIdioms;

    private final static int WIDTH = 12;
    private final static int Height = 9;
    private List<String> mCurrentWords = new ArrayList<>();
    Button mButton00;
    Button mButton01;
    Button mButton02;
    Button mButton03;
    Button mButton04;
    Button mButton05;
    Button mButton06;
    Button mButton07;
    Button mButton08;
    Button mButton09;
    Button mButton010;
    Button mButton011;
    Button mButton10;
    Button mButton11;
    Button mButton12;
    Button mButton13;
    Button mButton14;
    Button mButton15;
    Button mButton16;
    Button mButton17;
    Button mButton18;
    Button mButton19;
    Button mButton110;
    Button mButton111;
    Button mButton20;
    Button mButton21;
    Button mButton22;
    Button mButton23;
    Button mButton24;
    Button mButton25;
    Button mButton26;
    Button mButton27;
    Button mButton28;
    Button mButton29;
    Button mButton210;
    Button mButton211;
    Button mButton30;
    Button mButton31;
    Button mButton32;
    Button mButton33;
    Button mButton34;
    Button mButton35;
    Button mButton36;
    Button mButton37;
    Button mButton38;
    Button mButton39;
    Button mButton310;
    Button mButton311;
    Button mButton40;
    Button mButton41;
    Button mButton42;
    Button mButton43;
    Button mButton44;
    Button mButton45;
    Button mButton46;
    Button mButton47;
    Button mButton48;
    Button mButton49;
    Button mButton410;
    Button mButton411;
    Button mButton50;
    Button mButton51;
    Button mButton52;
    Button mButton53;
    Button mButton54;
    Button mButton55;
    Button mButton56;
    Button mButton57;
    Button mButton58;
    Button mButton59;
    Button mButton510;
    Button mButton511;
    Button mButton60;
    Button mButton61;
    Button mButton62;
    Button mButton63;
    Button mButton64;
    Button mButton65;
    Button mButton66;
    Button mButton67;
    Button mButton68;
    Button mButton69;
    Button mButton610;
    Button mButton611;
    Button mButton70;
    Button mButton71;
    Button mButton72;
    Button mButton73;
    Button mButton74;
    Button mButton75;
    Button mButton76;
    Button mButton77;
    Button mButton78;
    Button mButton79;
    Button mButton710;
    Button mButton711;
    Button mButton80;
    Button mButton81;
    Button mButton82;
    Button mButton83;
    Button mButton84;
    Button mButton85;
    Button mButton86;
    Button mButton87;
    Button mButton88;
    Button mButton89;
    Button mButton810;
    Button mButton811;
    private Button mButtonWord1;
    private Button mButtonWord2;
    private Button mButtonWord3;
    private Button mButtonWord4;
    private List<Button> mButtonList;
    private List<Button> mAnswerList;
    private String[][] mUserAnswer = new String[WIDTH][Height];
    private Button mButtonCommit;
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent(this);

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


        mWords = new String[WIDTH][Height];
        mHasExisted = new boolean[WIDTH][Height];

        int j = 0;

        Random random = new Random();
        int target = random.nextInt(mIdioms.length);
        for (int i = 4; i <= 7; i++) {

            mWords[i][4] = String.valueOf(mIdioms[target].idiom.charAt(j));
            Log.d("", "123: " + target + " " + mIdioms[target].idiom.charAt(j));
            j++;

        }
        Log.d("TAG", "idiom: " + mIdioms[target].idiom);


//        int result = Judge.selectNumber(WIDTH, Height, Judge.HORIZONTAL, 4, 4, mWords);


        generate(4, 4, Judge.HORIZONTAL);
        generate(6, 4, Judge.HORIZONTAL);


        Log.d("TAG", "Words: " + mWords.toString());

//        setButtonsEnabled();

        show();

        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < Height; k++) {
                Log.d("TAG", "mwords: " + i + k + mWords[i][k]);
            }
        }
    }

    private void show() {
        mButtonList.get(12 + 1 + 1 + 1 * 12).setVisibility(View.INVISIBLE);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < Height; j++) {
                if (mWords[i][j] != null) {
                    mButtonList.get(12 + i + 1 + j * 12).setVisibility(View.VISIBLE);
//                    mButtonList.get(12 +i + 1 + j * 12).setText(mWords[i][j]);
                }
                if (mUserAnswer[i][j] != null) {
                    mButtonList.get(12 + i + 1 + j * 12).setEnabled(false);
                    mButtonList.get(12 + i + 1 + j * 12).setText(mUserAnswer[i][j]);
                }

            }
        }
    }

    private void initEvent(final Context context) {
        Listener listener = new Listener();
        for (Button button :
                mButtonList) {
            button.setOnClickListener(listener);
        }

        for (Button button :
                mAnswerList) {
            button.setOnClickListener(listener);
        }

        mButtonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserAnswer == mWords) {
                    Toast.makeText(context, "恭喜你全部答对", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void setButtonsEnabled() {
//        int num = 0;
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < Height; j++) {
//                if (mWords[i][j] != null) {
//                    mButtonList.get(num).setEnabled(true);
//                }
//                num++;
//            }
//        }
//    }

    private void initView() {
        mButton00 = findViewById(R.id.b00);
        mButton01 = findViewById(R.id.b01);
        mButton02 = findViewById(R.id.b02);
        mButton03 = findViewById(R.id.b03);
        mButton04 = findViewById(R.id.b04);
        mButton05 = findViewById(R.id.b05);
        mButton06 = findViewById(R.id.b06);
        mButton07 = findViewById(R.id.b07);
        mButton08 = findViewById(R.id.b08);
        mButton09 = findViewById(R.id.b09);
        mButton010 = findViewById(R.id.b010);
        mButton011 = findViewById(R.id.b011);
        mButton10 = findViewById(R.id.b10);
        mButton11 = findViewById(R.id.b11);
        mButton12 = findViewById(R.id.b12);
        mButton13 = findViewById(R.id.b13);
        mButton14 = findViewById(R.id.b14);
        mButton15 = findViewById(R.id.b15);
        mButton16 = findViewById(R.id.b16);
        mButton17 = findViewById(R.id.b17);
        mButton18 = findViewById(R.id.b18);
        mButton19 = findViewById(R.id.b19);
        mButton110 = findViewById(R.id.b110);
        mButton111 = findViewById(R.id.b111);
        mButton20 = findViewById(R.id.b20);
        mButton21 = findViewById(R.id.b21);
        mButton22 = findViewById(R.id.b22);
        mButton23 = findViewById(R.id.b23);
        mButton24 = findViewById(R.id.b24);
        mButton25 = findViewById(R.id.b25);
        mButton26 = findViewById(R.id.b26);
        mButton27 = findViewById(R.id.b27);
        mButton28 = findViewById(R.id.b28);
        mButton29 = findViewById(R.id.b29);
        mButton210 = findViewById(R.id.b210);
        mButton211 = findViewById(R.id.b211);
        mButton30 = findViewById(R.id.b30);
        mButton31 = findViewById(R.id.b31);
        mButton32 = findViewById(R.id.b32);
        mButton33 = findViewById(R.id.b33);
        mButton34 = findViewById(R.id.b34);
        mButton35 = findViewById(R.id.b35);
        mButton36 = findViewById(R.id.b36);
        mButton37 = findViewById(R.id.b37);
        mButton38 = findViewById(R.id.b38);
        mButton39 = findViewById(R.id.b39);
        mButton310 = findViewById(R.id.b310);
        mButton311 = findViewById(R.id.b311);
        mButton40 = findViewById(R.id.b40);
        mButton41 = findViewById(R.id.b41);
        mButton42 = findViewById(R.id.b42);
        mButton43 = findViewById(R.id.b43);
        mButton44 = findViewById(R.id.b44);
        mButton45 = findViewById(R.id.b45);
        mButton46 = findViewById(R.id.b46);
        mButton47 = findViewById(R.id.b47);
        mButton48 = findViewById(R.id.b48);
        mButton49 = findViewById(R.id.b49);
        mButton410 = findViewById(R.id.b410);
        mButton411 = findViewById(R.id.b411);
        mButton50 = findViewById(R.id.b50);
        mButton51 = findViewById(R.id.b51);
        mButton52 = findViewById(R.id.b52);
        mButton53 = findViewById(R.id.b53);
        mButton54 = findViewById(R.id.b54);
        mButton55 = findViewById(R.id.b55);
        mButton56 = findViewById(R.id.b56);
        mButton57 = findViewById(R.id.b57);
        mButton58 = findViewById(R.id.b58);
        mButton59 = findViewById(R.id.b59);
        mButton510 = findViewById(R.id.b510);
        mButton511 = findViewById(R.id.b511);
        mButton60 = findViewById(R.id.b60);
        mButton61 = findViewById(R.id.b61);
        mButton62 = findViewById(R.id.b62);
        mButton63 = findViewById(R.id.b63);
        mButton64 = findViewById(R.id.b64);
        mButton65 = findViewById(R.id.b65);
        mButton66 = findViewById(R.id.b66);
        mButton67 = findViewById(R.id.b67);
        mButton68 = findViewById(R.id.b68);
        mButton69 = findViewById(R.id.b69);
        mButton610 = findViewById(R.id.b610);
        mButton611 = findViewById(R.id.b611);
        mButton70 = findViewById(R.id.b70);
        mButton71 = findViewById(R.id.b71);
        mButton72 = findViewById(R.id.b72);
        mButton73 = findViewById(R.id.b73);
        mButton74 = findViewById(R.id.b74);
        mButton75 = findViewById(R.id.b75);
        mButton76 = findViewById(R.id.b76);
        mButton77 = findViewById(R.id.b77);
        mButton78 = findViewById(R.id.b78);
        mButton79 = findViewById(R.id.b79);
        mButton710 = findViewById(R.id.b710);
        mButton711 = findViewById(R.id.b711);
        mButton80 = findViewById(R.id.b80);
        mButton81 = findViewById(R.id.b81);
        mButton82 = findViewById(R.id.b82);
        mButton83 = findViewById(R.id.b83);
        mButton84 = findViewById(R.id.b84);
        mButton85 = findViewById(R.id.b85);
        mButton86 = findViewById(R.id.b86);
        mButton87 = findViewById(R.id.b87);
        mButton88 = findViewById(R.id.b88);
        mButton89 = findViewById(R.id.b89);
        mButton810 = findViewById(R.id.b810);
        mButton811 = findViewById(R.id.b811);

        mButtonWord1 = findViewById(R.id.word1);
        mButtonWord2 = findViewById(R.id.word2);
        mButtonWord3 = findViewById(R.id.word3);
        mButtonWord4 = findViewById(R.id.word4);

        mButtonCommit = findViewById(R.id.commit);

        mButtonList = new ArrayList<>();

        mButtonList.add(0, mButton00);
        mButtonList.add(1, mButton01);
        mButtonList.add(2, mButton02);
        mButtonList.add(3, mButton03);
        mButtonList.add(4, mButton04);
        mButtonList.add(5, mButton05);
        mButtonList.add(6, mButton06);
        mButtonList.add(7, mButton07);
        mButtonList.add(8, mButton08);
        mButtonList.add(9, mButton09);
        mButtonList.add(10, mButton010);
        mButtonList.add(11, mButton011);

        mButtonList.add(12, mButton00);
        mButtonList.add(13, mButton01);
        mButtonList.add(14, mButton02);
        mButtonList.add(15, mButton03);
        mButtonList.add(16, mButton04);
        mButtonList.add(17, mButton05);
        mButtonList.add(18, mButton06);
        mButtonList.add(19, mButton07);
        mButtonList.add(20, mButton08);
        mButtonList.add(21, mButton09);
        mButtonList.add(22, mButton010);
        mButtonList.add(23, mButton011);

        mButtonList.add(24, mButton10);
        mButtonList.add(25, mButton11);
        mButtonList.add(26, mButton12);
        mButtonList.add(27, mButton13);
        mButtonList.add(28, mButton14);
        mButtonList.add(29, mButton15);
        mButtonList.add(30, mButton16);
        mButtonList.add(31, mButton17);
        mButtonList.add(32, mButton18);
        mButtonList.add(33, mButton19);
        mButtonList.add(34, mButton110);
        mButtonList.add(35, mButton111);

        mButtonList.add(36, mButton20);
        mButtonList.add(37, mButton21);
        mButtonList.add(38, mButton22);
        mButtonList.add(39, mButton23);
        mButtonList.add(40, mButton24);
        mButtonList.add(41, mButton25);
        mButtonList.add(42, mButton26);
        mButtonList.add(43, mButton27);
        mButtonList.add(44, mButton28);
        mButtonList.add(45, mButton29);
        mButtonList.add(46, mButton210);
        mButtonList.add(47, mButton211);

        mButtonList.add(48, mButton30);
        mButtonList.add(49, mButton31);
        mButtonList.add(50, mButton32);
        mButtonList.add(51, mButton33);
        mButtonList.add(52, mButton34);
        mButtonList.add(53, mButton35);
        mButtonList.add(54, mButton36);
        mButtonList.add(55, mButton37);
        mButtonList.add(56, mButton38);
        mButtonList.add(57, mButton39);
        mButtonList.add(58, mButton310);
        mButtonList.add(59, mButton311);

        mButtonList.add(60, mButton40);
        mButtonList.add(61, mButton41);
        mButtonList.add(62, mButton42);
        mButtonList.add(63, mButton43);
        mButtonList.add(64, mButton44);
        mButtonList.add(65, mButton45);
        mButtonList.add(66, mButton46);
        mButtonList.add(67, mButton47);
        mButtonList.add(68, mButton48);
        mButtonList.add(69, mButton49);
        mButtonList.add(70, mButton410);
        mButtonList.add(71, mButton411);

        mButtonList.add(72, mButton50);
        mButtonList.add(73, mButton51);
        mButtonList.add(74, mButton52);
        mButtonList.add(75, mButton53);
        mButtonList.add(76, mButton54);
        mButtonList.add(77, mButton55);
        mButtonList.add(78, mButton56);
        mButtonList.add(79, mButton57);
        mButtonList.add(80, mButton58);
        mButtonList.add(81, mButton59);
        mButtonList.add(82, mButton510);
        mButtonList.add(83, mButton511);

        mButtonList.add(84, mButton60);
        mButtonList.add(85, mButton61);
        mButtonList.add(86, mButton62);
        mButtonList.add(87, mButton63);
        mButtonList.add(88, mButton64);
        mButtonList.add(89, mButton65);
        mButtonList.add(90, mButton66);
        mButtonList.add(91, mButton67);
        mButtonList.add(92, mButton68);
        mButtonList.add(93, mButton69);
        mButtonList.add(94, mButton610);
        mButtonList.add(95, mButton611);

        mButtonList.add(96, mButton70);
        mButtonList.add(97, mButton71);
        mButtonList.add(98, mButton72);
        mButtonList.add(99, mButton73);
        mButtonList.add(100, mButton74);
        mButtonList.add(101, mButton75);
        mButtonList.add(102, mButton76);
        mButtonList.add(103, mButton77);
        mButtonList.add(104, mButton78);
        mButtonList.add(105, mButton79);
        mButtonList.add(106, mButton710);
        mButtonList.add(107, mButton711);

        mButtonList.add(108, mButton80);
        mButtonList.add(109, mButton81);
        mButtonList.add(110, mButton82);
        mButtonList.add(111, mButton83);
        mButtonList.add(112, mButton84);
        mButtonList.add(113, mButton85);
        mButtonList.add(114, mButton86);
        mButtonList.add(115, mButton87);
        mButtonList.add(116, mButton88);
        mButtonList.add(117, mButton89);
        mButtonList.add(118, mButton810);
        mButtonList.add(119, mButton811);

        mAnswerList = new ArrayList<>();

        mAnswerList.add(0, mButtonWord1);
        mAnswerList.add(1, mButtonWord2);
        mAnswerList.add(2, mButtonWord3);
        mAnswerList.add(3, mButtonWord4);

    }

    public class Listener implements View.OnClickListener {


        private int mY;
        private int mX;
        private int mPosition;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.word1:
                    mButtonList.get(mPosition - 1).setText(mButtonWord1.getText());
                    mUserAnswer[mX][mY] = String.valueOf(mButtonWord1.getText());
                    break;
                case R.id.word2:
                    mButtonList.get(mPosition - 1).setText(mButtonWord2.getText());
                    mUserAnswer[mX][mY] = String.valueOf(mButtonWord2.getText());
                    break;
                case R.id.word3:
                    mButtonList.get(mPosition - 1).setText(mButtonWord3.getText());
                    mUserAnswer[mX][mY] = String.valueOf(mButtonWord3.getText());
                    break;
                case R.id.word4:
                    mButtonList.get(mPosition - 1).setText(mButtonWord4.getText());
                    mUserAnswer[mX][mY] = String.valueOf(mButtonWord4.getText());
                    break;
                default:
                    mPosition = idiomClick(v.getId());
                    int mY = mPosition / WIDTH - 1;
                    mX = mPosition % WIDTH - 1;

                    setRandomAnswer();

                    Random random = new Random();
                    int ran = random.nextInt(4);
//                    boolean need = true;
//                    for (Button bu :
//                            mAnswerList) {
//                        if (bu.getText().equals(mWords[mX][mY])) {
//                            need = false;
//                        }
//                    }
//                    if (need)
                    mAnswerList.get(ran).setText(mWords[mX][mY]);
                    break;

            }
        }

        private void setRandomAnswer() {
            Random random = new Random();

            for (int i = 0; i < 4; i++) {
                int random_1 = random.nextInt(mCurrentWords.size());
                mAnswerList.get(i).setText(String.valueOf(mCurrentWords.get(random_1).charAt(random.nextInt(4))));
            }
        }

        private int idiomClick(int id) {
            int position = 1;
            for (Button button :
                    mButtonList) {
                if (button.getId() == id) {
                    break;
                }
                position++;
            }
            return position;
        }

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
        Log.d("TAG", "words x y : " + x + y + mWords[x][y] + "result :" + result);
        mUserAnswer[x][y] = mWords[x][y];
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
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                        }
                    }
                    if (word != null) {
                        mWords[x][y] = String.valueOf(word.charAt(0));
                        mWords[x + 1][y] = String.valueOf(word.charAt(1));
                        mWords[x + 2][y] = String.valueOf(word.charAt(2));
                        mWords[x + 3][y] = String.valueOf(word.charAt(3));


                        x = x + 3;
                    }
                    break;
                case 2:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.two).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                        }
                    }
                    if (word != null) {
                        mWords[x - 1][y] = String.valueOf(word.charAt(0));
                        mWords[x][y] = String.valueOf(word.charAt(1));
                        mWords[x + 1][y] = String.valueOf(word.charAt(2));
                        mWords[x + 2][y] = String.valueOf(word.charAt(3));
                        x = x + 2;
                    }
                    break;

                case 3:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.three).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                        }
                    }
                    if (word != null) {
                        mWords[x - 2][y] = String.valueOf(word.charAt(0));
                        mWords[x - 1][y] = String.valueOf(word.charAt(1));
                        mWords[x][y] = String.valueOf(word.charAt(2));
                        mWords[x + 1][y] = String.valueOf(word.charAt(3));
                        x = x - 2;
                    }
                    break;
                case 4:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.four).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                    }
                    if (word != null) {
                        mWords[x - 3][y] = String.valueOf(word.charAt(0));
                        mWords[x - 2][y] = String.valueOf(word.charAt(1));
                        mWords[x - 1][y] = String.valueOf(word.charAt(2));
                        mWords[x][y] = String.valueOf(word.charAt(3));
                        x = x - 3;
                    }
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
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                    }
                    if (word != null) {
                        mWords[x][y] = String.valueOf(word.charAt(0));
                        mWords[x][y + 1] = String.valueOf(word.charAt(1));
                        mWords[x][y + 2] = String.valueOf(word.charAt(2));
                        mWords[x][y + 3] = String.valueOf(word.charAt(3));

                        y = y + 3;
                    }
                    break;
                case 2:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null) {

                            if (String.valueOf(idiom.two).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                        }
                    }
                    if (word != null) {
                        mWords[x][y - 1] = String.valueOf(word.charAt(0));
                        mWords[x][y] = String.valueOf(word.charAt(1));
                        mWords[x][y + 1] = String.valueOf(word.charAt(2));
                        mWords[x][y + 2] = String.valueOf(word.charAt(3));
                        y = y + 2;
                    }
                    break;

                case 3:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.three).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                    }
                    if (word != null) {
                        mWords[x][y - 2] = String.valueOf(word.charAt(0));
                        mWords[x][y - 1] = String.valueOf(word.charAt(1));
                        mWords[x][y] = String.valueOf(word.charAt(2));
                        mWords[x][y + 1] = String.valueOf(word.charAt(3));
                        y = y - 2;
                    }
                    break;
                case 4:
                    for (Idiom idiom : mIdioms) {
                        if (idiom != null)
                            if (String.valueOf(idiom.four).equals(mWords[x][y]) && idiom != null) {
                                word = idiom.idiom;
                                boolean isContinue = false;
                                for (String word1 :
                                        mCurrentWords) {
                                    if (word1.equals(word)) {
                                        isContinue = true;
                                    }
                                }
                                if (!isContinue) {
                                    break;
                                }
                            }
                    }
                    if (word != null) {
                        mWords[x][y - 3] = String.valueOf(word.charAt(0));
                        mWords[x][y - 2] = String.valueOf(word.charAt(1));
                        mWords[x][y - 1] = String.valueOf(word.charAt(2));
                        mWords[x][y] = String.valueOf(word.charAt(3));
                        y = y - 3;
                    }
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

package com.spicytomato.chengyujielong;

import java.util.Random;

public class Judge {

    public final static boolean HORIZONTAL = true;
    public final static boolean VERTICAL = false;


    /**
     *
     * @param width 整个地图的宽
     * @param height 整个地图的高
     * @param preOrientation 被选中成语的当前状态
     * @param preX 相同字的横坐标
     * @param preY 相同字的纵坐标
     * @param words 二位字符串数组
     * @return
     */
    public static int selectNumber(int width, int height, boolean preOrientation, int preX, int preY, String[][] words) {
        int y_top = 0;
        int x_left = 0;
        int y_bottom = 0;
        int x_right = 0;
        int result = -1;
        int length = 0;


        if (preOrientation == HORIZONTAL) {
            for (int i = preY - 1; i >= 0; i--) {
                if (words[preX][i] == null) {
                    x_left++;
                    continue;
                } else {
                    break;
                }
            }
            for (int i = preY + 1; i <= height - 1; i++) {
                if (words[preX][i] == null) {
                    x_right++;
                    continue;
                } else {
                    break;
                }
            }
            length = x_left + x_right;
            System.out.println("length "  + length);
            Random random = new Random();
            if (length == 3) {
                switch (x_left) {
                    case 0:
                        result = 1;
                        break;
                    case 1:
                        result = 2;
                        break;
                    case 2:
                        result = 3;
                        break;
                    case 3:
                        result = 4;
                        break;
                    default:
                        result = -1;
                }
            } else if (length > 3) {
                int rest = length - 3;
                switch (x_left) {
                    case 0:
                        result = 1;
                        break;
                    case 1:
                        result = random.nextInt(2) + 1;
                        break;
                    case 2:
                        if (rest > 1) {
                            result = random.nextInt(3) + 1;
                        } else {
                            result = 2 + random.nextInt(2) + 1;
                        }
                        break;
                    default:
                        if (x_right == 0) {
                            result = 4;
                        }
                        if (x_right == 1) {
                            result = 2 + random.nextInt(2) + 1;
                        }
                        if (x_right == 2) {
                            result = 1 + random.nextInt(3) + 1;
                        }
                        if (x_right >= 3) {
                            result = random.nextInt(4) + 1;
                        }
                        break;
                }
            }

            return result;

        }

        if (preOrientation == VERTICAL) {
            for (int i = preX - 1; i >= 0; i--) {
                if (words[i][preY] == null) {
                    y_top++;
                    continue;
                } else {
                    break;
                }
            }
            for (int i = preX + 1; i <= height - 1; i++) {
                if (words[i][preY] == null) {
                    y_bottom++;
                    continue;
                } else {
                    break;
                }
            }
            length = y_top + y_bottom;
            System.out.println("length "  + length + y_top + y_bottom);
            Random random = new Random();
            if (length == 3) {
                switch (y_top) {
                    case 0:
                        result = 1;
                        break;
                    case 1:
                        result = 2;
                        break;
                    case 2:
                        result = 3;
                        break;
                    case 3:
                        result = 4;
                        break;
                    default:
                        result = -1;
                }
            } else if (length > 3) {
                int rest = length - 3;
                switch (y_top) {
                    case 0:
                        result = 1;
                        break;
                    case 1:
                        result = random.nextInt(2) + 1;
                        break;
                    case 2:
                        if (rest > 1) {
                            result = random.nextInt(3) + 1;
                        } else {
                            result = 2 + random.nextInt(2) + 1;
                        }
                        break;
                    default:
                        if (y_bottom == 0) {
                            result = 4;
                        }
                        if (y_bottom == 1) {
                            result = 2 + random.nextInt(2) + 1;
                        }
                        if (y_bottom == 2) {
                            result = 1 + random.nextInt(3) + 1;
                        }
                        if (y_bottom >= 3) {
                            result = random.nextInt(4) + 1;
                            System.out.println(result);
                        }
                        break;
                }
            }

            return result;
        }

        return -1;
    }
}

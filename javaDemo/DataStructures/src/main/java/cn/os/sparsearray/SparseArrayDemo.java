package cn.os.sparsearray;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author ou shuo
 * @date 2021/6/1 9:03
 * @description 稀疏数组
 */
public class SparseArrayDemo {
    private static final int rowLen = 11;
    private static final int columnLen = 11;

    /**
     * 模拟五子棋 1：黑子  2：白子
     */
    @Test
    public void v1SparseArrayDemo() {
        // 1、创建一个二维数组
        int[][] chessArr = new int[rowLen][columnLen];
        // 2、创建黑白子
        chessArr[5][5] = 2;
        chessArr[6][6] = 1;
        chessArr[4][6] = 2;
        chessArr[6][4] = 1;
        // 3、打印原始的二维数组
        printTwoArray(chessArr);
        // 4、获取有效数据的个数
        int sum = 0;
        for (int[] chess : chessArr) {
            for (int i : chess) {
                sum = i == 0 ? sum : ++sum;
            }
        }
        // 5、创建稀疏数组(第一行用于记录原始二维数组的行、列、有效数据个数)
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = rowLen;
        sparseArray[0][1] = columnLen;
        sparseArray[0][2] = sum;
        // 6、将有效数据存储到稀疏数组中
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }
        // 7、打印稀疏数组
        printTwoArray(sparseArray);
        // @TODO 将稀疏数组存储到磁盘中
    }


    /**
     * 打印二维数组
     *
     * @param array int型二维数组
     */
    private static void printTwoArray(int[][] array) {
        for (int[] arr : array) {
            for (int i : arr) {
                System.out.printf("%d\t", i);
            }
            // 换行
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------");
    }


}

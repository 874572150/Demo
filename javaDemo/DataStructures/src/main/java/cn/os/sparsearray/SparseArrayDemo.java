package cn.os.sparsearray;

import cn.os.common.utils.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        int[][] array = new int[rowLen][columnLen];
        // 2、创建黑白子
        array[5][5] = 2;
        array[6][6] = 1;
        array[4][6] = 2;
        array[6][4] = 1;
        // 3、打印原始的二维数组
        System.out.println("1、打印原始的二维数组");
        printTwoArray(array);
        // 4、获取有效数据的个数
        int sum = 0;
        for (int[] arr : array) {
            for (int i : arr) {
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
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        // 7、打印稀疏数组
        System.out.println("2、打印转换的稀疏数组：");
        printTwoArray(sparseArray);
        // 8、将稀疏数组存储到磁盘中（sparsearray/map.data）
        File resourcesFile = FileUtils.getResourcesFile("sparsearray/map.data");
        FileWriter fileWriter = null;
        try {
            // 创建FileWrite，允许在文件后追加
            fileWriter = new FileWriter(resourcesFile, true);
            System.out.println("开始写入磁盘...");
            for (int i = 0; i < sparseArray.length; i++) {
                // 单行存储二维数据
                String str = sparseArray[i][0] + "," + sparseArray[i][1] + "," + sparseArray[i][2];
                fileWriter.append(str);
                if (i != sparseArray.length - 1) {
                    fileWriter.append(",");
                } else {
                    fileWriter.append("\n");
                }

            }
            System.out.println("写入磁盘成功...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭io流
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 9、从磁盘中读取稀疏数组
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<int[][]> sparseArrayList = new ArrayList<>();
        try {
            fileReader = new FileReader(resourcesFile);
            bufferedReader = new BufferedReader(fileReader);
            System.out.println("开始读取稀疏数组...");
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] strArray = line.split(",");
                int rowL = Integer.parseInt(strArray[0]);
                int columnL = Integer.parseInt(strArray[1]);
                int itemL = Integer.parseInt((strArray[2]));
                int[][] sparseArr = new int[itemL + 1][3];
                sparseArr[0][0] = rowL;
                sparseArr[0][1] = columnL;
                sparseArr[0][2] = itemL;
                int index = 0;
                for (int i = 3; i < strArray.length; i = i + 3) {
                    index++;
                    sparseArr[index][0] = Integer.parseInt(strArray[i]);
                    sparseArr[index][1] = Integer.parseInt(strArray[i + 1]);
                    sparseArr[index][2] = Integer.parseInt(strArray[i + 2]);

                }
                sparseArrayList.add(sparseArr);
            }
            System.out.println("读取稀疏数组成功...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭io流
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 10、打印还原的稀疏数组
        System.out.println("打印还原的稀疏数组...");
        for (int i = 0; i < sparseArrayList.size(); i++) {
            System.out.printf("第%d局：\n", i + 1);
            printTwoArray(sparseArrayList.get(i));
        }
        // 10、还原磁盘读取的二维数组
        List<int[][]> beginArrList = new ArrayList<>();
        for (int[][] sparseArr : sparseArrayList) {
            int[][] beginArr = new int[sparseArr[0][0]][sparseArr[0][1]];
            for (int i = 1; i < sparseArr.length; i++) {
                beginArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }
            beginArrList.add(beginArr);
        }
        // 11、打印磁盘读取的二维数组
        System.out.println("打印还原的二维数组...");
        for (int i = 0; i < beginArrList.size(); i++) {
            System.out.printf("第%d局：\n", i + 1);
            printTwoArray(beginArrList.get(i));
        }
    }


    /**
     * 打印二维数组
     *
     * @param array int型二维数组
     */
    private static void printTwoArray(int[][] array) {
        System.out.println("--------------------------------------------------------------------------");
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

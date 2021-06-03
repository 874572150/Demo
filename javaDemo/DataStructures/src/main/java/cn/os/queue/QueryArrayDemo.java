package cn.os.queue;

import java.util.Scanner;

/**
 * @author ou shuo
 * @date 2021/6/2 14:44
 * @description 环形数组队列结构测试
 */
public class QueryArrayDemo {

    public static void main(String[] args) {
        // 创建一个队列
        QueueArray queueArray = new QueueArray(3);
        Scanner scanner = new Scanner(System.in);
        Boolean bool = true;
        while (bool) {
            System.out.println("-----------------------------------");
            System.out.println("1、a：向队列中添加元素");
            System.out.println("2、g：从队列中取出元素");
            System.out.println("3、q：查询队列中的元素");
            System.out.println("4、e：退出");
            System.out.println("-----------------------------------");
            System.out.print("请输入：");
            char c = scanner.next().charAt(0);
            System.out.println();
            switch (c) {
                case 'a':
                    System.out.printf("请输入元素:");
                    int element = scanner.nextInt();
                    try {
                        queueArray.addQueue(element);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(queueArray.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    queueArray.printQueue();
                    break;
                case 'e':
                    bool = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入。");
            }
        }
    }


}

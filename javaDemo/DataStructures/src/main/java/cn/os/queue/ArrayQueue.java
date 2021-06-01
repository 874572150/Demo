package cn.os.queue;

/**
 * @author ou shuo
 * @date 2021/6/1 18:16
 * @description 队列结构
 */
public class ArrayQueue {
    // 队列的最大长度
    private final int maxSize;

    // 存储数组
    private int[] arr;

    // 指向队列头
    private int front;

    // 指向队列尾
    private int rear;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    /**
     * 判断队列是否已满
     *
     * @return true 如果队列已满
     */
    private static boolean isFull() {
        // @TODO 判断队列是否已满
        return true;
    }

}

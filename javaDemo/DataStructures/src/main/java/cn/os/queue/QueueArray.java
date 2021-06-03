package cn.os.queue;

/**
 * @author ou shuo
 * @date 2021/6/1 18:16
 * @description 环形数组队列结构
 */
public class QueueArray {
    /**
     * 队列最大长度
     */
    private final int maxSize;

    /**
     * 数组
     */
    private final int[] arr;

    /**
     * 指向队列头
     */
    private int front;

    /**
     * 指向队列尾的后一个元素
     */
    private int rear;

    /**
     * 队列元素数
     */
    private int elementNum;

    /**
     * 初始化队列
     *
     * @param maxSize 队列长度
     */
    public QueueArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     *
     * @return true 如果队列已满
     */
    private boolean isFull() {
        return elementNum == maxSize;
    }

    /**
     * 判断队列是否为空
     *
     * @return true 如果队列为空
     */
    private boolean isEmpty() {
        return elementNum == 0;
    }

    /**
     * 入队列
     *
     * @param value zhi
     */
    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加数据...");
        }
        elementNum++;
        arr[rear] = value;
        rear = ++rear % maxSize;
    }

    /**
     * 出队列
     *
     * @return value 出队列的值
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取出数据...");
        }
        elementNum--;
        int value = arr[front];
        front = ++front % maxSize;
        return value;
    }

    /**
     * 打印队列数据
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空");
            return;
        }
        int count = elementNum;
        int i = front;
        while (count-- != 0) {
            System.out.println(arr[i++ % maxSize]);

        }
    }

}

package thh.studycode.algorithm;

/**
 * 用数组实现固定长度的队列
 */
public class A205_LimitQueueByArray {
    private int maxSize ;
    private int[] arr = null;
    private int size = 0;
    private int addIndex = 0;
    private int pollIndex = 0;

    public A205_LimitQueueByArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public void add(int num) {
        if (maxSize == size) {
            System.out.println("没有位置放了");
            throw new RuntimeException();
        }
        arr[addIndex++] = num;
        if(addIndex == maxSize){
            addIndex= 0;
        }
        size++;
    }

    public int poll() {
        if (size == 0) {
            System.out.println("没有了");
            throw new RuntimeException();
        }
        int num = arr[pollIndex];
        arr[pollIndex++] = 0;
        if(pollIndex == maxSize){
            pollIndex = 0;
        }
        size--;
        return num;
    }

    public static void main(String[] args) {
        A205_LimitQueueByArray queue = new A205_LimitQueueByArray(10);
        queue.add(1);
        queue.add(2);
        System.out.println("poll "+queue.poll());
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println("poll "+queue.poll());
        System.out.println("poll "+queue.poll());
        queue.add(5);
        queue.add(6);
        System.out.println("poll "+queue.poll());
        System.out.println("poll "+queue.poll());
        queue.add(5);
        queue.add(6);
        queue.add(7);
        while (true) {
            System.out.println("poll "+queue.poll());
        }

    }
}

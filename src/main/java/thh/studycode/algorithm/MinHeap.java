package thh.studycode.algorithm;

import org.apache.commons.lang.math.RandomUtils;

import java.util.PriorityQueue;

/**
 * 完全二叉树:
 * 从上到下，从左到右
 * 左边的子节点：2*i+1
 * 右边的子节点：2*i+2
 * 子节点求父节点：(i-1)/2
 * <p>
 * 如果从1开始计算
 * 左边的子节点：2*i
 * 右边的子节点：2*i+1
 * 左边的子节点求父节点：i/2
 * 由于频繁的计算*2, 如是从1开始计算，可以用位移操作，减少计算。
 * <p>
 * 堆是在二叉树的基础之上，实现的大根堆，或小根堆， 提到堆的时候必段是大根堆或小根堆，不存在别的
 * <p>
 * 大根堆，每一棵子树的最大值，都是头节点的值。
 * 大根堆，每一棵子树的最小值，都是头节点的值。
 */
public class MinHeap<T> {
    int[] arr;
    int size;

    public MinHeap(int limit) {
        arr = new int[limit];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("满了");
        }
        arr[size] = value;
        heapInsert(arr, size++);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        int value = arr[0];
        swap(arr, 0, --size);
        arr[size] = 0;

        heapify(arr, 0, size);
        return value;
    }

    public void heapify(int[] arr, int index, int size) {
        int lIndex = index << 1 | 1;
        while (lIndex < size) {
            int rIndex = lIndex + 1;
            if (rIndex < size) {
                int mIndex = arr[lIndex] > arr[rIndex] ? rIndex : lIndex;
                if (arr[index] > arr[mIndex]) {
                    swap(arr, index, mIndex);
                    index = mIndex;
                } else {
                    break;
                }
            } else if (arr[index] > arr[lIndex]){
                swap(arr, index, lIndex);
                index = lIndex;
            } else {
                break;
            }
            lIndex = index << 1 | 1;
        }
    }

    public void heapInsert(int[] arr, int index) {
        int pIndex = (index -1) >> 1;
        while (pIndex >= 0 && arr[pIndex] > arr[index]) {
            swap(arr, index, pIndex);
            index = pIndex;
            pIndex = index -1 >> 1;
        }
    }

    public void swap(int[] arr, int res, int des) {
        arr[res] = arr[res] ^ arr[des];
        arr[des] = arr[res] ^ arr[des];
        arr[res] = arr[res] ^ arr[des];
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        int size = RandomUtils.nextInt(10)+20;
        MinHeap heap = new MinHeap(size);

        for (int i = 0; i < size; i++) {
            int a = RandomUtils.nextInt(100);
            heap.push(a);
            queue.add(a);
        }

        for (int j = 0; j < size; j++) {
            System.out.print(heap.pop() + "\t");
        }
        System.out.println();
        for (int j = 0; j < size; j++) {
            System.out.print(queue.poll() + "\t");
        }

        System.out.println();
    }




}


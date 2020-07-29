package thh.studycode.algorithm;

/**
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过K,并且K相对于数组长度来说是比较小的。
 */
public class A404_MinHeapLimitSort<T> {

    int[] arr;
    int size = 0;

    public A404_MinHeapLimitSort(int size) {
        this.arr = new int[size];
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("满了");
        }
        arr[size] = value;
        heapInsert(arr, size++);
    }

    public void heapInsert(int[] arr, int index) {
        //判断父节点树，是不是小根堆，进行交换
        while (index > 0) {
            int pIndex = index-1 >> 1;
            if (arr[index] < arr[pIndex]) {
                swap(arr, index, pIndex);
                index = pIndex;
            } else {
                break;
            }
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        int value = arr[0];

        swap(arr, 0, size - 1);
        arr[--size] = 0;
        heapify(arr, 0, size);

        return value;
    }

    public void heapify(int[] arr, int index, int size) {
        int lIndex = index << 1 | 1;
        while (lIndex < size) {
            int rIndex = lIndex + 1;
            if (rIndex < size) {
                int mIndex = arr[lIndex] < arr[rIndex] ? lIndex : rIndex;
                if (arr[index] > arr[mIndex]) {
                    swap(arr, index, mIndex);
                    index = mIndex;
                } else {
                    break;
                }
            } else if (arr[index] > arr[lIndex]) {
                swap(arr, index, lIndex);
                index = lIndex;
            } else {
                break;
            }
            lIndex = index << 1 | 1;
        }
    }

    public void swap(int[] arr, int res, int des) {
        arr[res] = arr[res] ^ arr[des];
        arr[des] = arr[res] ^ arr[des];
        arr[res] = arr[res] ^ arr[des];
    }

    public static void main(String[] args) {
        int k = 5;
        int[] a = {0,0,2,5,12,24,23,41,40,57,40,46,68,64,81,76,75,78,91,93};
        A404_MinHeapLimitSort heap = new A404_MinHeapLimitSort(6);
        int i = 0;
        while (heap.isFull() == false && i< a.length){
            heap.push(a[i++]);
            if(heap.isFull()){
                System.out.println(heap.pop());
            }
        }
        while(heap.isEmpty() ==false){
            System.out.println(heap.pop());
        }
    }

}



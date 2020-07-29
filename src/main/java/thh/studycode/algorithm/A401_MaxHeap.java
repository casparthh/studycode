package thh.studycode.algorithm;

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
public class A401_MaxHeap<T> {

    int[] arr;
    int heapSize;

    public A401_MaxHeap(int limitSize) {
        arr = new int[limitSize];
    }

    public void insert(int value) {
        if (heapSize == arr.length) {
            throw new RuntimeException("满了。。");
        }
        //存放到最后位置
        arr[heapSize++] = value;

        //对比最后节点与父节点大小，调整节点位置
        int index = heapSize - 1;
        int parent = index - 1 >> 1;
        while (index > 0 && arr[index] > arr[parent]) {
            swap(arr, index, parent);
            index = parent;
            parent = index - 1 >> 1;
        }
    }

    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("没了。。");
        }
        if (heapSize == 1) {
            arr[heapSize--] = 0;
            return arr[0];
        }

        int value = arr[0];
        swap(arr, 0, --heapSize);
        arr[heapSize] = 0;
        heapify1(arr, 0, heapSize);
        return value;
    }

    /**
     * 递归实现
     * 从index 位置往下看，不断的下沉，与子节点的最大值进行替换
     * 停：没有子节点，或子节点都比当前值小
     *
     * @param nums
     * @param index
     * @param heapSize
     */
    private void heapify(int[] nums, int index, int heapSize) {
        //找出在右两个子节点
        int lIndex = (index << 1) | 1;
        int rIndex = (index << 1) + 2;

        //如果左右两个子节点都存在
        if (lIndex < heapSize && rIndex < heapSize) {
            //找出左右子节点最大值，并进行交换
            int mIndex = arr[lIndex] > arr[rIndex] ? lIndex : rIndex;
            if(arr[index]< arr[mIndex]) {
                swap(nums, index, mIndex);
                //递归重复操作
                heapify(nums, mIndex, heapSize);
            }
        } else if (lIndex < heapSize && arr[index]< arr[lIndex]) {
            //如果右子节点不存在，说明找到最后一个节点了，直接替换
            swap(nums, index, lIndex);
        }
    }

    /**
     * 循环实现
     *
     * @param nums
     * @param index
     * @param heapSize
     */
    private void heapify1(int[] nums, int index, int heapSize){
        int lIndex = index << 1 | 1;
        while (lIndex< heapSize){
            int rIndex = lIndex +1;
            if (rIndex< heapSize) {
                int mIndex = nums[lIndex] > nums[rIndex] ? lIndex : rIndex;
                if(nums[index] < nums[mIndex]) {
                    swap(nums, index, mIndex);
                    index = mIndex;
                } else {
                    break;
                }
            } else if(nums[index] < arr[lIndex]) {
                //右节点不存在
                swap(nums, index, lIndex);
                index = lIndex;
            } else {
                break;
            }
            lIndex = index << 1 | 1;
        }
    }

    private void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }

    public static void main(String[] args) {
        A401_MaxHeap heap = new A401_MaxHeap(10);
        for (int i = 0; i <10 ; i++ ) {
            heap.insert(i);
            System.out.println();
            for (int j = 0; j < heap.heapSize;j++) {
                System.out.print(heap.arr[j] + "\t");
            }
        }

        for (int i = 0; i < 10; i++) {
            heap.pop();
            System.out.println();
            for (int j = 0; j < heap.heapSize;j++) {
                System.out.print(heap.arr[j] + "\t");
            }
        }

        System.out.println();
    }
}


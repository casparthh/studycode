package thh.studycode.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

/**
 * resign 堆, 生成堆之后，对堆中的对象数据进行修改，造成需要重新调整堆。
 */
public class A405_ResignHeap<T> {

    private List<T> dataList = new ArrayList<T>();
    private Map<T, Integer> indexMap = new HashMap<T, Integer>();
    private int size;
    private Comparator<T> comparator;

    public A405_ResignHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T t) {
        dataList.add(t);
        indexMap.put(t, size);
        heapInsert(dataList, size++);
    }

    public T poll() {
        T t = dataList.get(0);
        swap(dataList, 0, --size);
        dataList.remove(size);
        heapify(dataList, 0);
        indexMap.remove(t);
        return t;
    }

    public void resign(T t){
        if(indexMap.containsKey(t) == false) {
            return;
        }
        int index = indexMap.get(t);
        heapInsert(dataList, index);
        heapify(dataList, index);
    }

    /**
     * 生成一个小根堆
     *
     * @param dataList
     * @param index
     */
    public void heapInsert(List<T> dataList, int index) {
        int pIndex = (index - 1) >> 1;
        while (pIndex >= 0) {
            T pNode = dataList.get(pIndex);
            T cNode = dataList.get(index);
            if (comparator.compare(pNode, cNode) > 0) {
                swap(dataList, pIndex, index);
                index = pIndex;
                pIndex = (pIndex - 1) >> 1;
            } else {
                return;
            }
        }
    }

    public void heapify(List<T> dataList, int index) {
        int lIndex = index << 1 | 1;
        int rIndex = lIndex + 1;
        int currIndex = index;
        while (lIndex < size) {
            T currNode = dataList.get(currIndex);
            T lNode = dataList.get(lIndex);
            int least = comparator.compare(currNode, lNode) > 0 ? lIndex : currIndex;
            if (rIndex < size) {
                T rNode = dataList.get(rIndex);
                least = comparator.compare(dataList.get(least), rNode) > 0 ? rIndex : least;
            }
            if (least != currIndex) {
                swap(dataList, least, currIndex);
                currIndex = least;
                lIndex = least << 1 | 1;
                rIndex = lIndex + 1;
            } else {
                return;
            }

        }
    }

    public void print(){
        for (T t : dataList) {
            System.out.println(((User)t).getAge());
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void swap(List<T> dataList, int i0, int i1) {
        T t = dataList.get(i0);
        dataList.set(i0, dataList.get(i1));
        indexMap.put(dataList.get(i1), i0);
        dataList.set(i1, t);
        indexMap.put(t, i1);
    }


    public static void main(String[] args) {
        A405_ResignHeap<User> data = new A405_ResignHeap<User>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            int age = RandomUtils.nextInt(100);
            User u = new User("name" + age, age);
            data.add(u);
            userList.add(u);
        }
        data.print();
        System.out.println("---------");
        userList.forEach(u ->{
            u.setAge(RandomUtils.nextInt(200));
            data.resign(u);
        });

//        data.print();
        while (data.isEmpty() == false){
            System.out.println(data.poll().getAge());
        }

    }
}

@Getter
@Setter
@AllArgsConstructor
class User {
    private String name;
    private int age;
}



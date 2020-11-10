package wxn.util.heap;

import wxn.util.tools.Tools;

import java.util.Arrays;

// TODO: 区分 Comparable 和 Comparator
/**
 * array heap
 * @param <T>
 */
public class Heap<T extends Comparable<T>>{
    Object[] arr;
    int size;

    Heap(T[] arr){
        buildMaxHeap(arr);
    }

    public int length(){
        return arr.length;
    }

    public void buildMaxHeap(T[] arr) {
        this.arr = arr;

        size = arr.length;
        for (int i = (size >> 1) - 1; i > -1; i--) {
            maxHeapify(i);
        }
    }

    public void maxHeapify(int idx) {
        if (idx >= size){
            return;
        }

        int leftIdx = (idx << 1) + 1 >= size ? idx : (idx << 1) + 1;
        int rightIdx = (idx << 1) + 2 >= size ? idx : (idx << 1) + 2;

        int maxIdx = get(idx).compareTo(get(leftIdx)) >= 0 ? idx : leftIdx;
        maxIdx = get(maxIdx).compareTo(get(rightIdx)) >= 0 ? maxIdx : rightIdx;

        if (maxIdx != idx){
            Tools.exchange(arr, maxIdx, idx);
            maxHeapify(maxIdx);
        }
    }

    private void grow(){
        arr = Arrays.copyOf(arr, length() + (length() >> 1));
    }

    public void maxHeapInsert(T element) {
        if (size >= length()){
            grow();
        }

        arr[size] = Integer.MIN_VALUE;
        heapIncreaseKey(size, element);
        size++;
    }

    public void heapIncreaseKey(int idx, T val) {
        assert get(idx).compareTo(val) <= 0;

        while (idx > 0){
            int parent = (idx - 1) >> 1;
            if (val.compareTo(get(parent)) <= 0){
                break;
            }
            arr[idx] = arr[parent];
            idx = parent;
        }
        arr[idx] = val;
    }

    public T heapExtractMax() {
        T res = heapMax();

        arr[0] = arr[size-1];
        size--;
        maxHeapify(0);

        return res;
    }

    public T get(int idx){
        return (T) arr[idx];
    }

    public T heapMax() {
        return (T) arr[0];
    }

    public void heapSort(){
        int originSize = size;

        while (size > 1){
            Tools.exchange(arr, 0, size-1);
            size--;
            maxHeapify(0);
        }
        size = originSize;
    }

    public Object[] toArray(){
        return Arrays.copyOf(arr, size);
    }

    @Override
    public String toString() {
        return "ArrayHeap{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }

    // for test
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4,1,3,2,16,9,10,14,8,7};

        Heap<Integer> ah = new Heap<>(arr);
        ah.maxHeapInsert(5);
        System.out.println("insert " + ah);

        int max = ah.heapExtractMax();
        System.out.println("max " + max);
        System.out.println("extract max " + ah);

        ah.heapSort();
        System.out.println("sort " + ah);

    }
}

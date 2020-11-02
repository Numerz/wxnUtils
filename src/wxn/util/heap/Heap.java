package wxn.util.heap;

public interface Heap<T extends Comparable<T>> {
    void buildMaxHeap(T[] arr);
    void maxHeapify(int idx);
    void maxHeapInsert(T element);
    void heapIncreaseKey(int idx, T val);
    T heapExtractMax();
    T heapMax();
}

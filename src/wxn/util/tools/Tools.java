package wxn.util.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tools {
    public static <T extends Comparable<T>> T numberMax(T a, T b){
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * 交换arr数组中下标为 a 和下标为 b 的元素
     * @param arr Array
     * @param a index
     * @param b index
     * @param <T>
     */
    public static <T> void exchange(T[] arr, int a, int b){
        T tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void exchange(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // Quick Sort Part

    public static void quickSort(int[] arr, int start, int end){
        if (start >= end - 1){
            return;
        }

        int q = partitionHoare(arr, start, end);
        quickSort(arr, start, q);
        quickSort(arr, q+1, end);
    }

    private static int partitionLomuto(int[] arr, int start, int end){
        if (start >= end - 1){
            return start;
        }

        int right = end - 1;

        // random select
        int idx = randomSelect(start, end);
        exchange(arr, idx, right);

        int i = start;
        int pivot = arr[right];

        for (int j = start; j < right; j++) {
            if (arr[j] < pivot){
                exchange(arr, i, j);
                i++;
            }
        }

        exchange(arr, i, right);
        return i;
    }

    private static int partitionHoare(int[] arr, int start, int end){
        if (start >= end - 1){
            return start;
        }

        // random select
        int idx = randomSelect(start, end);
        exchange(arr, idx, start);

        int pivot = arr[start];
        int left = start;
        int right = end - 1;

        while (left < right){
            while (left < right && arr[right] >= pivot){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] < pivot){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;

        return left;
    }

    /**
     * 从start到end中随机选出一个整数
     * @param start index
     * @param end index exclusive
     * @return random int
     */
    private static int randomSelect(int start, int end){
        int bound = end - start;
        Random random = new Random();
        return start + random.nextInt(bound);
    }

    // Counting Sort Part

    /**
     * 计数排序 元素值范围[0,k]
     * @param arr Array
     * @param k 最大值
     * @return sorted array
     */
    public static int[] countingSort(int[] arr, int k){
        int n = arr.length;
        int[] count = new int[k+1];
        int[] res = new int[n];

        for (int num :
                arr) {
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i-1] + count[i];
        }

        for (int i = n-1; i > -1; i--) {
            res[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        return res;
    }

    // Radix Sort Part

    public static void radixSort(int[] arr){
        int digit = getMaxDigit(arr);

        for (int i = 0; i < digit; i++) {
            bucketSort(arr, i);
        }
    }

    private static int getMaxDigit(int[] arr){
        int digit = 1;
        int base = 10;
        for (int num :
                arr) {
            while (num > base){
                digit++;
                base *= 10;
            }
        }
        return digit;
    }

    private static int getMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    private static int getDigit(int num){
        return (num + "").length();
    }

    private static void bucketSort(int[] arr, int digit){
        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucket.add(new ArrayList<>());
        }

        int base = (int) Math.pow(10, digit);
        for (int num :
                arr) {
            int idx = (num / base) % 10;
            bucket.get(idx).add(num);
        }

        int idx = 0;
        for (List<Integer> list :
                bucket) {
            for (int num :
                    list) {
                arr[idx++] = num;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2,8,7,1,3,5,6,4,9};
        int[] arr = new int[] { 321, 1234, 543, 324, 24, 960, 540, 672, 783, 1000 };
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}

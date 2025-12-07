package sorting;

import java.io.IOException;
import java.util.Arrays;

public class QuickSort {

    public static void main(String args[]) throws IOException {
        int arr[] = {5, 8, 1, 3, 7, 9, 2};
        quickSort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIdx = partition(arr, low, high);
            quickSort(arr, low, partitionIdx - 1);
            quickSort(arr, partitionIdx + 1, high);
        }
    }

    //[5, 8, 1, 3, 7, 9, 2]
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                // j = 2, 1 < 2, i = 0
                i++;
                if (i != j)
                    swap(arr, i, j);
                // swap 0 with 2 [ 1, 8, 5, 7, 9, 2]
            }
        }
        swap(arr, i + 1, high); //[1, 2, 5, 7, 9, 8]
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

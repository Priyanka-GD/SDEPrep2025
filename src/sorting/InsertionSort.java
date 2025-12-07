package sorting;

import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {
    public static void main(String args[]) throws IOException {
        int arr[] = {5, 8, 1, 3, 7, 9, 2};

        for(int idx = 1; idx < arr.length; idx++){
            int j = idx - 1;
            int key = arr[idx];

            while(j >= 0 && arr[j] > key){
                swap(arr, j, j + 1);
                j--;
            }
            arr[j + 1] = key;
        }
        System.out.println(Arrays.toString(arr));
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

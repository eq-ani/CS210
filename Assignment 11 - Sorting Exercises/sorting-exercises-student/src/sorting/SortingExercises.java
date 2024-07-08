/*
 * Copyright 2023 Marc Liberatore.
 */

package sorting;
import java.util.Arrays;
public class SortingExercises {

    /**
     * Swap the values at a[i] and a[j].
     */
    static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Perform an in-place insertion sort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; (j > 0 && a[j - 1] > a[j]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Perform an in-place insertion sort of the range start (inclusive) 
     * through end (exclusive) on array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i; (j > start && a[j - 1] > a[j]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Perform a destructive mergesort on the array a.
    
     * The array will be in ascending order (least-to-greatest) after sorting; the original
     * values will be overwritten.
     * Additional array space will be allocated by this method.
     * 
     * For efficiency, this method will *insertion sort* any array of length less than 10.
     */
    public static void mergeSort(double[] a) 
    {  
        if (a.length < 10)
        {
            insertionSort(a);
            return;
        }
        if (a.length < 2)
        {
            return;
        }
        
            int half = a.length / 2;
            double[] left = Arrays.copyOfRange(a, 0, half);
            double[] right = Arrays.copyOfRange(a, half, a.length);

            mergeSort(left);
            mergeSort(right);

            merge(a, left, right); 

    }

    /**
     * Merge the sorted arrays l and r into the array a (overwriting its previous contents).
     */
    static void merge(double[] a, double[] l, double[] r) {
        // indices into the left, right, and output/merged array
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                a[k] = l[i];
                k++;
                i++;
            } else {
                a[k] = r[j];
                k++;
                j++;
            }
        }

        // copy remaining elements in:
        while (i < l.length) {
            a[k] = l[i];
            k++;
            i++;
        }
        while (j < r.length) {
            a[k] = r[j];
            k++;
            j++;
        }
    }

    /**
     * Perform an in-place quicksort on the array a.
     */
    static void quickSort(double[] a) {
        quickSort(a, 0, a.length);
    }

    /**
     * Perform an in-place quicksort on the range i (inclusive) to j 
     * (exclusive) of the array a.
     * 
     * For efficiency, this method will *insertion sort* any range of 
     * length less than 10.
     */
    static void quickSort(double[] a, int i, int j) { 
        if (a.length < 10)
        {
            insertionSort(a, i, j);
            return;
        }
        if (j - i < 2) {
            return;
        }

        int pivotIndex = partition(a, i, j); 
        quickSort(a, i, pivotIndex); 
        quickSort(a, pivotIndex + 1, j);
    }

    /**
     * Perform an in-place partition on the  range i (inclusive) to j 
     * (exclusive) of the array a, returning the index of the pivot
     * after partitioning.
     * 
     * To cut down on worst case choices, this method will chose the 
     * pivot value at random from among the values in the range.
     * 
     * @return the index of the pivot after the partition
     */
    static int partition(double[] a, int i, int j) {
        int p = i - 1;
        int q = j - 1;
        double x = a[q]; 
        int rand = (int)(Math.random()) * (q - i) + i;
        swap(a, i, rand);
        for (int r = i; r < q; r++) {
            if (a[r] <= x) {
                p++;
                swap(a, p, r);
            }
        }
        swap(a, p + 1, q);
        return p + 1;
    }
}
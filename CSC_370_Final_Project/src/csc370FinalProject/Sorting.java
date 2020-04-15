/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc370FinalProject;

/*
 * Micah Biermann
 * 18-03-12
 * CSC300 Array Sorter
 * A sorting algorithm for a random generated array of integers.
 */

public class Sorting
{
    public static void sort(int[] array)
    {
        int n = array.length;
        
        System.out.println("\n\nUnsorted Array: ");
        for (int j = 0; j < n; j++)
        {
            if (j < n - 1)
            {
                System.out.print(array[j] + ", ");
            }
            else
            {
                System.out.print(array[j] + ".");
            }
        }
        
        array = sort(array, 0, array.length-1);
        
        System.out.println("\n\n\nSorted Array: ");
        for (int j =0; j < n; j++)
        {
            if (j < n - 1)
            {
                System.out.print(array[j] + ", ");
            }
            else
            {
                System.out.print(array[j] + ".");
            }
        }
    }
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    public static int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    public static int[] sort(int array[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(array, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(array, low, pi-1); 
            sort(array, pi+1, high); 
        } 
        return array;
    }
    
    
}


package csc370FinalProject;

import java.util.ArrayList;

public class AlphabeticalSorting
{
    public static void sort(ArrayList<String> words, ArrayList<Integer> count)
    {
        words = sorter(words, count, 0, words.size()-1);
    }
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    public static int partition(ArrayList<String> words, ArrayList<Integer> count, int low, int high) 
    { 
        String pivot = words.get(high);  
        int i = (low-1); // Index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or equal to pivot 
            if (words.get(j).compareToIgnoreCase(pivot)<0) 
            { 
                i++; 
  
                // Swap words.get(i) and words.get(j)
                String tempW = words.get(i); 
                words.set(i, words.get(j)); 
                words.set(j, tempW);
                
                // Swap count.get(i) and count.get(j)
                int tempC = count.get(i); 
                count.set(i, count.get(j)); 
                count.set(j, tempC);
            } 
        } 
  
        // Swap words.get(i+1) and words.get(high) (or pivot) 
        String tempW = words.get(i+1); 
        words.set(i+1, words.get(high)); 
        words.set(high, tempW);
        
        // Swap count.get(i+1) and count.get(high) (or pivot) 
        int tempC = count.get(i+1); 
        count.set(i+1, count.get(high)); 
        count.set(high, tempC);
  
        return i+1; 
    } 
    /*The main function that implements sort() 
      words --> Array to be sorted
      count --> Count to be rearranged to match sorted words
      low  --> Starting index, 
      high  --> Ending index */
    public static ArrayList<String> sorter(ArrayList<String> words, ArrayList<Integer> count, int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, words.get(pi) is now at right place */
            int pi = partition(words, count, low, high); 
  
            // Recursively sort elements before partition and after partition 
            sorter(words, count, low, pi-1); 
            sorter(words, count, pi+1, high); 
        } 
        return words;
    } 
}


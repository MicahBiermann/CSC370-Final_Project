/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc370FinalProject;

/**
 *
 * @author Micah
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LongestWord
{
    public static String getLongestWords(String file) throws FileNotFoundException
    {
        String longestWord = "";
        String current;
        String output = "";
        Scanner scan = new Scanner(new File(file));
        ArrayList<String> longestWords = new ArrayList<>();
        longestWords.add("");


        while (scan.hasNext())
        {
            current = scan.next();
            if (current.length() >= longestWord.length())
            {
                longestWord = current;
            }
            if (longestWord.length() > longestWords.get(0).length())
            {
                longestWords.clear();
                longestWords.add(current);
            }
            else if (longestWord.length() == longestWords.get(0).length() && !longestWords.contains(longestWord))
            {
                longestWords.add(longestWord);
            }
        }
        if (longestWords.size() == 1)
        {
            output = longestWords.get(0) + ".";
        }
        else if (longestWords.isEmpty())
        {
            output = "Empty file, no longest word present.";
        }
        else
        {
            for (int i = 0; i < (longestWords.size() - 1); i++)
            {
                output += longestWords.get(i) + ", ";
            }
            output += longestWords.get(longestWords.size() - 1) + ".";
        }
        return output;
    }
}

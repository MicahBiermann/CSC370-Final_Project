package csc370FinalProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author mscetinel
 */

public class Csc370FinalProject
{
    public static final String FILEPATH = "C:\\Users\\Micah\\Documents\\Essays & School Papers\\2019-2020 School Papers\\Spring 2020\\CSC 370\\CSC 370 Final Project\\";
    public static final String FILETYPE = ".txt";
    
    public static void main(String[] args) throws IOException
    {
        
        Scanner scnrUserIn = new Scanner(System.in);
        System.out.print("Please enter number of files to read from:");
        int numFiles = scnrUserIn.nextInt();
        scnrUserIn.nextLine();
        System.out.println("Assuming files are .txt files in \"Documents\".");
        
        String[] fileNameAndPath = new String[numFiles];
        String[] fileName = new String[numFiles];
        for(int i = 0; i < numFiles; i++)
        {
            System.out.print("\nPlease enter the name of file number " + (i + 1) + ":");
            fileName[i] = scnrUserIn.nextLine();
            fileNameAndPath[i] = FILEPATH + fileName[i] + FILETYPE;
        
            FileInputStream fin = new FileInputStream(fileNameAndPath[i]);
            Scanner fileInput = new Scanner(fin);

            ArrayList<String> words = new ArrayList<>();
            ArrayList<Integer> count = new ArrayList<>();

            while(fileInput.hasNext())
            {

                String nextWord = fileInput.next();

                if(words.contains(nextWord))
                {
                    int index = words.indexOf(nextWord);
                    count.set(index, count.get(index) +1);
                }
                else
                {
                    words.add(nextWord);
                    count.add(1);
                }

            }

            fileInput.close();
            fin.close();
            
            for(int j = 0; j < words.size(); j++)
            {
               System.out.println("In " + fileName[i] + " \"" + words.get(j) + "\" occured " + count.get(j) + " time(s).");
            }
        }
       
    }
   
}

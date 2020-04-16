package csc370FinalProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Arda Cetinel
 * @author Micah Biermann
 */

public class Csc370FinalProject
{
    public static final String FILEPATH = "C:\\Users\\Micah\\Documents\\Essays & School Papers\\2019-2020 School Papers\\Spring 2020\\CSC 370\\CSC 370 Final Project\\";
    public static final String FILETYPE = ".txt";
    public static final String ALLOWEDCHARSPATTERN = "[^a-zA-Z'’-]";
    public static final String WORDSBLACKLIST = "the of in and as like on to at"; 
    
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
                Pattern allowedChars = Pattern.compile(ALLOWEDCHARSPATTERN);
                fileInput.useDelimiter(allowedChars);
                
                String nextWord = fileInput.next();
  
                if(words.contains(nextWord))
                {
                    int index = words.indexOf(nextWord);
                    count.set(index, count.get(index) +1);
                }
                else if(!nextWord.isEmpty() && !WORDSBLACKLIST.contains(nextWord))
                {
                    words.add(nextWord);
                    count.add(1);
                }
            }

            fileInput.close();
            fin.close();
            
            NumericalSorting.sort(words, count);
            AlphabeticalSorting.sort(words, count);
            //NumericalSorting.sort(words, count);  FIXME: This could be changed so that it sorts differenly, I'm just not sure what is the best way to have it sort.
            
            System.out.println("In " + fileName[i] + ":");
            if(count.contains(1))
            {
                System.out.print("Words occuring one time: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 1 && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    else if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }
            
            if(count.contains(2))
            {
                System.out.print("Words occuring twice: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 2 && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    else if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }
            
            if(count.contains(3))
            {
                System.out.print("Words occuring three times: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 3 && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    else if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }
            
            if(count.contains(4))
            {
                System.out.print("Words occuring four times: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 4 && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    else if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }
            
            if(count.contains(5))
            {
                System.out.print("Words occuring five times: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 5 && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    else if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }

            System.out.print("Words occuring more than 5 times:\n");
            for(int j = 0; j < words.size(); j++)
            {
                if(count.get(j) > 5)
                {
                    System.out.println("\"" + words.get(j) + "\"" + " occured " + count.get(j) + " times.");
                }
            }
            System.out.println("===============================================================================");
        }
    }
}

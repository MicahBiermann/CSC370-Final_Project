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
    public static final String INDEX_DIR = "C:\\Users\\Micah\\Documents\\Essays & School Papers\\2019-2020 School Papers\\Spring 2020\\CSC 370\\CSC 370 Final Project\\Lucene Index\\"; //The default Lucene index files folder
    public static final String FILEPATH = "C:\\Users\\Micah\\Documents\\Essays & School Papers\\2019-2020 School Papers\\Spring 2020\\CSC 370\\CSC 370 Final Project\\Test Documents\\"; //The default filepath
    public static final String FILETYPE = ".txt"; //The default filetype
    public static final String ALLOWEDCHARSPATTERN = "[^a-zA-Z'’-]"; //List of characters that will be counted as part of a word
    public static final String[] WORDSBLACKLIST = {"the", "of", "in", "and", "as", "like", "on", "to", "at"}; //List of words to not be tallied by the program
    
    public static void main(String[] args) throws IOException
    {     
        Scanner scnrUserIn = new Scanner(System.in); //Creates a scanner to take user inputs
        System.out.print("Please enter number of files to read from:");
        int numFiles = scnrUserIn.nextInt();
        scnrUserIn.nextLine();
        System.out.println("Assuming files are .txt files in \"CSC 370 Final Project\".");
        
        String[] fileNameAndPath = new String[numFiles]; //Creates arrays to store the names and paths for all files
        String[] fileName = new String[numFiles];
        for(int i = 0; i < numFiles; i++) //Main loop to iterate through all files
        {
            System.out.print("\nPlease enter the name of file number " + (i + 1) + ":");
            fileName[i] = scnrUserIn.nextLine();
            fileNameAndPath[i] = FILEPATH + fileName[i] + FILETYPE;
        
            FileInputStream fin = new FileInputStream(fileNameAndPath[i]); //Initializes a file input stream for given file
            Scanner fileInput = new Scanner(fin);

            ArrayList<String> words = new ArrayList<>(); //Creates an array list for the unique words in the file
            ArrayList<Integer> count = new ArrayList<>(); //Creates an array list to store the number of occurences of each unique word

            while(fileInput.hasNext())
            {
                Pattern allowedChars = Pattern.compile(ALLOWEDCHARSPATTERN); //Compiles the parameters of what characters should not be counted as part of a word
                fileInput.useDelimiter(allowedChars); //Defines that above characters should not be counted
                
                String nextWord = fileInput.next(); //Extracts next word in file
  
                if(words.contains(nextWord)) //If the next word is already in the array list, the count is increased by 1
                {
                    int index = words.indexOf(nextWord);
                    count.set(index, count.get(index) +1);
                }
                else if(!nextWord.isEmpty() && !blacklistTest(nextWord)) //If the next word is not already in the array list,
                {                                                                  //the count and words array lists are expanded to include the new word.
                    words.add(nextWord);
                    count.add(1);
                }
            }

            fileInput.close(); //Closes the file input stream for the given file
            fin.close(); //Closes the file scanner for the given file
            
            AlphabeticalSorting.sort(words, count); //Sorts the array lists alphabetically
            
            System.out.println("In " + fileName[i] + ":"); //Outputs file name
            if(count.contains(1)) //Various print functions to group words by the number of occurences
            {
                System.out.print("Words occuring one time: ");
                for(int j = 0; j < words.size(); j++)
                {
                    if(count.get(j) == 1)// && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    if(j == count.size()-1)
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
                    if(count.get(j) == 2)// && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    if(j == count.size()-1)
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
                    if(count.get(j) == 3)// && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    if(j == count.size()-1)
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
                    if(count.get(j) == 4)// && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    if(j == count.size()-1)
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
                    if(count.get(j) == 5)// && j != count.size()-1)
                    {
                        System.out.print(words.get(j) + ", ");
                    }
                    if(j == count.size()-1)
                    {
                        System.out.print("\n\n");
                    }
                }
            }
            
            AlphabeticalSorting.sort(words, count);
            NumericalSorting.sort(words, count); //Sorts the array lists by the number of occurences, placed here because it is unneeded prior because it inherently sorts numerically already
            
            System.out.print("Words occuring more than 5 times:\n"); //Words occuring more than 5 times will be outputted alphabetically here
            for(int j = 0; j < words.size(); j++)
            {
                if(count.get(j) > 5)
                {
                    System.out.println("\"" + words.get(j) + "\"" + " occured " + count.get(j) + " times.");
                }
            }
            System.out.println("\n\nThe longest word(s) in " + fileName[i] + " is(are): " + LongestWord.getLongestWords(fileNameAndPath[i]));
            System.out.println("==============================================================================="); //Delineates between files
        }
        System.out.print("Would you like to list files sorted by given search term? (\"y\" or \"n\"):");
        String searchCheck = scnrUserIn.next();
        if (searchCheck.equalsIgnoreCase("y"))
        {
            String filePath = fileNameAndPath[0];
            System.out.print("Please enter search term:");
            String searchTerm = scnrUserIn.next();
            try
            {
                LuceneIndexer.indexer(INDEX_DIR, fileNameAndPath, fileName);
                LuceneFileSearcher.searcher(INDEX_DIR, filePath, searchTerm);
            }
            catch (Exception e)
            {
                System.out.println("An error occured, please check your inputs and retry.");
                System.out.println("Error:" + e);
            }
        }
        else if (searchCheck.equalsIgnoreCase("n"))
        {
            System.out.print("Goodbye.");
        }
        else
        {
            System.out.print("Invalid input.");
        }
    }
    
    
    public static boolean blacklistTest(String word)
    {
        for(int i = 0; i < WORDSBLACKLIST.length; i++)
        {
            if (WORDSBLACKLIST[i].equalsIgnoreCase(word))
            {
                return true;
            }
        }
        return false;
    }
}
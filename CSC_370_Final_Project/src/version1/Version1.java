package version1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mscetinel
 */
public class Version1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        System.out.println("Hello World");
       
        FileInputStream fin = new FileInputStream("C:\\Users\\Micah\\Documents\\Essays & School Papers\\2019-2020 School Papers\\Spring 2020\\CSC 370\\CSC 370 Final Project\\1.txt");
        Scanner fileInput = new Scanner(fin);
       
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();
       
        while(fileInput.hasNext()){
           
            String nextWord = fileInput.next();
           
            if(words.contains(nextWord)){
                int index = words.indexOf(nextWord);
                count.set(index, count.get(index) +1);
            }
            else{
                words.add(nextWord);
                count.add(1);
            }
   
        }
       
       fileInput.close();
       fin.close();
       
       for(int i = 0; i < words.size(); i++ ){
           System.out.println(words.get(i) + " occured " + count.get(i) + "time(s)"    );
       }
       
    }
   
}

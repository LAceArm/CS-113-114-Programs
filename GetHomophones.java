import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
@SuppressWarnings("unchecked")
//There are two components to this program: Initialization and Search. Initializing the UALDictionary
//is in O(n), as each insertion takes a constant amount of time, and it will insert n amount of entries
//into the dictionary.
// The second part, the Search, comes in two parts: Searching for the pronounciation and 
//Searching for all Homophones. Both of these work off linear 
//Search, which is in O(n). Therefore, the entire program can be said to be in O(n).
public class GetHomophones{
   public static void main(String[] args){
      Dictionary initialDict= new UALDictionary<String,Pronunciation>();
      Dictionary searchDict= new UALDictionary<String,String>();
      long start=System.currentTimeMillis();
      File file=new File("cmudict.0.7a.txt");
      Scanner scan=new Scanner(System.in);
      try{
         Scanner reader=new Scanner(file);
         while (reader.hasNext()){
            String next=reader.nextLine();
            if (next.substring(0,3).equals(";;;")) //Ignores the start
               continue;
            Pronunciation p=new Pronunciation(next);
            initialDict.insert(p.getWord(),p);  //One dictionary to get the pronounciation of user input
            searchDict.insert(p.getPhonemes(),p.getWord()); //One to search for the homophones
         }
         reader.close();
      }
      catch (FileNotFoundException e){
         e.printStackTrace();
      }
     String toFind;
     do{
        System.out.println("Enter a word: ");
        toFind=scan.nextLine().toUpperCase();
        if (toFind.equals("0 0"))
            break;
        Object temp=initialDict.find(toFind); //Find returns an object
        while (temp==null){
            System.out.println("Word not found. Enter another: ");
            toFind=scan.nextLine().toUpperCase();
            temp=initialDict.find(toFind);
        }
        Pronunciation homophone=(Pronunciation)(temp); //Type casted from Object to Pronunciation
        ArrayList <String>allHomophones=(ArrayList <String>)(searchDict.findAll(homophone.getPhonemes())); //More type casting
     
        for (int i=0;i<allHomophones.size();++i)
            System.out.println(allHomophones.get(i));
     }while(!toFind.equals("0 0"));

   }
}
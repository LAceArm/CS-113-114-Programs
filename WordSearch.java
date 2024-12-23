import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class WordSearch{
   public static void main(String[] args){
      Scanner scan=new Scanner(System.in);
      File words=new File("words.txt");
      ArrayList<String> lst=new ArrayList <String>();
      
      try{
         Scanner reader=new Scanner(words);
         while (reader.hasNext()){
            lst.add(reader.nextLine());
         }
         reader.close();
      }
      catch (FileNotFoundException e){
         e.printStackTrace();
      }
      System.out.println("Enter a string of characters. Please keep it below 9 letters:");
      String wordSearch=scan.nextLine();
      while(wordSearch.length()>12){
         System.out.println("Please keep your input below 9 letters:");
         wordSearch=scan.nextLine();
      }
       wordSearch=wordSearch.toLowerCase();
      ArrayList <String> longestWord=new ArrayList <String>();
      for (int i=0;i<lst.size();i++){
         if (hasLetters(wordSearch,lst.get(i))){
            if (longestWord.isEmpty())
               longestWord.add(lst.get(i));
            if (!longestWord.isEmpty() && longestWord.get(0).length()<lst.get(i).length()){
               longestWord.clear();
               longestWord.add(lst.get(i));
            }
            else if (!longestWord.isEmpty() && longestWord.get(0).length()==lst.get(i).length())
               longestWord.add(lst.get(i));
         }
      }
      Collections.sort(longestWord);
      for (String s: longestWord)
         System.out.println(s);
   } 
   
    public static boolean hasLetters(String word1,String word2){
      ArrayList <Character> array1=toCharArray(word1);
      ArrayList <Character> array2=toCharArray(word2);
      ArrayList <Character> used=new ArrayList<Character>();
      for (Character letter: array2){
         if (!array1.contains(letter))
            return false;
         if (used.contains(letter) && charCounter(letter,array2)!=charCounter(letter,array1))
            return false;
         else
            used.add(letter);
      }
      return true;
   }
   
   private static ArrayList <Character> toCharArray(String l){
      ArrayList <Character> returnArray=new ArrayList <Character>();
      for (int c=0;c<l.length();++c){
         Character ch=l.charAt(c);
         returnArray.add(ch);
      }
      return returnArray;
   }
   private static int charCounter(Character c,ArrayList<Character> ch){
      int count=0;
      for (Character var:ch)
         if (var.equals(c))
            count++;
      return count;
   }
   
}
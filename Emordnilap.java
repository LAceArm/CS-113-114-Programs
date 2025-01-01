import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Emordnilap{
   public static void main(String[] args){
      Scanner scan=new Scanner(System.in);
      File words=new File("words.txt");
      ArrayList<String> dictionary=new ArrayList<String>();
      try{
         Scanner reader=new Scanner(words);
         while(reader.hasNext()){
            dictionary.add(reader.next());
         }
         reader.close();
      }
      catch(FileNotFoundException e){
         e.printStackTrace();
      }
      System.out.println("Determining emordnilaps.");
      while(true){
         System.out.print("Enter a word: ");
         String potEmo=scan.nextLine();
         if(potEmo.equals("0 0"))
            break;
         while(!dictionary.contains(potEmo)){
            System.out.println("Word not found.");
            System.out.print("Try Again: ");
            potEmo=scan.nextLine();
         }
         String emo=emordinilapCheck(potEmo,dictionary);
         if(emo==null)
            System.out.println("This word is not an emordnilap");
         else{
            System.out.println("This word is an emordnilap!");
            System.out.println(potEmo+" "+emo);
         }
        System.out.println("Enter 0 0 to end the program.");
      }
   }
   public static String emordinilapCheck(String word,ArrayList<String> dictionary){
      String check="";
      for(int i=word.length()-1;i>=0;--i)
         check+=word.charAt(i);
      if(dictionary.contains(check))
         return check;
      else
         return null;
   }
}
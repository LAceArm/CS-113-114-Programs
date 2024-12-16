import java.util.Scanner;
public class Palindrome{
   public static void main(String[] args){
      Scanner scan=new Scanner(System.in);
      String potentialPalindrome;
      System.out.println("Enter the potential palindrome: ");
      potentialPalindrome=scan.nextLine();
      if (palindrome(potentialPalindrome))
         System.out.println("This is a palindrome.");
      else
         System.out.println("This is not a palindrome.");
      
   }
   public static boolean palindrome(String word){
      String lower=word.toLowerCase();
      if (lower.length()<2)
         return true;
      else{
         if (lower.charAt(0)==lower.charAt(lower.length()-1) && palindrome(lower.substring(1,lower.length()-1)))
            return true;
         else
            return false;   
            
      }
   }
}
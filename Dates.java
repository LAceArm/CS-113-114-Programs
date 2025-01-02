import java.util.Scanner;
public class Dates{
   public static void main(String [] args){
      int month,day,year;
      int daysInMonth;
      boolean monthValid,dayValid,yearValid;
      boolean leapYear;
      Scanner scan=new Scanner (System.in);
      System.out.println("Enter a valid date between the years 1000-2000.");
      System.out.print("Enter this date in the month/day/year format. "+
      "\nRefrain from using slashes: ");
      month=scan.nextInt();
      day=scan.nextInt();
      year=scan.nextInt();
      //Month Checker
      if (month>12)
         monthValid=false;
      else 
         monthValid=true;
      //Year Checker
      if (year>2000)
         yearValid=false;
      else 
         yearValid=true;
     //Leap year checker. Calendars are weird
     leapYear=false; //Initialized this so it doesn't cause problems later.
     if (year%4==0){
         if (year%100==0){
            if (year%400==0)
               leapYear=true;
            else
               leapYear=false;
         }
         else
            leapYear=true;
     }
     if (month==1 || month==3 || month==5 || month==7 || month==8 ||month==10 || month==12)
         daysInMonth=31;
     else if(month==2){
         if (leapYear)
            daysInMonth=29;
         else
            daysInMonth=28;
     }
     else
         daysInMonth=30;
         
     if (day<=daysInMonth)
         dayValid=true;
     else
         dayValid=false;
    
    if (dayValid && monthValid && yearValid)
         System.out.println("This is a valid date.");
    else
         System.out.println("This is not a valid date.");
    
     
   }
}
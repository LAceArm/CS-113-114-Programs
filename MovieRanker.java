import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;
@SuppressWarnings("unchecked")
public class MovieRanker{
   public static void main(String[] args){
      File database=new File("ratings.tsv");
      Scanner scan=new Scanner(System.in);
      final double MAX_REVIEWS=2e6; 
      final double MAX_MOVIES=1e5; 
      int numMovies,minVotes;
     ArrayList<MovieRating> reel=new ArrayList<MovieRating>();
     PriorityQueue<MovieRating> criterion=new PriorityQueue<MovieRating>();
     try{
         Scanner ebrt=new Scanner(database);
         while(ebrt.hasNext()){
            String next=ebrt.nextLine();
            String[] tkns=next.split("\\t");
            int votes=Integer.parseInt(tkns[0]);
            int score=Integer.parseInt(tkns[1]);
            MovieRating toAdd=new MovieRating(tkns[2],score,votes);
            reel.add(toAdd);
         }
         ebrt.close();     
     }
     catch (FileNotFoundException e){
         e.printStackTrace();
      }
      while(true){
         System.out.println("Enter the number of movies in the ranking: ");
         numMovies=scan.nextInt();
         System.out.println("Enter the minimum number of reviews: ");
         minVotes=scan.nextInt();
         if(numMovies==0 || minVotes==0)
            break;
         ArrayList<MovieRating>roll=minVoteArray(reel,minVotes);
         int num=0;
         for(int i=0;i<roll.size();++i){
            if(num<numMovies){
          //     System.out.println(roll.get(i));
               criterion.add(roll.get(i));
               num++;
            }
            else if(num==numMovies){
               MovieRating curr=roll.get(i);
               MovieRating low=criterion.peek();
               if(curr.getRating()>low.getRating()){
                  criterion.poll();
                  criterion.add(curr);
               }
               else 
                  continue;  
            }
         }
         Object[] topList=criterion.toArray();
         for(int i=topList.length-1;i>=0;--i){
            MovieRating kino=(MovieRating)(topList[i]);
            System.out.println(kino);
         }
      }
      
   }
     
     public static ArrayList<MovieRating> minVoteArray(ArrayList<MovieRating> inArray,int minVotes){
         ArrayList<MovieRating> reviews=new ArrayList<MovieRating>();
         Object[] iterator=inArray.toArray();
         for(int i=0;i<iterator.length;++i){
            MovieRating rec=(MovieRating)(iterator[i]);
            if(rec.getVotes()>=minVotes)
               reviews.add(rec);
            else
               continue;
         }
        return reviews;
     }
 }
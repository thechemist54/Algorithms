/**  
   Program to test basic functionality of the AnimalTransport class.
*/

public class IWMOTest
{
   public static void main(String [] args)
   {
      AnimalTransport problem = new AnimalTransport("iwmo1.csv");
      
      String test2 = "Asian elephant,Owl monkey,Cheetah,Mongoose lemur,African elephant";
      StringBuilder all = new StringBuilder();
      int containers = 0;
      String output1 = "NONE";
      try
      {
         java.util.Scanner scan =
                 new java.util.Scanner(new java.io.File("iwmo1.csv"));
         String line;
         while(scan.hasNextLine())
         {
            line = scan.nextLine();
            String w = line.substring(0, line.indexOf(','));
            all.append(w + ",");
         }
         String test1 = all.substring(0, all.length()-1);
         output1 = problem.getListing(test1);
         containers = problem.minContainers(test1);
      }
      catch (Exception e)
      {
         System.out.println(e);
      }

      System.out.println("\n" + containers + " container(s)");
      System.out.println(output1);
      System.out.println(containers == 5);
      System.out.print("\nTransport requires " + problem.minContainers(test2) + " containers:");
      System.out.println(problem.getListing(test2));
      /* Expectations: 2 containers (one with cheetah, dog, and vesper mouse)        */
      
      String test3 = "Owl monkey,Mountain beaver,Vesper mouse,Three-toed sloth";
      System.out.print("\nTransport requires " + problem.minContainers(test3) + " containers:");
      System.out.println(problem.getListing(test3));
      /* Expectations: 1 container carrying all four animals                        */
   }   
}

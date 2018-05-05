/** Name: Aaman Bhandari
   Due Date: 13th April, 2018
   Program description: The objective of the program is to compute
   the minimum number of containers that are needed to transport the given number
   of animals.
 */


import java.util.*; //importing java packages
import java.io.*;

/**
    This program computes the minimum number of containers needed to transfer
    the required number of animals from one zoo to another. This solution uses an
    adjacency matrix to represent whether two animals can be transported together
    or not based on the status and classification information given. By using this
    adjacency matrix the minimum number of containers is calculated using graph
    coloring backtracking algorithm and similarly the animals in a particular
    container is obtained.
*/
public class AnimalTransport
{
    //initializing a map where the associated value is a class which helps keep track of classification
    // status and name of the animal
    private Map<String,AnimalCon> mapp;   //private instance variables

    private int[][] g; //adjacency matrix

    private int[] k;

    /**
     * An argument constructor that reads from a text file and associates values to
     * map accordingly.
     * @param filename read from file provided.
     */

    public AnimalTransport(String filename)
    {
        try
        {
            Scanner in = new Scanner(new File(filename)); //read from file

            AnimalCon d;
            mapp = new HashMap<>();


            while(in.hasNextLine())
            {
                /**splitting the species name,classification and status and
                putting that array into a map accordingly.*/
                String[] arr = in.nextLine().toLowerCase().split(",");
                d = new AnimalCon(arr[0],arr[1],arr[3]);
                mapp.put(arr[0],d);

            }

            in.close();




        }


        catch(Exception e)
        {

        }
    }

    /**
     * Calculate the minimum amount of containers required to transfer the animals.
     * @param animals the animals needed to be transferred.
     * @return the minimum number of containers required to transfer the animals.
     */

    public int minContainers(String animals)
    {
        String[] arr1 = animals.toLowerCase().split(",");
         g = graphing(arr1); //making an adjacency matrix

        int[] containers = new int[arr1.length];

        int m = arr1.length;

        m_containers(containers,-1,m);  //calling helper method to calculate the
                                             //minimum number of containers
        Set<Integer> st1 = new HashSet<>();
        for(int i = 0;i<arr1.length;i++)
        {
            st1.add(k[i]);
        }
        return st1.size();

    }

    /**
     * Helper method to calculate the minimum number of containers.
     * @param arr the containers for the animals to be kept
     * @param index the index to be checked and evaluated.
     * @param length the length of arr.
     */

    private void m_containers(int[] arr, int index, int length)
    {
        int c;
        if(isSafe(index,arr)) //checks whether the index is feasible or not.
        {
            if(index==length-1)
            {
                k  = new int[length];

                k = Arrays.copyOf(arr,length);


            }


            else
            {
                for(c=0;c<length;c++)
                {
                    arr[index+1]=c;
                    m_containers(arr,index+1,length); //recursive call to
                }                                          //check for other indexes.
            }

        }

    }

    /**
     * Check whether the passed index is feasible to be added or not.
     * @param index the index to be checked.
     * @param arr1 the array of containers.
     * @return whether the index is feasible or not.
     */

    private boolean isSafe(int index,int[] arr1)
    {
        int j;
        boolean flag = true;
        j = 0;
        while(j<index && flag)
        {
            if(g[index][j]==0 && arr1[index]==arr1[j])
            {
                flag = false;
            }
            j++;
        }
        return flag;

    }

    /**
     * The listing of animals in their particular containers.
     * @param animals the animals that are needed to be put in containers.
     * @return the listing of animals in their respective containers.
     */


    public String getListing(String animals)
    {
        String[] an = animals.split(",");
        int m = an.length;
        int[] containers = new int[m];
        m_containers(containers,-1,m);
        String r ;

        Map<String, Integer> ma1 = new HashMap<>();
        Set<Integer> st = new HashSet<>(); //initializing a set
        String q = "";

        for(int i = 0;i<an.length;i++)
        {
            st.add(k[i]);
            ma1.put(an[i],k[i]);
        }
        Iterator<Integer> iter = st.iterator(); //iterator to iterate through set
        int n = 0;
        while(iter.hasNext())
        {
            int item = iter.next();
            n+=1;
            r="";
            for(int i = 0;i<an.length;i++)
            {
                if(ma1.get(an[i])==item)
                {
                    r+=an[i]+", ";
                }
            }
            q+="\nContainer "+n +" = " +r.substring(0,r.length()-2)+".";
        }

        return q;

    }

    /**
     * Private helper method to construct the adjacency matrix
     * @param arr the array for which the adjacency matrix needs to be made.
     * @return the adjacency matrix.
     */

    private int[][] graphing(String[] arr)
    {
        int[][] graph = new int[arr.length][arr.length];
        for(int i = 0;i<arr.length;i++)
        {
            for(int j = 0;j<arr.length;j++)
            {
                graph[i][j] = 1;
            }

        }
        for(int i = 0;i<arr.length;i++)
        {
            for(int j = 0;j<arr.length;j++)
            {

                if(mapp.get(arr[i]).getStatus().equals("en"))
                {
                    graph[i][j]=0;

                }
                else if(mapp.get(arr[j]).getStatus().equals("en"))
                {
                    graph[i][j]=0;
                }


                else
                {
                    if(mapp.get(arr[i]).getClassification().equals("carnivore"))
                    {
                        if(mapp.get(arr[j]).getClassification().equals("herbivore"))
                        {
                            if (mapp.get(arr[i]).getStatus().equals("do")
                                    && mapp.get(arr[j]).getStatus().equals("do"))
                            {
                                graph[i][j] = 1;
                            }
                            else
                            {
                                graph[i][j] = 0;
                            }
                        }

                        else
                        {
                            if(mapp.get(arr[j]).getStatus().equals("vu")||
                                    mapp.get(arr[j]).getStatus().equals("nt"))
                            {
                                graph[i][j] = 0;
                            }


                        }
                    }
                    else if(mapp.get(arr[i]).getClassification().equals("herbivore"))
                    {
                        if(mapp.get(arr[j]).getClassification().equals("carnivore"))
                        {
                            if (mapp.get(arr[i]).getStatus().equals("do")
                                    && mapp.get(arr[j]).getStatus().equals("do"))
                            {
                                graph[i][j] = 1;
                            }
                            else
                            {
                                graph[i][j] = 0;
                            }
                        }

                    }
                    else if(mapp.get(arr[i]).getStatus().equals("vu")||
                            mapp.get(arr[i]).getStatus().equals("nt"))
                    {
                        if(mapp.get(arr[j]).getClassification().equals("carnivore"))
                        {
                            graph[i][j]=0;
                        }

                    }


                }

                if(i==j)
                {
                    graph[i][j]=1;
                }
            }
        }
        return graph;


    }



}

/**
 * Name: Aaman Bhandari
 * Due Date: 4 May 2018
 * Program Description: Use breadth-first algorithm to efficiently traverse through
 * a directed graph and perform various operations like finding the shortest
 * chain of path from one vertex to another, counting numbers of vertices visited
 * from one word, etc.
 */


import java.util.*;
import java.io.*;    //importing the Input/Output and Utility packages

/**
 * WordPairs class functions as the class that uses DiGraph class to make a directed
 * graph of pairs of words and in this way calculate various paths and number of paths
 * within a certain level of the graph or the number of reachable words from one point, etc.
 */
public class WordPairs
{
    private DiGraph d;   //private instance variable to make use of DiGraph

    /**
     * An argument constructor for reading from file and pass that to Digraph class
     * to make a directed graph of whatever is passed in the text file.
     * @param filename The file to be read.
     */

    public WordPairs(String filename)
    {
        try
        {
            d = new DiGraph();
            Scanner in = new Scanner(new File(filename));

            while(in.hasNextLine())
            {
                /**
                 * Splitting the words in the file and sending it to the DiGraph class
                 * to make a certain graph.
                 */
                String[] arr = in.nextLine().split(" ");

                d.addVertex(arr[0]);

                d.addVertex(arr[1]);

                d.addEdge(arr[0],arr[1]);

            }
        }
        catch(Exception e)
        {

        }
    }

    /**
     * The wordChain class returns the sequence of word pairs that begins and ends with
     * the words given as argument
     * @param first The first word to start from.
     * @param last The last word to end with.
     * @return The string with the shortest path from first to last.
     */

    public String wordChain(String first, String last)
    {
        /**
         * Defining maps for the visited nodes and previous nodes
         */
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        Queue<String> qu = new LinkedList<>(); //defining a queue for BFS algorithm
        List<String> li = new LinkedList<>(); //defining a list to store the vertices

        /**
         * Using BFS to check and determine the word chain.
         */

        qu.add(first);
        String current = first;
        visited.put(first, true);
        while(!qu.isEmpty())
        {
            current = qu.remove();
            if(current.equals(last))
            {
                break;
            }

            else
            {
                for(String each: d.getAdjacent(current) )
                {
                    if(!visited.containsKey(each))
                    {
                        qu.add(each);
                        visited.put(each, true);
                        previous.put(each, current);
                    }
                }
            }
        }

        /**
         * Return [] if word chain not possible.
         */
        if(!current.equals(last))
        {
            return "[]";

        }
        /**
         * Add the value from map to list and manipulate the list to
         * return the required output.
         */
        for(String i = last; i!=null; i=previous.get(i))
        {
            li.add(i);
        }

        Collections.reverse(li);

        String s="[";

        for(int i = 0;i<li.size()-1;i++)
        {
            s+=li.get(i)+" "+li.get(i+1)+",";
        }


        return s.substring(0,s.length()-1)+"]";
    }

    /**
     * Calculates the length of chain of words by checking the chain using
     * wordChain method.
     * @param first The first word to start from.
     * @param last The Last word to end with.
     * @return The length of the chain.
     */
    public int chainLength(String first, String last)
    {
        String s = wordChain(first,last);
        if(s.equals("[]"))
        {
            return Integer.MAX_VALUE;  //returns infinite value if chain not possible
        }
        String[] arr = s.split(",");
        return arr.length;

    }

    /**
     * Calculates the number of distinct words from the starting word.
     * @param word The word to start from.
     * @return The number of words reachable from word passed as argument.
     */

    public int reachableFrom(String word)
    {
        /**
         * Implementing BFS like previous methods
         * and returning size of the list.
         */
        Queue<String> qu = new LinkedList<>();
        Set<String> li = new HashSet<>();
        qu.add(word);
        while(!qu.isEmpty())
        {
            String item = qu.poll();
            li.add(item);
            Iterator<String> iter = d.getAdjacent(item).iterator();
            while(iter.hasNext())
            {
                String item1 = iter.next();
                if(!li.contains(item1))
                {
                    qu.add(item1);
                    li.add(item1);
                }
            }
        }
        return li.size();


    }

    /**
     * Another reachableFrom method that returns the number of words form
     * parameter and goes to the maximum level of maxLength.
     * @param word The word to start from.
     * @param maxLength The Level upto which to check.
     * @return The words reachable from word upto level maxLength.
     */

    public int reachableFrom(String word, int maxLength)
    {
        /**
         * Defining various sets to trace the parent nodes and return
         * the required output accordingly.
         */
        Set<String> stt = new HashSet<>();
        Set<String> stt2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        visited.add(word);
        stt.add(word);

        while(visited.size()>0 && maxLength>0)
        {
            Iterator<String> iter = stt.iterator();  //iterate through stt
            while(iter.hasNext())
            {
                Iterator<String> iter1 = d.getAdjacent(iter.next()).iterator();

                while(iter1.hasNext())
                {
                    String item = iter1.next();
                    if(!visited.contains(item))
                    {
                        stt2.add(item);
                        visited.add(item);
                    }
                }
            }
            stt=stt2;

            stt2 = new HashSet<>();
            maxLength-=1;
        }
        return visited.size();

    }

    /**
     * Determines the words reachable from word to the max level of maxLength
     * @param word The word to start from.
     * @param maxLength The number of levels to check.
     * @return The string with every words reachable from word with maxLength level.
     */

    public String reachableWords(String word, int maxLength)
    {
        int count = 0;
        /**
         * Defining various maps and sets to determine the return value accordingly.
         */
        Map<Integer, Set<String>> mapp = new HashMap<>();
        Set<String> stt = new HashSet<>();
        Set<String> stt2 = new HashSet<>();
        Set<String> visited = new HashSet<>();
        visited.add(word);
        stt.add(word);

        while(visited.size()>0 && maxLength>0)
        {
            Iterator<String> iter = stt.iterator();
            while(iter.hasNext())
            {
                Iterator<String> iter1 = d.getAdjacent(iter.next()).iterator();
                while(iter1.hasNext())
                {
                    String item = iter1.next();
                    if(!visited.contains(item))
                    {
                        stt2.add(item);
                        visited.add(item);
                    }
                }
            }
            count+=1;
            maxLength-=1;
            if(stt2.size()>0)
            {
                mapp.put(count, stt2);
            }
            stt=stt2;

            stt2 = new HashSet<>();

        }

        String s = "["+word+"]\n";

        Iterator<Integer> iter2 = mapp.keySet().iterator();
        while(iter2.hasNext())
        {
            List<String> lii = new ArrayList<>(mapp.get(iter2.next()));
            Collections.sort(lii);
            s+=lii+"\n";
        }


        return s;

    }

    /**
     * Determines the sequence of words that start and end from word.
     * @param word The word to start and end with.
     * @return The sequence of words that start and end with word(if possible).
     */

    public String cycle(String word)
    {
        String s = "";

        /**
         * Loop through each value of set and determine chain of words.
         */
        for(String each: d.getAdjacent(word) )
        {
            s=wordChain(each,word);
            if(s.length()>2)
            {
                break;
            }
        }
        if(s.equals("[]"))
        {
            return "[]";
        }
        String arr[] = s.split(" ",2);

        return "["+word+" " +arr[0].substring(1)+","+s.substring(1);
    }

}

import java.util.*;   //importing util package

/**
 * This class reads through an array of non-negative
 * integers,where each integer in the cell is the
 * cost of that cell and calculates the minimum cost required to
 * reach the final cell.
 */
public class SlideOrJump
{
    private int[] board;
    private int index = 0;
    private long cost = 0;
    private String moves="";    //private instance variables
    private long[] arr;
    private int count=0;
    private int count1 = 0;
    private boolean flag = true;

    /**
     * Initializes the array with the array past as argument.
     * @param board the array to find the optimal solution.
     */

    public SlideOrJump(int [] board)
    {
        this.board = board;
        arr = new long[board.length+1];

    }

    /**
     * Find the optimal solution recursively.
     * @return another method which returns a number after solving the array recursively.
     */

    public long recSolution()
    {

        return recur(board,0);



    }

    /**
     * Find the optimal solution using dynamic programming.
     * @return the cost obtained after solving the array using dynamic programming.
     */

    public long dpSolution()
    {

        moves = "";

        if (isAscending(board))     //checks whether array is ascending and
                                    //handles the array accordingly
        {
            index = 0;
            if (board.length == 2) {
                moves += "S";
                count1 += 1;
                return board[board.length - 1];

            }
            if ((board.length) % 2 != 0)
            {

                for (int i = 0; i <board.length; i+=2)
                {
                    count1+=1;
                    index+=1;
                    arr[index] = board[i]+arr[index-1];



                }
                int d = 1;

                while(d<=board.length/2)
                {
                    moves+="J";
                    d++;
                }
            }

            else if((board.length)%2 ==0)
            {

                moves+="S";
                arr[0]=board[0];

                for(int i = 1;i<board.length;i+=2)
                {
                    index+=1;
                    count1+=1;
                    arr[index] = board[i]+arr[index-1];

                }

                int d = 1;
                while(d<=(board.length/2)-1)
                {
                    moves+="J";
                    d++;
                }
            }
        }
        else    //uses while loop to find the minimum cost
        {
            index = 0;

            boolean flag1 = true;
            while (flag1 && index < board.length)
            {

                if (adjacentEnd(board, index)) {
                    arr[index] = arr[index] + board[index + 1];
                    moves += "S";

                    flag1 = false;
                }
                else if (thirdEnd(board, index)) {
                    arr[index] = arr[index] + board[index + 2];
                    moves += "J";

                    flag1 = false;
                }
                else
                {
                    int a;
                    int k = Math.min(board[index + 1], board[index + 2]);

                    //adds "S"or"J" based on the smaller number
                    moves+=(board[index+1]<board[index+2])? "S":"J";
                    a = (board[index+1]<board[index+2])? 1:2;
                    index += (board[index+1]<board[index+2])? 1:2;


                    arr[index] = arr[index-a ] + k;




                }

                count1+=1;
            }
        }


        return arr[index];


    }

    /**
     * The moves used to get the optimal solution.
     * @return the respective move to get the optimal solution.
     */

    public String getMoves()
    {
        System.out.println("No. of recursive calls: "+count);
        System.out.println("No. of dynamic calls: "+count1);
        return moves;
    }

    /**
     * Checks if the index is at the end.
     * @param board array to be checked.
     * @param index index to be checked.
     * @return true or false based on the value of index.
     */

    private boolean isEnd(int[] board,int index)
    {
        if(index == board.length-1)
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if the index is at adjacent to the end.
     * @param board array to be checked.
     * @param index index to be checked.
     * @return true or false based on the value of index.
     */

    private boolean adjacentEnd(int[] board,int index)
    {
        if(index == board.length-2)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks if the index is at third from the end.
     * @param board array to be checked.
     * @param index index to be checked.
     * @return true or false based on the value of index.
     */

    private boolean thirdEnd(int[] board,int index)
    {
        if(index == board.length-3)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the array is in ascending order or not.
     * @param board the array to be checked.
     * @return true or false based on the order of the array.
     */

    private boolean isAscending(int[] board)
    {
        int[] board1=Arrays.copyOf(board,board.length);

        Arrays.sort(board1);   //sorts using quicksort

        boolean val = false;
        for (int i = 1;i<board.length;i++)
        {
            if(board1[i]==board[i])
            {
                val=true;
            }
            else
            {
                val = false;
                break;
            }
        }

        return val;
    }

    /**
     * Method called by recSolution to calculate optimal solution.
     * @param board the array to check for optimal solution.
     * @param ind the index from which to be checked.
     * @return the cost after solving array optimally.
     */

    private long recur(int[] board,int ind)
    {

        if (flag && isAscending(board)) //checks if array is ascending
        {

            if (board.length == 2) {
                moves += "S";

                return board[board.length - 1];

            }
            if ((board.length) % 2 != 0)
            {

                for (int i = 0; i < board.length; i+=2)
                {

                    cost+=board[i];



                }

                int d = 1;

                while(d<=board.length/2)
                {
                    moves+="J";
                    d++;
                }
            }

            else if((board.length)%2 ==0)
            {
                moves+="S";
                for(int i = 1;i<board.length;i+=2)
                {
                    cost+=board[i];



                }
                int d = 1;
                while(d<=(board.length/2)-1)
                {
                    moves+="J";
                    d++;
                }
            }
            return cost;
        }

        else
        {


            if (board.length == 2) {
                moves += "S";

                return board[board.length - 1];

            }
            else
            {


                if (isEnd(board, ind)) {

                    return board[ind];
                }
                else if (adjacentEnd(board, ind)) {
                    moves += "S";


                    return board[ind]+board[ind+1];

                }
                else if (thirdEnd(board, ind)) {

                    moves += "J";



                     return board[ind]+board[ind+2];


                }


                flag = false;
                //add "S" or "J" based on the smaller amount in the index
                moves+=(board[ind+1]<board[ind+2])? "S":"J";
                count+=1;



                //recursion based on the minimum index
                return board[ind]+Math.min(recur(board,ind+1),recur(board,ind+2));


            }
        }


    }




}
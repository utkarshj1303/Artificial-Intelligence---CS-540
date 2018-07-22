import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;

	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}
        //finds which state is bigger if they are viewed as an integer
        public boolean bigger(State a, State b)
        {
            for(int i=0;i<9;i++)
            {
                if(a.board[i]!=b.board[i])
                    return a.board[i]<b.board[i];
            }
            return false;
        }

	public State[] getSuccessors() {
		State[] successors = new State[4];
                int space=0;
                //finds the index of the space
		for(int i=0;i<9;i++)
                {
                    if(board[i]==0)
                    {
                        space=i;
                        break;
                    }
                }
                int[] arr = new int[9];
                int inc=0;
                //all tiles in the same row or column as the space can be swaped with it so add configurations where space 
                //is swapped with each elements with same row or column
                for(int i=0;i<9;i++)
                {
                    if(((i%3 == space%3) || ((i/3)==(space/3))) && i!=space)
                    {
                        for(int j=0;j<9;j++)
                        {
                            if(j!=space && j!=i)
                                arr[j] = board[j]; 
                            else
                                arr[j] = board[j==i?space:i];
                        }
                        State x = new State(arr);
                        successors[inc++]=x;
                    }
                }
                //sort the configurations using bubble sort
		for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3-i;j++)
                    {
                        if(bigger(successors[j+1],successors[j]))
                        {
                            State temp = successors[j];
                            successors[j] = successors[j+1];
                            successors[j+1] = temp;
                        }
                    }
                }
		return successors;
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

public class Torus {

	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
                            System.out.println(successor.getBoard());
;
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;
                        boolean getout=false;
                        
                        //outer loop for iterative deepening
			while (true) 
                        {	stack.clear();
                                prefix.clear();
				stack.push(init);
                                prefix.add(init);
                                //dfs loop
				while (!stack.isEmpty()) 
                                {
                                    maxStackSize = maxStackSize>stack.size()?maxStackSize:stack.size();
                                    State x = stack.pop();
                                    if(x!=init)
                                    {
                                        //removes elements from the prefix array until the parent of the state is found
                                        for(int i=prefix.size()-1;i>=0;i--)
                                        {
                                       
                                            if(prefix.get(i).equals(x.parentPt))
                                            {
                                                prefix.add(x);
                                                break;
                                            }
                                            else
                                            {
                                                prefix.remove(i);
                                            }
                                        }
                                    }
                                    if(option==2)
                                        System.out.println(x.getBoard());
                                    if(option==3)
                                    {
                                        System.out.print(x.getBoard()+" ");

                                        if(x.parentPt!=null)
                                            System.out.println("parent "+x.parentPt.getBoard());
                                        else
                                            System.out.println("parent 0 0 0 0 0 0 0 0 0");

                                    }
                                    //for option 4, print the prefix path till first state checked whose depth is equal to cutoff
                                    if(option==4 && x.depth==cutoff)
                                    {   
                                        Stack<State> st = new Stack<>();
                                        st.push(x);
                                        while(x.parentPt!=null)
                                        {
                                            x=x.parentPt;
                                            st.push(x);
                                        }
                                        while(!st.empty())
                                        {
                                            System.out.println(st.pop().getBoard());
                                        }
                                        break;
                                    }
                                    goalChecked++;
                                    if(x.isGoalState())
                                    {
                                        //print prefix path if goal is reached before cutoff for option 4
                                        if(option==4 && x.depth!=cutoff)
                                        {                                        
                                            Stack<State> st = new Stack<>();
                                            st.push(x);
                                            while(x.parentPt!=null)
                                            {
                                                x=x.parentPt;
                                                st.push(x);
                                            }
                                            while(!st.empty())
                                            {
                                                System.out.println(st.pop().getBoard());
                                            }
                                        }
                                        if(option==5)
                                        {
                                            Stack<State> st = new Stack<>();
                                            st.push(x);
                                            while(x.parentPt!=null)
                                            {
                                                x=x.parentPt;
                                                st.push(x);
                                            }
                                            while(!st.empty())
                                            {
                                                System.out.println(st.pop().getBoard());
                                            }
                                            getout=true;
                                            System.out.println("Goal-check "+goalChecked);
                                            System.out.println("Max-stack-size "+maxStackSize);
   
                                            
                                        }
                                        break;
                                    }
                                    //only add successors to the stack if depth of the state is less than the cutoff
                                    if(x.depth<cutoff)
                                    {
                                        State[] next = x.getSuccessors();
                                        for(int i=0;i<4;i++)
                                        {
                                            boolean has = false;
                                            //adds state to stack only if its not already in the prefix
                                            for(int j=0;j<prefix.size();j++)
                                            {
                                                if(prefix.get(j).equals(next[i]))
                                                {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if(!has)
                                            {
                                                next[i].parentPt = x;
                                                next[i].depth = x.depth+1;
                                                stack.push(next[i]);
                                            }
                                        }
                                    }

                                }
                                //break out of outer while loop if option is not equal to 5 or if goal has been reached
                                if (option!= 5 || getout)
                                    break;
                                //if option is not equal to 5 or the if the goal has not been reached then increment the cutoff for iterative deepening
                                cutoff++;
                                


                        }

				
				

			}
		}
}



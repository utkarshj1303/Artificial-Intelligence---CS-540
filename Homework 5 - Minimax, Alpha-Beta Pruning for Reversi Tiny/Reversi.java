import java.util.*;
class State {
    char[] board;
    public State(char[] arr) {
        this.board = Arrays.copyOf(arr, arr.length);
    }
    public int getScore() {
                int one=0;
                int two=0;
                for(int i=0;i<16;i++)
                {
                    if(board[i]=='1')
                        one++;
                    else if(board[i]=='2')
                        two++;
                }
                if(one==two)
                    return 0;
                else if(one>two)
                    return 1;
                else
                    return -1;
    }
    
    public boolean isTerminal() 
    {
        return getSuccessors('1').length==0 && getSuccessors('2').length==0;
    }
    int[] dx = {1,-1,0,0,1,-1,1,-1};
    int[] dy = {0,0,1,-1,1,-1,-1,1};
    boolean inside(int x,int y)
    {
        return x>=0 && x<4 && y>=0 && y<4;
    }
    public State[] getSuccessors(char player) {
        ArrayList<State> successor = new ArrayList();
        char opp;
        if(player=='1')
            opp = '2';
        else
            opp = '1';
        char[][] arr = new char[4][4];
        for(int i=0;i<16;i++)
        {
            arr[i/4][i%4]=board[i];
        }
        char[][] a = new char[4][4];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                for(int d=0;d<4;d++)
                {
                    for(int f=0;f<4;f++)
                    {
                        a[d][f] = arr[d][f];
                    }
                }
                if(arr[i][j]=='0')
                {
                    boolean flag=false;    
                    for(int k=0;k<8;k++)
                    {
                        int x = i+dx[k];
                        int y= j+dy[k];
                        if(inside(x,y) && arr[x][y]==opp)
                        {
                            while(inside(x,y) && arr[x][y]!=player && arr[x][y]!='0')
                            {
                                x+=dx[k];
                                y+=dy[k];
                            }
                            if(inside(x,y))
                            {
                                if(arr[x][y]==player)
                                {
                                    int q=i;
                                    int w=j;
                                    flag=true;
                                    while(q!=x || w!=y)
                                    {
                                        a[q][w] = player;
                                        q+=dx[k];
                                        w+=dy[k];
                                    }
                                }
                            }
                        }
                    }
                    if(flag)
                    {
                        char[] s = new char[16];
                        for(int c=0;c<16;c++)
                        {
                        s[c] = a[c/4][c%4];
                        }
                        State blah = new State(s);
                        successor.add(blah);
                    }
                }
            }
        }
        State[] successors = new State[successor.size()];
        for(int i=0;i<successor.size();i++)
        {
            successors[i] = successor.get(i);
        }
        return successors;
    }
 
    public void printState(int option, char player) {
        if(option==1)
        {
            State[] x = getSuccessors(player); 
            for(int i=0;i<x.length;i++)
            {
                System.out.println(x[i].getBoard());
            }
            if(x.length==0 && !isTerminal())
            {
                System.out.println(getBoard());
            }
        }
        else if(option==2)
        {
            if(!isTerminal())
                System.out.println("non-terminal");
            else
            {
                System.out.println(getScore());
            }
        }
        else if(option ==3)
        {
            System.out.println(Minimax.run(this,player));
            System.out.println(Minimax.num);
        }
        else if(option==4)
        {
            Minimax.run(this,player);
            if(Minimax.nxt!=null)
                System.out.println(Minimax.nxt.getBoard());
        }
        else if(option==5)
        {
            System.out.println(Minimax.run_with_pruning(this,player));
            System.out.println(Minimax.num);
        }
        else 
        {
            Minimax.run_with_pruning(this,player);
            if(Minimax.nxt!=null)
                System.out.println(Minimax.nxt.getBoard());
        }
    }

    public String getBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            builder.append(this.board[i]);
        }
        return builder.toString().trim();
    }

    public boolean equals(State src) {
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != src.board[i])
                return false;
        }
        return true;
    }
}
class Minimax {
        static int num;
        static State curr;
        static State nxt;
	private static int max_value(State curr_state) {
            num++;
            if(curr_state.isTerminal())
            {
                return curr_state.getScore();
            }
            State[] succ = curr_state.getSuccessors('1');
            int maxi=-2;
            int y;
            for(int i=0;i<succ.length;i++)
            {
                y = min_value(succ[i]);
                if(curr==curr_state && y>maxi)
                {
                    nxt = succ[i];
                }
                maxi = maxi>y?maxi:y;
            }
            if(succ.length==0)
            {
                State as = new State(curr_state.board);
                if(curr==curr_state)
                {
                    nxt = curr_state;
                }
                return min_value(as);
            }
            return maxi;
	}
	private static int min_value(State curr_state) {
            num++;
            if(curr_state.isTerminal())
            {
                return curr_state.getScore();
            }
            State[] succ = curr_state.getSuccessors('2');
            int mini=2;
            int y;
            for(int i=0;i<succ.length;i++)
            {
                y = max_value(succ[i]);
                if(curr==curr_state && y<mini)
                {
                    nxt = succ[i];
                }
                mini = mini<y?mini:y;
            }
            if(succ.length==0)
            {
                State as = new State(curr_state.board);
                if(curr==curr_state)
                {
                    nxt = curr_state;
                }
                return max_value(as);
            }
            return mini;
	}
	private static int max_value_with_pruning(State curr_state, int alpha, int beta) {
            num++;
            if(curr_state.isTerminal())
            {
                return curr_state.getScore();
            }
            State[] succ = curr_state.getSuccessors('1');
            int y;
            for(int i=0;i<succ.length;i++)
            {
                y = min_value_with_pruning(succ[i],alpha,beta);
                if(curr==curr_state && y>alpha)
                {
                    nxt = succ[i];
                }
                alpha = alpha>y?alpha:y;
                if(alpha>=beta)
                    return beta;
            }
            if(succ.length==0)
            {
                State as = new State(curr_state.board);
                if(curr==curr_state)
                {
                    nxt = curr_state;
                }
                return min_value_with_pruning(as,alpha,beta);
            }
            return alpha;
	}
	private static int min_value_with_pruning(State curr_state, int alpha, int beta) {
            num++;
            if(curr_state.isTerminal())
            {
                return curr_state.getScore();
            }
            State[] succ = curr_state.getSuccessors('2');
            int y;
            for(int i=0;i<succ.length;i++)
            {
                y = max_value_with_pruning(succ[i],alpha,beta);
                if(curr==curr_state && y<beta)
                {
                    nxt = succ[i];
                }
                beta = beta<y?beta:y;
                if(alpha>=beta)
                    return alpha;
            }
            if(succ.length==0)
            {
                State as = new State(curr_state.board);
                if(curr==curr_state)
                {
                    nxt = curr_state;
                }
                return max_value_with_pruning(as,alpha,beta);
            }
            return beta;
	}
        
	public static int run(State curr_state, char player) {
            num=0;
            curr = curr_state;
            nxt = null;
            if(player=='1')
                return max_value(curr_state);
            else
                return min_value(curr_state);
	}
	public static int run_with_pruning(State curr_state, char player) {
            num=0;
            curr = curr_state;
            nxt = null;
            if(player=='1')
                return max_value_with_pruning(curr_state,-2,2);
            else
                return min_value_with_pruning(curr_state,-2,2);
	}
}

public class Reversi {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        int flag = Integer.valueOf(args[0]);
        char[] board = new char[16];
        for (int i = 0; i < 16; i++) {
            board[i] = args[2].charAt(i);
        }
        int option = flag / 100;
        char player = args[1].charAt(0);
        if ((player != '1' && player != '2') || args[1].length() != 1) {
            System.out.println("Invalid Player Input");
            return;
        }
        State init = new State(board);
        init.printState(option, player);
    }
}
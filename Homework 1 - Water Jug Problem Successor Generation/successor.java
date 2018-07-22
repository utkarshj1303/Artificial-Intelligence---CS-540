import java.util.*;

public class successor {
    public static class JugState {
        int[] Capacity = new int[]{0,0,0};
        int[] Content = new int[]{0,0,0};
        public JugState(JugState copyFrom)
        {
            this.Capacity[0] = copyFrom.Capacity[0];
            this.Capacity[1] = copyFrom.Capacity[1];
            this.Capacity[2] = copyFrom.Capacity[2];
            this.Content[0] = copyFrom.Content[0];
            this.Content[1] = copyFrom.Content[1];
            this.Content[2] = copyFrom.Content[2];
        }
        public JugState()
        {
        }
        public JugState(int A,int B, int C)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
        }
        public JugState(int A,int B, int C, int a, int b, int c)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
            this.Content[0] = a;
            this.Content[1] = b;
            this.Content[2] = c;
        }
 
        public void printContent()
        {
            System.out.println(this.Content[0] + " " + this.Content[1] + " " + this.Content[2]);
        }
 
        public ArrayList<JugState> getNextStates(){
            ArrayList<JugState> successors = new ArrayList<>();
            JugState x;

            for(int i=0;i<3;i++)
            {
                if(Content[i]!=0)
                {
                    x = new JugState();
                    for(int j=0;j<3;j++)
                    {
                        if(i==j)
                            x.Content[j] = 0;
                        else
                            x.Content[j]=Content[j];
                    }
                    successors.add(x);

                }
            }
            for(int i=0;i<3;i++)
            {
                if(Content[i]!=Capacity[i])
                {
                    x = new JugState();
                    for(int j=0;j<3;j++)
                    {
                        if(i==j)
                            x.Content[j] = Capacity[j];
                        else
                            x.Content[j]=Content[j];
                    }
                    successors.add(x);

                }
            }

            for(int i=0;i<3;i++)
            {
                if(Content[i]!=Capacity[i])
                {
                    for(int j=0;j<3;j++)
                    {
                        if(i!=j && Content[j]!=0)
                        {
                            x = new JugState();
                            x.Content[i] = Capacity[i]>(Content[i]+Content[j])?(Content[i]+Content[j]):Capacity[i];
                            x.Content[j]= (Content[j]-Capacity[i]+Content[i])>0?(Content[j]-Capacity[i]+Content[i]):0;
                            x.Content[3-i-j] = Content[3-i-j];
                            successors.add(x);
                            
                        }
                    }
                }
            }

            return successors;
        }
    }

    public static void main(String[] args) {
        if( args.length != 6 )
        {
            System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
            return;
        }
        // parse command line arguments
        JugState a = new JugState();
        a.Capacity[0] = Integer.parseInt(args[0]);
        a.Capacity[1] = Integer.parseInt(args[1]);
        a.Capacity[2] = Integer.parseInt(args[2]);
        a.Content[0] = Integer.parseInt(args[3]);
        a.Content[1] = Integer.parseInt(args[4]);
        a.Content[2] = Integer.parseInt(args[5]);
        // Implement this function
        ArrayList<JugState> asist = a.getNextStates();

        // Print out generated successors
        for(int i=0;i< asist.size(); i++)
        {
            asist.get(i).printContent();
        }

        return;
    }
}

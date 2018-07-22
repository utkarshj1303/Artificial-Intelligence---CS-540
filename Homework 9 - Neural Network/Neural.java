
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Neural {
    
    public static void main(String[] args) 
    {
        int flag = Integer.parseInt(args[0]);
        if(flag == 100)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double inp1 = Double.parseDouble(args[10]);
            double inp2 = Double.parseDouble(args[11]);
            double uA = w[0]+w[1]*inp1+w[2]*inp2;
            double uB = w[3]+w[4]*inp1+w[5]*inp2;
            System.out.print(String.format("%.5f",uA)+" ");
            System.out.print(String.format("%.5f",Math.max(0,uA))+" ");
            System.out.print(String.format("%.5f",uB)+" ");
            System.out.print(String.format("%.5f",Math.max(0,uB))+" ");
            System.out.print(String.format("%.5f",w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB)+w[6])+" ");
            System.out.println(String.format("%.5f",1/(1+Math.exp(-1*(w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB)+w[6]))))+" ");

        }
        if(flag == 200)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double inp1 = Double.parseDouble(args[10]);
            double inp2 = Double.parseDouble(args[11]);
            double y = Double.parseDouble(args[12]);
            double uA = w[0]+w[1]*inp1+w[2]*inp2;
            double uB = w[3]+w[4]*inp1+w[5]*inp2;
            double uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
            double vC = 1/(1+Math.exp(-1*(uC)));
            System.out.print(String.format("%.5f",0.5*(vC-y)*(vC-y))+" ");
            System.out.print(String.format("%.5f",(vC-y))+" ");
            System.out.println(String.format("%.5f",(vC-y)*vC*(1-vC)));
        }
        if(flag==300)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double inp1 = Double.parseDouble(args[10]);
            double inp2 = Double.parseDouble(args[11]);
            double y = Double.parseDouble(args[12]);
            double uA = w[0]+w[1]*inp1+w[2]*inp2;
            double uB = w[3]+w[4]*inp1+w[5]*inp2;
            double uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
            double vC = 1/(1+Math.exp(-1*(uC)));
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[7])+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0))+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[8])+" ");
            System.out.println(String.format("%.5f",(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0))+" ");
        }
        
        if(flag==400)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double inp1 = Double.parseDouble(args[10]);
            double inp2 = Double.parseDouble(args[11]);
            double y = Double.parseDouble(args[12]);
            double uA = w[0]+w[1]*inp1+w[2]*inp2;
            double uB = w[3]+w[4]*inp1+w[5]*inp2;
            double uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
            double vC = 1/(1+Math.exp(-1*(uC)));
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0))+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp1)+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp2)+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0))+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp1)+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp2)+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC))+" ");
            System.out.print(String.format("%.5f",(vC-y)*vC*(1-vC)*Math.max(0,uA))+" ");
            System.out.println(String.format("%.5f",(vC-y)*vC*(1-vC)*Math.max(0,uB))+" ");

        }
        if(flag==500)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
                System.out.print(String.format("%.5f",w[i])+" ");
            }
            System.out.println("");
            double inp1 = Double.parseDouble(args[10]);
            double inp2 = Double.parseDouble(args[11]);
            double y = Double.parseDouble(args[12]);
            double eta = Double.parseDouble(args[13]);
            double uA = w[0]+w[1]*inp1+w[2]*inp2;
            double uB = w[3]+w[4]*inp1+w[5]*inp2;
            double uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
            double vC = 1/(1+Math.exp(-1*(uC)));
            System.out.println(String.format("%.5f",0.5*(vC-y)*(vC-y)));
            w[0]=w[0]-eta*(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0);
            w[1]=w[1]-eta*(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp1;
            w[2]=w[2]-eta*(vC-y)*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp2;
            w[3]=w[3]-eta*(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0);
            w[4]=w[4]-eta*(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp1;
            w[5]=w[5]-eta*(vC-y)*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp2;
            w[6]=w[6]-eta*(vC-y)*vC*(1-vC);
            w[7]=w[7]-eta*(vC-y)*vC*(1-vC)*Math.max(0,uA);
            w[8]=w[8]-eta*(vC-y)*vC*(1-vC)*Math.max(0,uB);
            uA = w[0]+w[1]*inp1+w[2]*inp2;
            uB = w[3]+w[4]*inp1+w[5]*inp2;
            uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
            vC = 1/(1+Math.exp(-1*(uC)));
            for(int i=0;i<9;i++)
            {
                System.out.print(String.format("%.5f",w[i])+" ");
            }
            System.out.println("\n"+String.format("%.5f",0.5*(vC-y)*(vC-y)));

        }
        
        if(flag==600)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double eta = Double.parseDouble(args[10]);
            String filename = "hw2_midterm_A_train.txt";
            ArrayList<Double> inp1t = new ArrayList<Double>();
            ArrayList<Double> inp2t = new ArrayList<Double>();
            ArrayList<Double> yt = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1t.add(i);
                    i = sc.nextDouble();
                    inp2t.add(i);
                    i = sc.nextDouble();
                    yt.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
            filename = "hw2_midterm_A_eval.txt";
            ArrayList<Double> inp1e = new ArrayList<Double>();
            ArrayList<Double> inp2e = new ArrayList<Double>();
            ArrayList<Double> ye = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1e.add(i);
                    i = sc.nextDouble();
                    inp2e.add(i);
                    i = sc.nextDouble();
                    ye.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
            for(int i=0;i<67;i++)
            {
                System.out.println(String.format("%.5f",inp1t.get(i))+" "+String.format("%.5f",inp2t.get(i))+" "+String.format("%.5f",yt.get(i)));
                double uA = w[0]+w[1]*inp1t.get(i)+w[2]*inp2t.get(i);
                double uB = w[3]+w[4]*inp1t.get(i)+w[5]*inp2t.get(i);
                double uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                double vC = 1/(1+Math.exp(-1*(uC)));
                w[0]=w[0]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0);
                w[1]=w[1]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp1t.get(i);
                w[2]=w[2]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp2t.get(i);
                w[3]=w[3]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0);
                w[4]=w[4]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp1t.get(i);
                w[5]=w[5]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp2t.get(i);
                w[6]=w[6]-eta*(vC-yt.get(i))*vC*(1-vC);
                w[7]=w[7]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uA);
                w[8]=w[8]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uB);
                double error=0;
                for(int j=0;j<9;j++)
                {
                    System.out.print(String.format("%.5f",w[j])+" ");
                }
                System.out.println("");
                for(int j=0;j<25;j++)
                {
                    uA = w[0]+w[1]*inp1e.get(j)+w[2]*inp2e.get(j);
                    uB = w[3]+w[4]*inp1e.get(j)+w[5]*inp2e.get(j);
                    uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                    vC = 1/(1+Math.exp(-1*(uC)));
                    error+=(0.5*(vC-ye.get(j))*(vC-ye.get(j)));
                }
                System.out.println(String.format("%.5f",error));

            }
        }
        if(flag==700)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double eta = Double.parseDouble(args[10]);
            int T = Integer.parseInt(args[11]);

            String filename = "hw2_midterm_A_train.txt";
            ArrayList<Double> inp1t = new ArrayList<Double>();
            ArrayList<Double> inp2t = new ArrayList<Double>();
            ArrayList<Double> yt = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1t.add(i);
                    i = sc.nextDouble();
                    inp2t.add(i);
                    i = sc.nextDouble();
                    yt.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
            filename = "hw2_midterm_A_eval.txt";
            ArrayList<Double> inp1e = new ArrayList<Double>();
            ArrayList<Double> inp2e = new ArrayList<Double>();
            ArrayList<Double> ye = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1e.add(i);
                    i = sc.nextDouble();
                    inp2e.add(i);
                    i = sc.nextDouble();
                    ye.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
                double uA;
                double uB;
                double uC;
                double vC;

            for(int k=0;k<T;k++)
            {
                
            for(int i=0;i<67;i++)
            {
                uA = w[0]+w[1]*inp1t.get(i)+w[2]*inp2t.get(i);
                uB = w[3]+w[4]*inp1t.get(i)+w[5]*inp2t.get(i);
                uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                vC = 1/(1+Math.exp(-1*(uC)));
                w[0]=w[0]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0);
                w[1]=w[1]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp1t.get(i);
                w[2]=w[2]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp2t.get(i);
                w[3]=w[3]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0);
                w[4]=w[4]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp1t.get(i);
                w[5]=w[5]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp2t.get(i);
                w[6]=w[6]-eta*(vC-yt.get(i))*vC*(1-vC);
                w[7]=w[7]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uA);
                w[8]=w[8]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uB);
            }
                double error=0;
                for(int j=0;j<9;j++)
                {
                    System.out.print(String.format("%.5f",w[j])+" ");
                }
                System.out.println("");
                for(int j=0;j<25;j++)
                {
                    uA = w[0]+w[1]*inp1e.get(j)+w[2]*inp2e.get(j);
                    uB = w[3]+w[4]*inp1e.get(j)+w[5]*inp2e.get(j);
                    uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                    vC = 1/(1+Math.exp(-1*(uC)));
                    error+=(0.5*(vC-ye.get(j))*(vC-ye.get(j)));
                }
                System.out.println(String.format("%.5f",error));


            }
        }
        if(flag==800)
        {
            double[] w = new double[9];
            for(int i=0;i<9;i++)
            {
                w[i]=Double.parseDouble(args[i+1]);
            }
            double eta = Double.parseDouble(args[10]);
            int T = Integer.parseInt(args[11]);

            String filename = "hw2_midterm_A_train.txt";
            ArrayList<Double> inp1t = new ArrayList<Double>();
            ArrayList<Double> inp2t = new ArrayList<Double>();
            ArrayList<Double> yt = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1t.add(i);
                    i = sc.nextDouble();
                    inp2t.add(i);
                    i = sc.nextDouble();
                    yt.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
            filename = "hw2_midterm_A_eval.txt";
            ArrayList<Double> inp1e = new ArrayList<Double>();
            ArrayList<Double> inp2e = new ArrayList<Double>();
            ArrayList<Double> ye = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1e.add(i);
                    i = sc.nextDouble();
                    inp2e.add(i);
                    i = sc.nextDouble();
                    ye.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }
            
            filename = "hw2_midterm_A_test.txt";
            ArrayList<Double> inp1te = new ArrayList<Double>();
            ArrayList<Double> inp2te = new ArrayList<Double>();
            ArrayList<Double> yte = new ArrayList<Double>();
            try
            {
                File f = new File(filename);
                Scanner sc = new Scanner(f);
                while(sc.hasNext())
                {
                    double i = sc.nextDouble();
                    inp1te.add(i);
                    i = sc.nextDouble();
                    inp2te.add(i);
                    i = sc.nextDouble();
                    yte.add(i);
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File Not Found.");
            }

                double uA;
                double uB;
                double uC;
                double vC;
                double error=0;
                
            double preverror = Double.MAX_VALUE;
            int k;
            for(k=0;k<T;k++)
            {
                
            for(int i=0;i<67;i++)
            {
                uA = w[0]+w[1]*inp1t.get(i)+w[2]*inp2t.get(i);
                uB = w[3]+w[4]*inp1t.get(i)+w[5]*inp2t.get(i);
                uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                vC = 1/(1+Math.exp(-1*(uC)));
                w[0]=w[0]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0);
                w[1]=w[1]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp1t.get(i);
                w[2]=w[2]-eta*(vC-yt.get(i))*vC*(1-vC)*w[7]*(uA>=0?1:0)*inp2t.get(i);
                w[3]=w[3]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0);
                w[4]=w[4]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp1t.get(i);
                w[5]=w[5]-eta*(vC-yt.get(i))*vC*(1-vC)*w[8]*(uB>=0?1:0)*inp2t.get(i);
                w[6]=w[6]-eta*(vC-yt.get(i))*vC*(1-vC);
                w[7]=w[7]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uA);
                w[8]=w[8]-eta*(vC-yt.get(i))*vC*(1-vC)*Math.max(0,uB);
            }
                error=0;
                for(int j=0;j<25;j++)
                {
                    uA = w[0]+w[1]*inp1e.get(j)+w[2]*inp2e.get(j);
                    uB = w[3]+w[4]*inp1e.get(j)+w[5]*inp2e.get(j);
                    uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                    vC = 1/(1+Math.exp(-1*(uC)));
                    error+=(0.5*(vC-ye.get(j))*(vC-ye.get(j)));
                }
                if(error>preverror)
                    break;
                preverror=error;

            }
            System.out.println(k+1);
            for(int i=0;i<9;i++)
            {
                System.out.print(String.format("%.5f",w[i])+" ");
            }
            System.out.println("");
            System.out.println(String.format("%.5f",error));
            int right=0;
            for(int j=0;j<25;j++)
            {
                uA = w[0]+w[1]*inp1te.get(j)+w[2]*inp2te.get(j);
                uB = w[3]+w[4]*inp1te.get(j)+w[5]*inp2te.get(j);
                uC = w[6]+w[7]*Math.max(0,uA)+w[8]*Math.max(0,uB);
                vC = 1/(1+Math.exp(-1*(uC)));
                right+=((vC>=0.5?1:0)==yte.get(j))?1:0;
            }
            System.out.println(String.format("%.5f",right/25.0));
            
            
        }


            



        
    }
    
}


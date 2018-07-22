import java.util.*;

public class Ice{
    
    public static void main(String[] args) 
    {
        int ice[]={118,151,121,96,110,117,132,104,125,118,125,123,110,127,131,99,126,144,136,126,91,130,62,112,99,161,78,124,119,124,128,131,113,88,75,111,97,112,101,101,91,110,100,130,111,107,105,89,126,108,97,94,83,106,98,101,108,99,88,115,102,116,115,82,110,81,96,125,104,105,124,103,106,96,107,98,65,115,91,94,101,121,105,97,105,96,82,116,114,92,98,101,104,96,109,122,114,81,85,92,114,111,95,126,105,108,117,112,113,120,65,98,91,108,113,110,105,97,105,107,88,115,123,118,99,93,96,54,111,85,107,89,87,97,93,88,99,108,94,74,119,102,47,82,53,115, 21, 89, 80,101, 95, 66,106, 97, 87,109, 57, 87,117, 91,62,65};
        int flag = Integer.valueOf(args[0]);
        if(flag==100)
        {
            for(int i=0;i<ice.length;i++)
            {
                System.out.println(1855+i+" "+ice[i]);
            }
        }
        else if(flag==200)
        {
            Long sum= 0L;
            double diffsq=0;
            for(int i=0;i<ice.length;i++)
            {
                sum+=ice[i];
            }
            double mean = sum/(double)ice.length;
            for(int i=0;i<ice.length;i++)
            {
                diffsq = diffsq + (ice[i]-mean)*(ice[i]-mean);
            }
            System.out.println(ice.length);
            System.out.println(String.format("%.2f",mean));
            System.out.println(String.format("%.2f",Math.sqrt(diffsq/(double)(ice.length-1))));
            
        }
        else if(flag==300)
        {
            double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);
            double sum=0;
            for(int i=0;i<ice.length;i++)
            {
                double z=(beta0+beta1*(1855+i)-ice[i]);
                sum+=z*z;
            }
            System.out.println(String.format("%.2f",sum/ice.length));

        }
        else if(flag==400)
        {
            double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);
            double sum=0;
            double sumbeta1=0;            
            for(int i=0;i<ice.length;i++)
            {
                sum+=(beta0+beta1*(1855+i)-ice[i]);
                sumbeta1+=((beta0+beta1*(1855+i)-ice[i])*(1855+i));
                
            }
            System.out.println(String.format("%.2f",2*(sum)/ice.length));
            System.out.println(String.format("%.2f",2*(sumbeta1)/ice.length));

        }
        else if(flag==500)
        {
            double beta0 = 0;
            double beta1 = 0;
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]); 
            double mse=0;
            double sum=0;
            double sumbeta1=0;
            for(int j=0;j<t;j++)
            {
                sum=0;
                sumbeta1=0;
                for(int i=0;i<ice.length;i++)
                {
                    sum+=(beta0+beta1*(1855+i)-ice[i]);
                    sumbeta1+=((beta0+beta1*(1855+i)-ice[i])*(1855+i));
                }
                beta0 = beta0 - eta*2*sum/ice.length;
                beta1 = beta1 - eta*2*sumbeta1/ice.length;
                mse=0;
                for(int i=0;i<ice.length;i++)
                {
                    double z=(beta0+beta1*(1855+i)-ice[i]);
                    mse+=z*z;
                }
                System.out.println((j+1)+" "+String.format("%.2f",beta0)+" "+String.format("%.2f",beta1)+" "+String.format("%.2f",(mse)/ice.length));
            }

        }
        else if(flag==600)
        {
            double sum= 1855 +((ice.length-1)/2.0);
            double sumy=0;            
            for(int i=0;i<ice.length;i++)
            {
                sumy+=ice[i];
            }
            sumy = sumy/ice.length;
            double num=0;
            double den=0;
            for(int i=0;i<ice.length;i++)
            {
                num+=(1855+i-sum)*(ice[i]-sumy);
                den+=(1855+i-sum)*(1855+i-sum);
                
            }
            double beta1 = num/den;
            double beta0 = sumy - beta1*sum;
            double mse=0;
            for(int i=0;i<ice.length;i++)
            {
                double z = (beta0+beta1*(1855+i)-ice[i]);
                mse+=(z*z);
            }
            System.out.println(String.format("%.2f",beta0)+" "+String.format("%.2f",beta1)+" "+String.format("%.2f",mse/ice.length));
        }
        else if(flag==700)
        {
            double year = Double.valueOf(args[1]);
            double sum= 1855 +((ice.length-1)/2.0);
            double sumy=0;            
            for(int i=0;i<ice.length;i++)
            {
                sumy+=ice[i];
            }
            sumy = sumy/ice.length;
            double num=0;
            double den=0;
            for(int i=0;i<ice.length;i++)
            {
                num+=(1855+i-sum)*(ice[i]-sumy);
                den+=(1855+i-sum)*(1855+i-sum);
                
            }
            double beta1 = num/den;
            double beta0 = sumy - beta1*sum;
            System.out.println(String.format("%.2f",beta0 + beta1*year));

        }
        else if(flag==800)
        {
            double beta0 = 0;
            double beta1 = 0;
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]); 
            double mse=0;
            double sum=0;
            double sumbeta1=0;
            double mean= 1855 +((ice.length-1)/2.0);
            double stddev=0;

            for(int i=0;i<ice.length;i++)
            {
                stddev = stddev + (1855+i-mean)*(1855+i-mean);
            }
            stddev = stddev/(double)(ice.length-1);
            stddev = Math.sqrt(stddev);
            
            for(int j=0;j<t;j++)
            {
                sum=0;
                sumbeta1=0;
                for(int i=0;i<ice.length;i++)
                {
                    sum+=(beta0+beta1*((1855+i-mean)/stddev)-ice[i]);
                    sumbeta1+=((beta0+beta1*((1855+i-mean)/stddev)-ice[i])*((1855+i-mean)/stddev));
                }
                beta0 = beta0 - eta*2*sum/ice.length;
                beta1 = beta1 - eta*2*sumbeta1/ice.length;
                mse=0;
                for(int i=0;i<ice.length;i++)
                {
                    double z=(beta0+beta1*((1855+i-mean)/stddev)-ice[i]);
                    mse+=z*z;
                }
                System.out.println((j+1)+" "+String.format("%.2f",beta0)+" "+String.format("%.2f",beta1)+" "+String.format("%.2f",(mse)/ice.length));
            }

            
        }
        else if(flag==900)
        {
            double beta0 = 0;
            double beta1 = 0;
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]); 
            double mse=0;
            double mean= 1855+((ice.length-1)/2.0);
            double stddev=0;
            for(int i=0;i<ice.length;i++)
            {
                stddev = stddev + (1855+i-mean)*(1855+i-mean);
            }
            stddev = stddev/(double)(ice.length-1);
            stddev = Math.sqrt(stddev);
            int r;
            Random rnd = new Random();
            for(int j=0;j<t;j++)
            {
                r = rnd.nextInt(ice.length);
                beta0 = beta0 - eta*2*(beta0+(beta1*((1855+r-mean)/stddev))-ice[r]);
                beta1 = beta1 - eta*2*((beta0+(beta1*((1855+r-mean)/stddev))-ice[r])*((1855+r-mean)/stddev));
                mse=0;
                for(int i=0;i<ice.length;i++)
                {
                    double z=(beta0+beta1*((1855+i-mean)/stddev)-ice[i]);
                    mse+=z*z;
                }
                System.out.println((j+1)+" "+String.format("%.2f",beta0)+" "+String.format("%.2f",beta1)+" "+String.format("%.2f",(mse)/ice.length));
            }
        }
    }
}

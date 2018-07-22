import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
	int flag = Integer.valueOf(args[0]);
        int[] vocab = new int[4700];
        for(int i=0;i<4700;i++)
        {
            vocab[i]=0;
        }
        for(int i=0;i<corpus.size();i++)
        {
            vocab[corpus.get(i)]++;
        }
        if(flag == 100)
        {
	    int w = Integer.valueOf(args[1]);
            System.out.println(vocab[w]);
            System.out.println(String.format("%.7f",vocab[w]/(double)corpus.size()));
        }
        else if(flag == 200)
        {
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double r = (double)n1/n2;
            double x =0;
            for(int i=0;i<4700;i++)
            {
                if(vocab[i]/(double)corpus.size()!=0)
                {
                    if((r>x && r<=x+(vocab[i]/(double)corpus.size())) || r==0)
                    {
                        System.out.println(i+"\n"+String.format("%.7f",x)+"\n"+String.format("%.7f",x+(vocab[i]/(double)corpus.size())));
                        break;
                    }
                    x = x+(vocab[i]/(double)corpus.size());
                }
            }
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            int z=0;
            for(int i=0;i<corpus.size()-1;i++)
            {
                if(corpus.get(i)==h && corpus.get(i+1)==w)
                {
                    count++;
                }
                if(corpus.get(i)==h)
                    z++;
            }
            System.out.println(count);
            System.out.println(z);
            System.out.println(String.format("%.7f",count/(double)z));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            int z=0;
            for(int i=0;i<4700;i++)
            {
                vocab[i]=0;
            }
            for(int i=0;i<corpus.size()-1;i++)
            {
                if(corpus.get(i)==h)
                {
                    vocab[corpus.get(i+1)]++;
                    z++;
                }
            }
            double r = (n1)/(double)n2;
            double x =0;
            for(int i=0;i<4700;i++)
            {
                if(vocab[i]/(double)z!=0)
                {
                    if((r>x && r<=x+(vocab[i]/(double)z)) || r==0)
                    {
                        System.out.println(i+"\n"+String.format("%.7f",x)+"\n"+String.format("%.7f",x+(vocab[i]/(double)z)));
                        break;
                    }
                    x = x+(vocab[i]/(double)z);
                }
            }
         }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int counth1h2 = 0;
            int counthw =0;
            for(int i=0;i<corpus.size()-2;i++)
            {
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2 && corpus.get(i+2)==w)
                    counthw++;
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2)
                    counth1h2++;
            }
            System.out.println(counthw);
            System.out.println(counth1h2);
            if(counth1h2 == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",counthw/(double)counth1h2));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            int counth1h2=0;
            for(int i=0;i<4700;i++)
            {
                vocab[i]=0;
            }
            for(int i=0;i<corpus.size()-2;i++)
            {
                if(corpus.get(i)==h1 && corpus.get(i+1)==h2)
                {
                    vocab[corpus.get(i+2)]++;
                    counth1h2++;
                }
            }
            if(counth1h2==0)
            {
                System.out.println("undefined");
            }
            else
            {
                double r = (n1)/(double)n2;
                double x =0;
                for(int i=0;i<4700;i++)
                {
                    if(vocab[i]/(double)counth1h2!=0)
                    {
                        if((r>x && r<=x+(vocab[i]/(double)counth1h2)) || r==0)
                        {
                            System.out.println(i+"\n"+String.format("%.7f",x)+"\n"+String.format("%.7f",x+(vocab[i]/(double)counth1h2)));
                            break;
                        }   
                        x = x+(vocab[i]/(double)counth1h2);
                    }
                }
            }

        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;
            Random rng = new Random();
            if (seed != -1) 
                rng.setSeed(seed);
            if(t == 0){
                double r = rng.nextDouble();
                double x =0;
                for(int i=0;i<4700;i++)
                {
                    if(vocab[i]/(double)corpus.size()!=0)
                    {
                        if(r==0 || (r>x && r<=x+(vocab[i]/(double)corpus.size())))
                        {
                            h1=i;
                            break;
                        }
                        x = x+(vocab[i]/(double)corpus.size());
                    }
                }
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }
                r = rng.nextDouble();
                int h = h1;
                int z=0;
                for(int i=0;i<4700;i++)
                {
                    vocab[i]=0;
                }
                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h)
                    {
                        vocab[corpus.get(i+1)]++;
                        z++;
                    }
                }
                x=0;
                for(int i=0;i<4700;i++)
                {
                    if(vocab[i]/(double)z!=0)
                    {
                        if(r==0 || (r>x && r<=x+(vocab[i]/(double)z)))
                        {
                            h2=i;
                            break;
                        }
                        x = x+(vocab[i]/(double)z);
                    }
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                double r = rng.nextDouble();
                int h = h1;
                int z=0;
                for(int i=0;i<4700;i++)
                {
                    vocab[i]=0;
                }
                for(int i=0;i<corpus.size()-1;i++)
                {
                    if(corpus.get(i)==h)
                    {
                        vocab[corpus.get(i+1)]++;
                        z++;
                    }
                }
                double x=0;
                for(int i=0;i<4700;i++)
                {
                    if(vocab[i]/(double)z!=0)
                    {
                        if(r==0 || (r>x && r<=x+(vocab[i]/(double)z)))
                        {
                            h2=i;
                            break;
                        }
                        x = x+(vocab[i]/(double)z);
                    }
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12)
            {
                double r = rng.nextDouble();
                int w  = 0;
                int counth1h2=0;
                for(int i=0;i<4700;i++)
                {
                    vocab[i]=0;
                }
                for(int i=0;i<corpus.size()-2;i++)
                {
                    if(corpus.get(i)==h1 && corpus.get(i+1)==h2)
                    {
                        vocab[corpus.get(i+2)]++;
                        counth1h2++;
                    }
                }
                if(counth1h2==0)
                {
                    int h = h2;
                    int z=0;
                    for(int i=0;i<4700;i++)
                    {
                        vocab[i]=0;
                    }
                    for(int i=0;i<corpus.size()-1;i++)
                    {
                        if(corpus.get(i)==h)
                        {
                            vocab[corpus.get(i+1)]++;
                            z++;
                        }
                    }
                    double x=0;
                    for(int i=0;i<4700;i++)
                    {
                        if(vocab[i]/(double)z!=0)
                        {
                            if(r==0 || (r>x && r<=x+(vocab[i]/(double)z)))
                            {
                                w=i;
                                break;
                            }
                            x = x+(vocab[i]/(double)z);
                        }
                    }
                }
                else
                {
                    double x =0;
                    for(int i=0;i<4700;i++)
                    {
                        if(vocab[i]/(double)counth1h2!=0)
                        {
                            if(r==0 || (r>x && r<=x+(vocab[i]/(double)counth1h2)))
                            {
                                w=i;
                                break;
                            }
                            x = x+(vocab[i]/(double)counth1h2);
                        }
                    }
                }
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
import java.util.*;
import java.lang.*;
class GFG
 {
	public static void main (String[] args)
	 {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int k=0;k<T;k++)
        {
            long N=sc.nextInt();
            long t1=1;
            long t2=1;
            
            for(int i=0;i<N-2;i++)
            {
                long next;
                next=t1+t2;
                t1=t2;
                t2=next;
                
                
            }
            
            System.out.println(t2%100);
        
	}
	 }
}
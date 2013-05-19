//correct
package vol0;

//import java.io.File;
import java.util.*;

public class Problem488 {
	public static final String [] wave = { //cached for performance, necessary (large input)
		"1",
		"1\n22\n1",
		"1\n22\n333\n22\n1",
		"1\n22\n333\n4444\n333\n22\n1",
		"1\n22\n333\n4444\n55555\n4444\n333\n22\n1",
		"1\n22\n333\n4444\n55555\n666666\n55555\n4444\n333\n22\n1",
		"1\n22\n333\n4444\n55555\n666666\n7777777\n666666\n55555\n4444\n333\n22\n1",
		"1\n22\n333\n4444\n55555\n666666\n7777777\n88888888\n7777777\n666666\n55555\n4444\n333\n22\n1",
		"1\n22\n333\n4444\n55555\n666666\n7777777\n88888888\n999999999\n88888888\n7777777\n666666\n55555\n4444\n333\n22\n1",
	};
	
	public static void main(String[] args) {// throws Exception {
		Scanner sc = new Scanner(System.in);//new File("Problem488.input"));
		int n = sc.nextInt();
		for(int i = 0; i < n; ++i) {
			int a = sc.nextInt();
			long f = sc.nextLong();
			
//			if(a < 1 || f < 1)
//				continue;
			
			if(i != 0)
				System.out.println();
			
			for(long j = 0; j < f; ++j) {
				System.out.print(wave[a-1]);
				if(j != f-1)
					System.out.println("\n");
			}
			//if not needed
			System.out.println();
		}
	}
//
//	public static void print_wave(int a)
//	{
//	    int i, j;
//	   
//	    for(i = 1; i < a; i++)
//	    {
//	        for(j = 0; j < i; j++)
//	            System.out.print(i);
//	        System.out.println();
//	    }
//	  
//	    for(j = 0; j < a; j++)
//	        System.out.print(a);
//	    System.out.println();
//	  
//	    for(i = a-1; i > 0; i--)
//	    {
//	        for(j = 0; j < i; j++)
//	            System.out.print(i);
//	        System.out.println();
//	    }
//	}
//
//	public static void main(String[] args)
//	{
//		Scanner sc = new Scanner(System.in);
//	    int i, n, a, f;
//	   
//	    n = sc.nextInt();
//	  
//	    for(n-- ; n >= 0; n--)
//	    {
//	        a = sc.nextInt();
//	        f = sc.nextInt();
//	      
//	        for(i = 0; i < f; i++)
//	        {
//	            if(i != 0)
//	                System.out.println();
//	          
//	            print_wave(a);
//	        }
//	      
//	        if(n != 0)
//	            System.out.println();
//	    }
//	}
}

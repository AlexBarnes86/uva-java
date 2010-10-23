package AOAPC.vol1.MiscMath;
//C++ code was accepted, does the same thing
//  probably some really dumb judge input on white spacing
import java.io.File;
import java.util.Scanner;

public class Problem10719 {
	private static void solve(long[] c, long k) {
		if(c.length == 1) {
			System.out.println("q(x):");
			System.out.println("r = " + c[0]);
			return;
		}
		
		long cur;
		long prev = c[0];
		System.out.print("q(x): " + prev);
		if(c.length > 2)
			System.out.print(" ");
		for(int i = 1; i < c.length-1; ++i) {
			cur = c[i];
			prev = cur + k*prev;
			System.out.print(prev);
			if(i != c.length-2) {
				System.out.print(" ");
			}
		}
		System.out.println();
		System.out.println("r = " + (c[c.length-1]+k*prev));
	}
	
	public static void main(String[] args) throws Exception {
		//Scanner sc = new Scanner(new File("Problem10719.in"));
		Scanner sc = new Scanner(System.in);
		String line = "";
		boolean first = true;
		while(sc.hasNext()) {
			 //input is kinda bs
			while((line = sc.nextLine()) != null) {
				line = line.trim();
				if(!line.equals(""))
					break;
				if(!sc.hasNext())
					return;
			}
			int k = Integer.parseInt(line);
			
			while((line = sc.nextLine()) != null) {
				line = line.trim();
				if(!line.equals(""))
					break;
				if(!sc.hasNext())
					return;
			}
			String [] tokens = line.trim().split("\\s+");
			
			if(first) {
				first = false;
			} else {
				System.out.println();
			}

			//System.out.println("Input: " + line);
			long [] coeff = new long[tokens.length];
			for(int i = 0; i < tokens.length; ++i)
				coeff[i] = Long.parseLong(tokens[i]);
			solve(coeff, k);
		}
	}
}

/*
// C++ Source Code 

#include <stdio.h>
#define MAXN 10010
int k,n,p[MAXN]={0},q[MAXN]={0};
int main()
{
    char c;
    while(scanf("%d",&k)==1)
    {
       n=0;
       while(true)
       {
          scanf("%d%c",&p[n++],&c);
          if(c=='\n') break;
       }
       q[0]=p[0];
       printf("q(x): %d",q[0]);
       for(int i=1;i<n-1;i++)
       {
          q[i]=q[i-1]*k+p[i];
          printf(" %d",q[i]);
       }
       printf("\nr = %d\n\n",q[n-2]*k+p[n-1]);
    }
    return 0;
}
*/

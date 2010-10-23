package AOAPC.vol1.MiscMath;
//Unsolved
import java.util.Scanner;

public class Problem10025 {
	private static long solve(long n) {
		n = Math.abs(n);
		
		long test = (long)(Math.sqrt((double)n*8+1)-1)/2;
		if(test*(test+1)/2 == n)
			return test;
		return test+1;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < N; ++i) {
			System.out.println(solve(Long.parseLong(sc.nextLine().trim())));
			if(i != N-1)
				System.out.println();
		}
	}
}

/*
 * so S1=n(n+1)/2 D1=S1-S
S2=(n+1)(n+2)/2 D2=S2-S
S3=(n+2)(n+3)/2 D3=S3-S
=> d1/2 < N+1
d2/2 < (2N+1)/2 < N+1
d3/2 < (3N+3)/2 <=2N+3=(N+1)+(N+2 )

a) if D1 is even => exist k between 0 and N so that D1/2 =k (we saw that D1/2 < N/2)

=> S1-S=2k
S1-2k=S
1+2+...+(k-1)+k+(k+1)+...+N-2k=S
1+2+...+(k-1)-k+(k+1)+...+N=S
=> solution is N

b) if D1 is odd
if D2 is even
=> in the same way we can prove that solution is N+1 and the sum of the terms which must be substracted is D2/2

c) id D2 is odd (D1 and D2 are odd)
D3=D1+(N+1)+(n+2)
D1 is odd
(N+1)+(N+2) is even
=> D3 is even

=> in the same way as a) that solution is N+2 and the sum of the terms that must be substracted is D3/2 ...

I hope this we'll help you . . .

Carmen
*/

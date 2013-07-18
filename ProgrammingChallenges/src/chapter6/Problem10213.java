package chapter6;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem10213 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = true;
	public static final Scanner sc = getScanner();
	
	static BigInteger binomial(final int N, final int K) {
		BigInteger ret = BigInteger.ONE;
		for (int k = 0; k < K; k++) {
			ret = ret.multiply(BigInteger.valueOf(N-k)).divide(BigInteger.valueOf(k+1));
		}
		
		return ret;
	}
	
	public static BigInteger solve(int n) {
		return BigInteger.ONE.add(binomial(n, 2).add(binomial(n, 4)));
	}
	
	public static void main(String[] args) {
		int c = sc.nextInt();
		for(int i = 0; i < c; ++i) {
			int n = sc.nextInt();
			System.out.println(solve(n));
		}
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter6/Problem10213.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
}

/**
Date: 04/19/2003 at 05:30:48
From: Doctor Anthony
Subject: Re: Regions in a circle

Regions of a Circle Cut by Chords to n Points
----------------------------------------------
n points are distributed round the circumference of a circle and each 
point is joined to every other point by a chord of the circle.  
Assuming that no three chords intersect at a point inside the circle 
we require the number of regions into which the circle is divided.

With no lines the circle has just one region. Now consider any 
collection of lines. If you draw a new line across the circle which 
does not cross any existing lines, then the effect is to increase the 
number of regions by 1. In addition, every time a new line crosses an 
existing line inside the circle the number of regions is increased by 
1 again.

So in any such arrangement

number of regions = 1 + number of lines + number of interior 
intersections

                  = 1 + C(n,2) + C(n,4)

Note that the number of lines is the number of ways 2 points can be 
chosen from n points. Also, the number of interior intersections is 
the number of quadrilaterals that can be formed from n points, since 
each quadrilateral produces just 1 intersection where the diagonals 
of the quadrilateral intersect.

Examples:

        n=4    Number of regions = 1 + C(4,2) + C(4,4) =  8
        n=5    Number of regions = 1 + C(5,2) + C(5,4) = 16
        n=6       "         "    = 1 + C(6,2) + C(6,4) = 31
        n=7       "         "    = 1 + C(7,2) + C(7,4) = 57

Note that formula 2^(n-1) starts to go wrong at n=6

- Doctor Anthony, The Math Forum
  http://mathforum.org/dr.math/   
*/
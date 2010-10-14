//correct
package AOAPC.vol0;

import java.util.*;

public class Problem457 {
	public static int [] next_generation(int[] dishes, int[] dna) {
		int[] copy = new int[dishes.length];
		int K = 0;
		for(int i = 0; i < dishes.length; ++i)
			copy[i] = dishes[i];
		for(int i = 0; i < dishes.length; ++i) {
			if(i == 0)
				K = dishes[0] + dishes[1];
			else if(i == dishes.length-1)
				K = dishes[dishes.length-1]+dishes[dishes.length-2];
			else
				K = dishes[i-1] + dishes[i] + dishes[i+1];
			copy[i] = dna[K];
		}
		return copy;
	}
	
	public static void print_dishes(int[] dishes) {
		for(int i = 0; i < dishes.length; ++i) {
			switch(dishes[i]) {
			case 0:
				System.out.print(' ');
			break;
			case 1:
				System.out.print('.');
			break;
			case 2:
				System.out.print('x');
			break;
			case 3:
				System.out.print('W');
			break;
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //blank line
		for(int i = 0; i < n; ++i) {
			if(i != 0)
				System.out.println();
			int dishes[] = new int[40];
			dishes[19] = 1;
			int dna[] = new int[10];
			for(int j = 0; j < 10; ++j)
				dna[j] = sc.nextInt();
			if(sc.hasNext())
				sc.nextLine(); //eat \n
			if(sc.hasNext())
				sc.nextLine(); //blank line
			for(int j = 0; j < 50; ++j) {
				print_dishes(dishes);
				dishes = next_generation(dishes, dna);
			}
		}
	}
}

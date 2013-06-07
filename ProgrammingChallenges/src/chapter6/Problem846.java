package chapter6;

import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.InputStreamReader;

public class Problem846 {
	public static void debug(String str) {
		//System.out.println("DEBUG: " + str);
	}
	
	public static int solve(int x, int y) {
		int diff = y - x;
		int mult = 1;
		int total = 0;
		while(true) {
			if(diff - mult*2 >= 0) {
				diff -= mult*2;
				total+=2;
				mult++;
			}
			else
				break;
		}
		while(diff != 0) {
			if(diff-mult >= 0) {
				diff -= mult;
				total++;
			}
			mult--;
		}
		return total;
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new FileReader("Problem846.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			String line = br.readLine();
			String[] tokens = line.split(" ");
			System.out.println(solve(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
			//System.out.println(i + ": " + solve(0, i));
		}
	}
}

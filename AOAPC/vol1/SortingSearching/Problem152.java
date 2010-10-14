//correct
package AOAPC.vol1.SortingSearching;

import java.util.*;

public class Problem152 {
	static class Point {
		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public double distanceTo(Point p) {
			return Math.sqrt(Math.pow(p.x-x, 2) + Math.pow(p.y-y, 2) + Math.pow(p.z -z, 2));
		}
		
		int x, y, z;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Point> points = new ArrayList<Point>();
		while(true) {
			int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
			if(x == 0 && y == 0 && z == 0)
				break;
			points.add(new Point(x, y, z));
		}
		int[] hist = new int[10];
		for(int i = 0; i < points.size(); ++i) {
			double closest = Double.POSITIVE_INFINITY;
			for(int j = 0; j < points.size(); ++j) {
				if(i == j)
					continue;
				double dist = points.get(i).distanceTo(points.get(j));
				if(dist < closest)
					closest = dist;
			}
			if(closest < 1)
				hist[0]++;
			else if(closest < 2)
				hist[1]++;
			else if(closest < 3)
				hist[2]++;
			else if(closest < 4)
				hist[3]++;
			else if(closest < 5)
				hist[4]++;
			else if(closest < 6)
				hist[5]++;
			else if(closest < 7)
				hist[6]++;
			else if(closest < 8)
				hist[7]++;
			else if(closest < 9)
				hist[8]++;
			else if(closest < 10)
				hist[9]++;
		}
		System.out.println(String.format("%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d", 
				hist[0],hist[1],hist[2],hist[3],hist[4],hist[5],hist[6],hist[7],hist[8],hist[9]));
	}
}

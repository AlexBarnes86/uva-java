package vol1.SimpleGeometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Problem10112 {
	static class Vector {
		public Vector(double a, double b, double c) {
			x = a;
			y = b;
			z = c;
		}
		
		public Vector() {
			x = y = z = 0;
		}
		
		public double dotProduct(Vector v) {
			return x*v.x + y*v.y + z*v.z;
		}
		
		public Vector crossProduct(Vector v) {
			return new Vector(y*v.z-z*v.y, z*v.x-x*v.z, x*v.y-y*v.x);
		}
		
		double x, y, z;
	}
	
	static class Point {
		public Point(int a, int b) {
			x = a;
			y = b;
		}
		public Point() {
			x = 0;
			y = 0;
		}
		public int x, y;
	}
	
	static class Triangle {
		public Point p1, p2, p3;
		
		public Triangle(Point a, Point b, Point c) {
			p1 = a;
			p2 = b;
			p3 = c;
		}
		
		public Triangle() {
			p1 = new Point();
			p2 = new Point();
			p3 = new Point();
		}
		
		/*
		   function SameSide(p1,p2, a,b)
			    cp1 = CrossProduct(b-a, p1-a)
			    cp2 = CrossProduct(b-a, p2-a)
			    if DotProduct(cp1, cp2) >= 0 then return true
			    else return false
		*/
		public static boolean sameSide(Point p1, Point p2, Point a, Point b) {
			Vector ab = new Vector(b.x-a.x, b.y-a.y, 0);
			Vector p1a = new Vector(p1.x-a.x, p1.y-a.y, 0);
			Vector p2a = new Vector(p2.x-a.x, p2.y-a.y, 0);
			
			Vector cp1 = ab.crossProduct(p1a);
			Vector cp2 = ab.crossProduct(p2a);
			
			if(cp1.dotProduct(cp2) >= 0)
				return true;
			return false;
		}
		
		/*
		function PointInTriangle(p, a,b,c)
		    if SameSide(p,a, b,c) and SameSide(p,b, a,c)
		        and SameSide(p,c, a,b) then return true
		    else return false
		 */
		public boolean contains(Point p) {
			if(sameSide(p, p1, p2, p3) && sameSide(p, p2, p1, p3) && sameSide(p, p3, p1, p2))
				return true;
			return false;
		}
		
		public int area() {
			//0.5 × [(y3 - y1)(x2 - x1) - (y2 - y1)(x3 - x1)]
			return (int)Math.abs(((p3.y-p1.y)*(p2.x-p1.x) - (p2.y - p1.y)*(p3.x - p1.x)));
		}
	}
	
	public static ArrayList<String> genSelections(ArrayList<Character> choices) {
		int n = choices.size();
		ArrayList<String> res = new ArrayList<String>();
		for(int i = 0; i < n; ++i) {
			for(int j = i+1; j < n; ++j) {
				for(int k = j+1; k < n; ++k) {
					res.add(choices.get(i) + "" + choices.get(j) + "" + choices.get(k));
				}
			}
		}
		return res;
	}
	//Test genChoices
//	ArrayList<Character> choices = new ArrayList<Character>();
//	for(char ch = 'A'; ch <= 'O'; ch++) {
//		choices.add(new Character(ch));
//	}
//	int i = 1;
//	for(String str : genChoices(choices)) {
//		System.out.println(i + ": " + str);
//		i++;
//	}
	
	public static String sort(String str) {
		ArrayList<Character> chars = new ArrayList<Character>();
		for(int i = 0; i < str.length(); ++i) {
			chars.add(new Character(str.charAt(i)));
		}
		Collections.sort(chars);
		String res = "";
		for(Character ch : chars)
			res += ch;
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol1/Problem10112.in"));
		String line;
		while((line = br.readLine()) != null) {
			int n = Integer.parseInt(line.trim());
			if(n == 0)
				return;
			
			ArrayList<Character> choices = new ArrayList<Character>();
			HashMap<Character, Point> points = new HashMap<Character, Point>();
			String set = "";
			for(int i = 0; i < n; ++i) {
				String [] tokens = br.readLine().trim().split("\\s+");
				char ch = tokens[0].charAt(0);
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				points.put(ch, new Point(x, y));
				choices.add(new Character(ch));
				set += ch;
			}
			ArrayList<String> sel = genSelections(choices);
			
			Triangle largest = new Triangle();
			String largestStr = "";
			for(String str : sel) {
				boolean containsPoints = false;
				
				Triangle cur = new Triangle();
				cur.p1 = points.get(new Character(str.charAt(0)));
				cur.p2 = points.get(new Character(str.charAt(1)));
				cur.p3 = points.get(new Character(str.charAt(2)));
				for(int i = 0; i < set.length(); ++i) {
					if(str.contains(set.charAt(i)+""))
						continue;
					if(cur.contains(points.get(new Character(set.charAt(i))))) {
						//System.out.println("DEBUG: Triangle " + str + ", contains " + set.charAt(i));
						containsPoints = true;
						break;
					}
				}
				if(containsPoints)
					continue;
				
				if(cur.area() > largest.area()) {
					largest = cur;
					largestStr = str;
				}
			}
			
			System.out.println(sort(largestStr));
			//System.out.println(largestStr);
		}
	}
}

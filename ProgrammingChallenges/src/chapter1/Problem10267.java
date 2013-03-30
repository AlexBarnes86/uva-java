package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10267 {
	public static Scanner sc = new Scanner(System.in);
	
	public static class Point {
		public int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Image {
		private static final char CLEAR_CHAR = 'O';
		char buffer[][];
		int width;
		int height;
		
		public Image(int width, int height) {
			this.width = width;
			this.height = height;
			buffer = new char[height][width];
			clear();
		}
		
		public void clear() {
			for(int i = 1; i <= width; ++i) {
				for(int j = 1; j <= height; ++j) {
					setPixel(i, j, CLEAR_CHAR);
				}
			}
		}
		
		public void setPixel(int x, int y, char color) {
			buffer[y-1][x-1] = color;
		}
		
		public char getPixel(int x, int y) {
			return buffer[y-1][x-1];
		}
		
		public void verticalBar(int x, int y1, int y2, char color) {
			for(int i = y1; i <= y2; ++i) {
				setPixel(x, i, color);
			}
		}
		
		public void horizontalBar(int x1, int x2, int y, char color) {
			for(int i = x1; i <= x2; ++i) {
				setPixel(i, y, color);
			}
		}
		
		public void rectangle(int x1, int y1, int x2, int y2, char color) {
			for(int i = x1; i <= x2; ++i) {
				for(int j = y1; j <= y2; ++j) {
					setPixel(i, j, color);
				}
			}
		}
		
		/*
		Flood-fill (node, target-color, replacement-color):
		 1. Set Q to the empty queue.
		 2. If the color of node is not equal to target-color, return.
		 3. Add node to Q.
		 4. For each element n of Q:
		 5.     If the color of n is equal to target-color:
		 6.         Set w and e equal to n.
		 7.         Move w to the west until the color of the node to the west of w no longer matches target-color.
		 8.         Move e to the east until the color of the node to the east of e no longer matches target-color.
		 9.         Set the color of nodes between w and e to replacement-color.
		10.         For each node n between w and e:
		11.             If the color of the node to the north of n is target-color, add that node to Q.
		12.             If the color of the node to the south of n is target-color, add that node to Q.
		13. Continue looping until Q is exhausted.
		14. Return.
		*/
		public void fill(int x, int y, char color) {
			char regionColor = getPixel(x, y);
			if(regionColor == color) {
				return;
			}
			List<Point> points = new ArrayList<Point>();
			points.add(new Point(x, y));
			while(!points.isEmpty()) {
				Point p = points.remove(points.size()-1);
				if(p.y < 1 || p.y > height || p.x < 1 || p.x > width || getPixel(p.x, p.y) != regionColor) {
					continue;
				}
				
				int beg = p.x;
				int end = p.x+1;
				while(beg >= 1 && getPixel(beg, p.y) == regionColor) {
					points.add(new Point(beg, p.y+1));
					points.add(new Point(beg, p.y-1));
					setPixel(beg, p.y, color);
					beg--;
				}
				while(end <= width && getPixel(end, p.y) == regionColor) {
					points.add(new Point(end, p.y+1));
					points.add(new Point(end, p.y-1));
					setPixel(end, p.y, color);
					end++;
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for(int y = 1; y <= height; ++y) {
				for(int x = 1; x <= width; ++x) {
					sb.append(getPixel(x, y));
				}
				sb.append("\n");
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		String line = null;
		Image image = null;
		while(sc.hasNext()) {
			line = sc.nextLine().trim();
			String[] tokens = line.split("\\s+");
			if(line.startsWith("I")) {
				int width = Integer.parseInt(tokens[1]);
				int height = Integer.parseInt(tokens[2]);
				image = new Image(width, height);
			}
			else if(line.startsWith("C")) {
				image.clear();
			}
			else if(line.startsWith("L")) {
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				char color = tokens[3].charAt(0);
				image.setPixel(x, y, color);
			}
			else if(line.startsWith("V")) {
				int x = Integer.parseInt(tokens[1]);
				int y1 = Integer.parseInt(tokens[2]);
				int y2 = Integer.parseInt(tokens[3]);
				if(y1 > y2) {
					int temp = y1;
					y1 = y2;
					y2 = temp;
				}
				char color = tokens[4].charAt(0);
				image.verticalBar(x, y1, y2, color);
			}
			else if(line.startsWith("H")) {
				int x1 = Integer.parseInt(tokens[1]);
				int x2 = Integer.parseInt(tokens[2]);
				if(x1 > x2) {
					int temp = x1;
					x1 = x2;
					x2 = temp;
				}
				int y = Integer.parseInt(tokens[3]);
				char color = tokens[4].charAt(0);
				image.horizontalBar(x1, x2, y, color);
			}
			else if(line.startsWith("K")) {
				int x1 = Integer.parseInt(tokens[1]);
				int y1 = Integer.parseInt(tokens[2]);
				int x2 = Integer.parseInt(tokens[3]);
				int y2 = Integer.parseInt(tokens[4]);
				char color = tokens[5].charAt(0);
				image.rectangle(x1, y1, x2, y2, color);
			}
			else if(line.startsWith("F")) {
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				char color = tokens[3].charAt(0);
				image.fill(x, y, color);
			}
			else if(line.startsWith("S")) {
				System.out.println(tokens[1]);
				System.out.print(image);
			}
			else if(line.startsWith("X")) {
				break;
			}
		}
	}
}
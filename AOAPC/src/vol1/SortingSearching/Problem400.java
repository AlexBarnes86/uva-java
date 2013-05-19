//correct
package vol1.SortingSearching;

import java.util.*;

public class Problem400 {
	public static final int MAX_COLS = 60;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int N = Integer.parseInt(sc.nextLine());
			ArrayList<String> files = new ArrayList<String>();
			int max = 0;
			int rows = 0, cols = 0;
			for(int i = 0; i < N; ++i) {
				String line = sc.nextLine();
				if(max < line.length())
					max = line.length();
				files.add(line);
			}
			Collections.sort(files);
			cols = (MAX_COLS-max)/(max+2)+1;
			rows = (int)Math.ceil(files.size()/(double)cols);
			for(int i = 0; i < MAX_COLS; ++i)
				System.out.print("-");
			System.out.println();
			for(int i = 0; i < rows; ++i) {
				for(int j = 0; j < cols-1; ++j) {
					if(i+j*rows < files.size()) {
						String format = "%-"+(max+2)+"s";
						System.out.print(String.format(format, files.get(i+j*rows)));
					}
				}
				String format = "%-"+(MAX_COLS-(max+2)*(cols-1))+"s";
				if(i+(cols-1)*rows < files.size()) {
					System.out.println(String.format(format, files.get(i+(cols-1)*rows)));
				}
				else
					System.out.println(String.format(format, " "));
			}
		}
	}
}

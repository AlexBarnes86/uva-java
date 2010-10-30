package vol1.SimpleGeometry;

//import java.io.File;
//import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem579 {
	public static void solve(int h, int m) {
		double M = m/60.0*360;
		double H = h/12.0*360+360/12.0*(m/60.0);
		if(M >= 360)
			M -= 360;
		if(H >= 360)
			H -= 360;
		double min = Math.min(H, M);
		double ans = Math.max(H, M) - min;
		if(ans >= 180)
			ans = 360 - ans;
		//System.out.printf("DEBUG: %f\n", ans);
		if(ans > 180)
			ans-=180;
		System.out.printf("%.3f\n", ans);
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new FileReader(new File("Problem579.in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			line = line.trim();
			if(line.equals("0:00"))
				return;
			String [] tokens = line.split(":");
			int h = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);
			solve(h, m);
		}
	}
}

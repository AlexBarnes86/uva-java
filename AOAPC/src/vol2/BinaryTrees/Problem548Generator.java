package vol2.BinaryTrees;

public class Problem548Generator {
	public static void main(String [] args) {
		for(int i = 1; i < 10000; ++i) {
			System.out.print(i);
			if(i != 9999)
				System.out.print(" ");
		}
		System.out.println();
		for(int i = 9999; i > 0; --i) {
			System.out.print(i);
			if(i != 1)
				System.out.print(" ");
		}
		System.out.println();
	}
}

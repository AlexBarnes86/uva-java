//correct
package vol0;

import java.io.*;

public class Problem458 {
   public static void main(String[] args) throws Exception {
	   DataInputStream dis = new DataInputStream(System.in);
	   DataOutputStream dos = new DataOutputStream(System.out);
	   
	   try {
		   while(true) {
			   byte b = dis.readByte();
			   if(b == 13 || b == 10) //not properly explained in the input description.
				   System.out.print((char)b);
			   else
				   dos.write((char)(b-7));
		   }
	   }
	   catch (EOFException e) {
		   //do nothing
	   }
   }
}
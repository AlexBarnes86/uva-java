package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10033 {
	public static final Scanner sc = new Scanner(System.in);
	
	public static class Computer {
		public static final int MEMORY_SIZE = 1000;
		
		public int [] register = new int[10];
		public int [] ram = new int[MEMORY_SIZE];
		public int pc; //program counter
		public long ct; //number of instructions executed
		
		public Computer() {
			ct = 0;
			pc = 0;
		}
		
		private int get1(int instruction) {
			return (instruction/100);
		}
		
		private int get2(int instruction) {
			return (instruction / 10) % 10;
		}
		
		private int get3(int instruction) {
			return instruction % 10;
		}
		
		public void run(List<Integer> program) {
			for(int i = 0; i < program.size() && i < MEMORY_SIZE; ++i) {
				ram[i] = program.get(i);
			}
			
			while(true) {
				ct++;
				
				int instruction = ram[pc];
//				System.out.println();
//				System.out.print("exec: " + instruction + " registers: [");
//				for(int i = 0; i < register.length; ++i) {
//					if(i != 0) {
//						System.out.print(", ");
//					}
//					System.out.print(register[i]);
//				}
//				System.out.println("]");
//				if(ct % 100 == 99) {
//					sc.nextLine();
//				}
				
				if(instruction == 100) {          // 100 means halt
//					System.out.println("Halt");
					return;
				}
				else if(get1(instruction) == 2) { // 2dn means set register d to n (between 0 and 9)
//					System.out.println("Set register " + get2(instruction) + " to " + get3(instruction));
					register[get2(instruction)] = get3(instruction);
				}
				else if(get1(instruction) == 3) { // 3dn means add n to register d
//					System.out.println("Add " + get3(instruction) + " to register " + get2(instruction));
					register[get2(instruction)] += get3(instruction);
					register[get2(instruction)] %= 1000;
				}
				else if(get1(instruction) == 4) { // 4dn means multiply register d by n
//					System.out.println("Multiply register " + get2(instruction) + " by " + get3(instruction));
					register[get2(instruction)] *= get3(instruction);
					register[get2(instruction)] %= 1000;
				}
				else if(get1(instruction) == 5) { // 5ds means set register d to the value of register s
//					System.out.println("Set register " + get2(instruction) + " to register " + get3(instruction));
					register[get2(instruction)] = register[get3(instruction)];
				}
				else if(get1(instruction) == 6) { // 6ds means add the value of register s to register d
//					System.out.println("Add register " + get3(instruction) + " to register " + get2(instruction));
					register[get2(instruction)] += register[get3(instruction)];
					register[get2(instruction)] %= 1000;
				}
				else if(get1(instruction) == 7) { // 7ds means multiply register d by the value of register s
//					System.out.println("Multiply register " + get2(instruction) + " by register " + get3(instruction));
					register[get2(instruction)] *= register[get3(instruction)];
					register[get2(instruction)] %= 1000;
				}
				else if(get1(instruction) == 8) { // 8da means set register d to the value in RAM whose address is in register a
//					System.out.println("Set register " + get2(instruction) + " to RAM at " + get3(instruction));
					register[get2(instruction)] = ram[register[get3(instruction)]];
				}
				else if(get1(instruction) == 9) { // 9sa means set the value in RAM whose address is in register a to the value of register s
//					System.out.println("Set RAM at " + register[get2(instruction)] + " to register " + get2(instruction));
					ram[register[get3(instruction)]] = register[get2(instruction)];
				}
				else if(get1(instruction) == 0) { // 0ds means goto the location in register d unless register s contains 0
					if(register[get3(instruction)] != 0) {  //contains 0 or is 0? ambiguous problem definition
//						System.out.println("Jump to register " + get2(instruction));
						pc = register[get2(instruction)];
						continue;
					}
//					else {
//						System.out.println("Register " + get2(instruction) + " contains 0, no jump");
//					}
				}
				
				pc++;
			}
		}
	}
	
	public static void main(String[] args) {
		int testCases = Integer.parseInt(sc.nextLine().trim());
		sc.nextLine();
		for(int i = 0; i < testCases; ++i) {
			String line = "";
			List<Integer> program = new ArrayList<Integer>();
			while(sc.hasNext() && !(line = sc.nextLine().trim()).isEmpty()) {
				program.add(Integer.parseInt(line));
			}
			
			if(i != 0) {
				System.out.println();
			}
			Computer cpu = new Computer();
			cpu.run(program);
			System.out.println(cpu.ct);
		}
	}
}

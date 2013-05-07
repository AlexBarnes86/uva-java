package chapter2;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Problem10044 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter2/Problem10044.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static final String ERDOS = "Erdos, P.";
	
	private static List< String > extractAuthors(String paperDesc){
		paperDesc = paperDesc.substring(0, paperDesc.indexOf(":"));
		List< String > names = new ArrayList< String >();
		int begin = 0;
		int end = paperDesc.indexOf(".,", begin);
		
		while(end != -1){
			names.add(paperDesc.substring(begin, end + 1));
			begin = end + 3;
			end = paperDesc.indexOf(".,", begin);
		}
		
		if(begin < (paperDesc.length() - 1)) {
			names.add(paperDesc.substring(begin));
		}
			 
		return names;
	}
	
	public static void main(String args[]) {
		int scenarios = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < scenarios; ++i) {
			String [] tokens = sc.nextLine().trim().split("\\s+");
			int paperCt = Integer.parseInt(tokens[0]);
			int nameCt = Integer.parseInt(tokens[1]);
			ArrayList<String> papers = new ArrayList<String>();
			ArrayList<String> names = new ArrayList<String>();
			for(int j = 0; j < paperCt; ++j) {
				papers.add(sc.nextLine());
			}
			
			names.add(ERDOS);
			for(int j = 0; j < nameCt; ++j) {
				names.add(sc.nextLine());
			}
			int desiredNamesCt = names.size();
			
			for(String paper : papers) {
				List<String> nameTokens = extractAuthors(paper);
				for(String name : nameTokens) {
					if(!names.contains(name)) {
						names.add(name);
					}
				}
			}
			
//			debug(names);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Scenario " + (i+1) + "\n");
			ArrayList<Integer> distances = solve(papers, names);
			for(int j = 1; j < desiredNamesCt; ++j) {
				if(distances.get(j) < 0) {
					sb.append(names.get(j) + " infinity\n");
				}
				else {
					sb.append(names.get(j) + " " + distances.get(j) + "\n");
				}
			}
			
			System.out.print(sb.toString());
		}
	}
	
	private static ArrayList<Integer> solve(ArrayList<String> papers, ArrayList<String> names) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for(int i = 0; i < names.size(); ++i) {
			graph.add(new LinkedList<Integer>());
		}
		
		for(String paper : papers) {
			ArrayList<Integer> namesInPaper = new ArrayList<Integer>();
			for(String curName : extractAuthors(paper)) {
				int nameIdx = names.indexOf(curName);
				namesInPaper.add(nameIdx);
			}
			
			for(Integer firstId : namesInPaper) {
				for(Integer secondId : namesInPaper) {
					if(firstId != secondId) {
						graph.get(firstId).add(secondId);
					}
				}
			}
		}
		
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for(int i = 0; i < names.size(); ++i) {
			distances.add(-1);
		}
		
		distances.set(0, 0); //ERDOS distance from himself is 0
		
		//Breadth first
		Queue<Integer> bfsQueue = new LinkedList<Integer>();
		bfsQueue.add(0);
		while(!bfsQueue.isEmpty()) {
			Integer personIdx = bfsQueue.remove();
			Iterator<Integer> itr = graph.get(personIdx).iterator();
			while(itr.hasNext()) {
				int otherIndex = itr.next();
				Integer otherDistance = distances.get(otherIndex);
				if(otherDistance.equals(-1)) {
					distances.set(otherIndex, distances.get(personIdx)+1);
					bfsQueue.add(otherIndex);
				}
			}
		}
		
		return distances;
	}
}
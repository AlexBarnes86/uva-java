package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

//assumes you wont enqueue a person already in the queue
public class Problem540 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem540.in"));
		String line = "";
		int itr = 1;
		
		while(true) {
			int t = Integer.parseInt(br.readLine());
			if(t == 0)
				break;
			System.out.println("Scenario #" + itr);
			itr++;
			
			HashMap<Integer, Integer> people = new HashMap<Integer, Integer>(); //person_id, team_id
			LinkedList<Integer> queue = new LinkedList<Integer>(); //list of teams in line
			HashMap<Integer, LinkedList<Integer>> team = new HashMap<Integer, LinkedList<Integer>>(); //team_id, list of people ids in line
			
			for(int i = 0; i < t; ++i) {
				line = br.readLine();
				String [] tokens = line.split(" ");
				int n = Integer.parseInt(tokens[0]);
				for(int j = 1; j <= n; j++) {
					people.put(new Integer(tokens[j]), i);
				}
				team.put(i, new LinkedList<Integer>());
			}
			
			while(true) {
				line = br.readLine().trim();
				String [] tokens = line.split(" ");
				if(tokens[0].equals("STOP"))
					break;
				else if(tokens[0].equals("ENQUEUE")) {
					int person_id = Integer.parseInt(tokens[1]);
					int team_id = people.get(person_id);
					if(team.get(team_id).isEmpty()) {
						queue.add(team_id);
					}
					team.get(team_id).add(person_id);
				}
				else { //if(tokens[0].equals("DEQUEUE")) {
					while(queue.size() != 0 && team.get(queue.get(0)).size() == 0) {
						queue.removeFirst();
					}
					if(queue.size() != 0) {
						System.out.println(team.get(queue.get(0)).removeFirst());
					}
				}
			}
			
			System.out.println();
		}
	}
}

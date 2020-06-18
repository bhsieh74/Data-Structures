import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class Store {
	
	//Holding an array of customers so we can re-reference back to each customer
	public int[] customers;

	public static void main(String args[]){
		
		Store store = new Store();
		//Using local file on my computer to test my code
		//CustomerQueue queue = store.readCustomers("C:\\Users\\Brian Hsieh\\eclipse-workspace\\HW 2\\src/customersfile.txt");
		//CommandLine argument
		CustomerQueue queue = store.readCustomers(args[0]);
		
		store.customers = new int[queue.currentsize];
		while (!queue.isEmpty(queue)) {
			queue.removeCustomer(store);
		}

		System.out.println("NUMBER-OF-CUSTOMERS-SERVED: " + queue.numserved);
		System.out.println("LONGEST-BREAK-LENGTH: " + queue.longestbreak);
		System.out.println("TOTAL-IDLE-TIME: " + queue.idletime);
		System.out.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME: " + queue.maxnum);
		
		//store.writeToFile(queue, "C:\\Users\\Brian Hsieh\\eclipse-workspace\\HW 2\\src/queriesfile.txt");
		//CommandLine Argument
		store.writeToFile(queue, args[1]);
		
		
	}
	
	public void writeToFile(CustomerQueue queue, String filename) {
		String input;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			FileWriter fw = new FileWriter("output.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			while ((input = br.readLine()) != null) {
				
				switch (input) {
				
				case "NUMBER-OF-CUSTOMERS-SERVED": {
					bw.write("NUMBER-OF-CUSTOMERS-SERVED: " + queue.numserved);
					bw.newLine();
					break;
				}
				case "LONGEST-BREAK-LENGTH": {
					bw.write("LONGEST-BREAK-LENGTH: "+ queue.longestbreak);
					bw.newLine();
					break;
				}
				case "TOTAL-IDLE-TIME": {
					bw.write("TOTAL-IDLE-TIME: "+ queue.idletime);
					bw.newLine();
					break;
				}
				case "MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME": {
					bw.write("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME: "+ queue.maxnum);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 1": {
					bw.write("WAITING-TIME-OF 1: " + customers[0]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 2": {
					bw.write("WAITING-TIME-OF 2: " + customers[1]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 3": {
					bw.write("WAITING-TIME-OF 3: " + customers[2]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 4": {
					bw.write("WAITING-TIME-OF 4: " + customers[3]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 5": {
					bw.write("WAITING-TIME-OF 5: " + customers[4]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 6": {
					bw.write("WAITING-TIME-OF 6: " + customers[5]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 7": {
					bw.write("WAITING-TIME-OF 7: " + customers[6]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 8": {
					bw.write("WAITING-TIME-OF 8: " + customers[7]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 9": {
					bw.write("WAITING-TIME-OF 9: " + customers[8]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 10": {
					bw.write("WAITING-TIME-OF 10: " + customers[9]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 11": {
					bw.write("WAITING-TIME-OF 11: " + customers[10]);
					bw.newLine();
					break;
				}
				case "WAITING-TIME-OF 12": {
					bw.write("WAITING-TIME-OF 12: " + customers[11]);
					bw.newLine();
					break;
				}
				}
			}
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public CustomerQueue readCustomers(String filename) {
		CustomerQueue queue = new CustomerQueue();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String input = br.readLine();
			Customer.constant = Integer.parseInt(input);
			while((input = br.readLine()) != null) {
				input = br.readLine();
				String[] temp = input.split("  ");
				int id = Integer.parseInt(temp[1]);
				input = br.readLine();
				
				String[] temp2 = input.split(" ");
				String arrivaltime = temp2[1];
				queue.addCustomer(id, arrivaltime);
			}
			
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return queue;
	}
}

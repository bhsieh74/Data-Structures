
public class Customer {
	
	//instance variables for the customer class. 
	
	public Customer next;
	public int ID;
	public int arrivaltime = 0;
	public int waittime = 0;
	public static int constant;
	
	public Customer(int ID, String arrivaltime) {
		this.ID = ID;
		//Splitting by colons to get the values of hours, minutes, and seconds. Then pass in the string array to the calculateTime method.
		String[] time = arrivaltime.split(":");
		this.arrivaltime = calculateTime(time);
	}
	
	//Converting time
	public int calculateTime(String[] time) {
		int hours = Integer.parseInt(time[0]);
		int minutes = Integer.parseInt(time[1]);
		int seconds = Integer.parseInt(time[2]);
		if (hours < 7) {
			hours = hours + 12;
		}
		int totalseconds = (hours * 3600) + (minutes * 60) + seconds;
		//If the customer arrives before 9, their waittime will be increased by 32400 seconds (9 hours) - their arrivaltime. The customer
		//must wait until 9 before they can get their service. After, their arrivaltime will be set to 32400 as if they had arrived at 9. 
		if (totalseconds < 32400) {
			waittime = 32400 - totalseconds;
			totalseconds = 32400;
		}
		
		return totalseconds;
				
	}
	
	public static void main(String[] args) {
		//Testing for a customer who arrives at 8 pm. He/she will have a waiting time of 1 hour
		Customer test = new Customer(5, "8:00:00");
		System.out.println(test.waittime);
		Customer test2 = new Customer(4, "12:00:00");
		System.out.println(test2.arrivaltime);

	}
}


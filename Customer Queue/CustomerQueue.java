

public class CustomerQueue<T> {
	
	//Instance variables for the customerqueue. We have the Customer front who is currently at the front of the line, and we have end who 
	//is the end of the line. 
	public Customer front = null;
	public Customer end = null;
	
	//the service time according to the output.txt file given to us appears to be 5 minutes. 5 * 60 is 300 so the service time it takes to
	//help each customer is 300 seconds. All other instance variables are set to 0 because the queue is just instantiated so it should be 0.
	public static int servicetime = 300;
	public int numserved = 0;
	public int currentsize = 0;
	public int maxnum = 0;
	public int idletime = 0;
	public int longestbreak = 0;
	
	//Empty constructor
	public CustomerQueue() {
	}
	
	
	//Testing the class
	public static void main(String[] args) {
		CustomerQueue queue = new CustomerQueue();
		System.out.println(queue.maxnum);
		
	}
	
	//Adding a customer: we increment currentsize by 1 and check if the end is null. If it is null, we add a customer to the front because it
	//is an empty queue. otherwise, we add the customer to the end of the queue.
	public void addCustomer(int customerid, String arrivaltime) {
		
		currentsize++;
		if(end == null) {
			front = new Customer(customerid, arrivaltime);
			end = front;
		}
		else {
			end.next = new Customer(customerid, arrivaltime);
			end = end.next;
		}
	}
	
	//Remove Customer: we check front is null, and if it is then we cannot remove a customer because there is no one in line
	public void removeCustomer(Store store) {
		if(front == null) {
			return;
		}

		int currentline = 0;
		Customer line = front.next;
		while ((line != null) && (line.arrivaltime < 61200) && (line.arrivaltime - front.arrivaltime < Customer.constant)) {
			currentline++;
			line = line.next;
		}
		//Checking the currentlength of the line and if it is greater than the previous maximum, update it.
		if (maxnum < currentline) {
			maxnum = currentline;
		}

		//Checking if the arrivaltime is past 5 pm. if it is past 5, we must turn away the customer and set their waittime to 0
		if (front.arrivaltime >= 61200) {
			front.waittime-=front.arrivaltime - 61200;
			//Customer arrives after 5 pm, so their wait time is automatically set to 0
			if (front.waittime < 0)
				front.waittime = 0;
			store.customers[front.ID - 1] = front.waittime;
			
			while(front.next != null) {
				front = front.next;
				if(front.arrivaltime <= 61200) {
					front.waittime = 61200 - front.arrivaltime;
				}
				store.customers[front.ID-1] = front.waittime;
			}
			
			front = null;
			end = null;
			currentsize = 0;
			return;
		}
		
		numserved = numserved + 1;
		
		
		Customer temp = front;
		store.customers[temp.ID-1] = temp.waittime;
		
		front = front.next;
		
		//checking if the last customer is before 5 pm. If so, we will add to the idletime
		if(front == null) {
			if (temp.arrivaltime - servicetime < 61200) {
				if(61200 - temp.arrivaltime - servicetime > longestbreak)
					longestbreak = 61200 - temp.arrivaltime - servicetime;
				idletime += 61200 - temp.arrivaltime - servicetime;
			}
		}
		else {
			//Checking if a customer arrives while there already is one on line at the front
			if(front.arrivaltime < temp.arrivaltime + servicetime) {
				front.waittime += temp.arrivaltime + servicetime - front.arrivaltime;
				front.arrivaltime = temp.arrivaltime + servicetime;
			}
			else {
				//Checking if the next customer arrives into an empty line; we must update the idletimes and seeing if it qualifies for longest break
				if(front.arrivaltime - temp.arrivaltime - servicetime > longestbreak) {
					longestbreak = front.arrivaltime - temp.arrivaltime- servicetime;
				}
				idletime += front.arrivaltime - temp.arrivaltime - servicetime;
			}
		}
		
		return;
	}
	
	//returns a boolean based on if the queue is empty
	public boolean isEmpty(CustomerQueue queue) {
		if (queue.front == null) {
			return true;
		}
		return false;
	}
}



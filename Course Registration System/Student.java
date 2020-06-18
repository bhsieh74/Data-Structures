
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface, Serializable {
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private boolean loggedIn = false;
	public ArrayList<Course> enrolledCourses = new ArrayList<Course>(3);
	
	public Student() {};
	//Constructor for student object
	public Student(String first, String last, String user, String pw) {
		this.first_name = first;
		this.last_name = last;
		this.username = user;
		this.password = pw;
	}
	//Getters and Setters
	public String getFirst() {
		return this.first_name;
	}
	
	public String getLast() {
		return this.last_name;
	}
	public String getUser() {
		return this.username;
	}
	public String getPW() {
		return this.password;
	}
	public ArrayList<Course> getCourses() {
		return this.enrolledCourses;
	}
	
	@Override
	public void logIn(String username, String password) {
		if ((username.equals(this.username)) && (password.equals(this.password))) {
			loggedIn = true;
		}
		
	}

	@Override
	public void logOut() {
		loggedIn = false;
	}
	
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	//View all by printing the school courses ArrayList
	public void viewAllCourses(School s) {
		for (int i = 0; i < s.courses.size(); i++) {
			System.out.println(s.courses.get(i).toString());
		}
	}
	
	public void viewAllAvailable(School s) {
		for (int i = 0; i < s.courses.size(); i++) {
			if (s.courses.get(i).isFull() == false) {
				System.out.println(s.courses.get(i).toString());
			}
		}
	}
	
	public void enrollCourse(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the coursename:");
		String coursename = input.nextLine();
		System.out.println("Please enter the section ID:");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer for the section ID:");
			input.next();
		}
		int sectionid = input.nextInt();
		System.out.println("Please enter your full name:");
		input.nextLine();
		String fullname = input.nextLine();
		for (int i = 0; i < s.courses.size(); i++) {
			Course temp = s.courses.get(i);
			if (temp.getName().equals(coursename) && (temp.getSectionID() == sectionid)) {
				 if (temp.isFull() == false) {
					enrolledCourses.add(temp);
					s.courses.get(i).listNames.add(fullname);
					s.courses.get(i).studentAdded();
					
				}
				else {
					System.out.println("Course is full.");
				}
	
			}
		}
	}
	
	//Withdraw checks for input and sees if the coursename and section id input matches with an existing course. if so, remove the student's name from the ListNames
	public void withdrawCourse(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the coursename:");
		String coursename = input.nextLine();
		System.out.println("Please enter the section ID:");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer for the section ID:");
			input.next();
		}
		int sectionid = input.nextInt();
		System.out.println("Please enter your full name:");
		input.nextLine();
		String fullname = input.nextLine();
		for (int i = 0; i < enrolledCourses.size(); i++) {
			Course temp = enrolledCourses.get(i);
			if (temp.getName().equals(coursename) && (temp.getSectionID() == sectionid)) {
				if (temp.getCurrNum() != 0) {
					enrolledCourses.remove(enrolledCourses.get(i));
					temp.listNames.remove(fullname);
					temp.studentRemoved();
					
				}
			}
		}
	}
	
	//Printing courses if they are in the enrolledCourse ArrayList
	public void viewStudentCourses() {
		for (int i = 0; i < enrolledCourses.size(); i++) {
			System.out.println(enrolledCourses.get(i).toString());
		}
	}
	
}

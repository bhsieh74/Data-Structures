
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Admin extends User implements AdminInterface {
	//Instance Variables
	private String username = "Admin";
	private String password = "Admin001";
	private String first_name;
	private String last_name;
	private boolean loggedIn = false;
	
	//Constructor for an Admin (Omitting username and password because there is only supposed to be 1 admin
	public Admin(String firstname, String lastname) {
		this.first_name = firstname;
		this.last_name = lastname;
	};
	
	public void logIn(String username, String password) {
		//Changing loggedIn boolean to true
		if ((username.equals(this.username)) && (password.equals(this.password))) {
			loggedIn = true;
			System.out.println("Successfully logged in.");
		}
	}
	
	public void logOut() {
		//Changing loggedIn boolean to False
		loggedIn = false;
	}

	public boolean isLoggedIn() {
		//Condition of While Loop to continuously make changes
		return this.loggedIn;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPw() {
		return this.password;
	}
	//Course Management Methods
	@Override
	public School createCourse(School s) {
		//Decided to have the methods receive input from the user, rather than the Tester
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course name:");
		String courseName = input.nextLine();
		System.out.println("Please enter the course ID:");
		String courseID = input.nextLine();
		System.out.println("Please enter the maximum students:");
		int maxNum = input.nextInt();
		System.out.println("Please enter the course instructor:");
		input.nextLine();
		String courseInstructor = input.nextLine();
		System.out.println("Please enter the section ID:");
		int sectionID = input.nextInt();
		System.out.println("Please enter the course location:");
		input.nextLine();
		String courseLoc = input.nextLine();
		
		//Adding a new course directly to the School Course ArrayList
		s.courses.add(new Course(courseName, courseID, maxNum, 0, courseInstructor, sectionID, courseLoc));
		return s;
	}

	@Override
	public School deleteCourse(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course name:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String courseName = input.nextLine();
		System.out.println("Please enter the section ID:");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer for the section ID:");
			input.next();
		}
		int sectionID = input.nextInt();
		for (int i = 0; i < s.courses.size(); i++) {
			Course temp = s.courses.get(i);
			//Checking if the coursename and sectionID corresponds to an existing Course
			if (temp.getName().equals(courseName) && (temp.getSectionID() == sectionID)) {
				s.courses.remove(i);
			}
		}
		return s;
	}

	@Override
	public School editCourse(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course ID:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String courseID = input.nextLine();
		System.out.println("Please enter the section ID:");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer for the section ID:");
			input.next();
		}
		int sectionID = input.nextInt();
		//Can only edit these three values. Changing other Course variables would most likely break the program.
		System.out.println("What would you like to edit? 1) Maximum Students, 2) Course Instructor, 3) Course Location");
		System.out.println("Press the corresponding number:");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer for the section ID:");
			input.next();
		}
		int choice = input.nextInt();
		//Editing the Maximum # of students
		if (choice == 1) {
			for (int i = 0; i < s.courses.size(); i++) {
				Course temp = s.courses.get(i);
				if (temp.getID().equals(courseID) && (temp.getSectionID() == sectionID)) {
					System.out.println("What would you like to change the maximum number of students to be?");
					while (!input.hasNextInt()) {
						System.out.println("Please enter an integer for the section ID:");
						input.next();
					}
					int newMax = input.nextInt();
					s.courses.get(i).setMaxNum(newMax);
				}
			}
		}
		//Editing the Instructor
		else if (choice == 2) {
			for (int i = 0; i < s.courses.size(); i++) {
				Course temp = s.courses.get(i);
				if (temp.getID().equals(courseID) && (temp.getSectionID() == sectionID)) {
					System.out.println("Who would you like the new instructor to be?");
					input.nextLine();
					String newInstructor = input.nextLine();
					s.courses.get(i).setCourseInstructor(newInstructor);
				}
			}
		}
		//Editing the Location
		else if (choice == 3) {
			for (int i = 0; i < s.courses.size(); i++) {
				Course temp = s.courses.get(i);
				if (temp.getID().equals(courseID) && (temp.getSectionID() == sectionID)) {
					System.out.println("Where would you like the new location to be?");
					input.nextLine();
					while (input.hasNextInt()) {
						System.out.println("Please enter a String");
						input.next();
					}
					String newLoc = input.nextLine();
					s.courses.get(i).setCourseLoc(newLoc);
				}
			}
		}
		//Checking for Invalid Inputs
		else {
			System.out.println("I'm sorry, you chose an invalid integer.");
		}
		return s;
	}

	@Override
	public void display(School s) {
		//Printing the Course that corresponds to the courseID input
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course ID:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String courseID = input.nextLine();
		for (int i = 0; i < s.courses.size(); i++) {
			Course temp = s.courses.get(i);
			if (temp.getID().equals(courseID)) {
				System.out.println(temp.toString());
			}
		}
	}

	@Override
	public School registerStudent(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first name of the student:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String firstname = input.nextLine();
		System.out.println("Please enter the last name of the student:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String lastname = input.nextLine();
		System.out.println("Please enter the username of the student:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String username = input.nextLine();
		System.out.println("Please enter the password of the student:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String password = input.nextLine();
		//Adding a student directly to the School Student ArrayList
		s.students.add(new Student(firstname, lastname, username, password));
		return s;
	}

	
	
	
	//Reports Methods
	public void viewAllCourses(School s) {
		//Calling the adminView method of a Course that will print what the admin needs to see
		for (int i = 0; i < s.courses.size(); i++) {
			s.courses.get(i).adminView();
		}
	}
	
	public void viewFull(School s) {
		//Once again calling the adminView method but only where the current # of students is equal to max #
		for (int i = 0; i < s.courses.size(); i++) {
			if (s.courses.get(i).isFull()) {
				s.courses.get(i).adminView();
			}
		}
	}

	@Override
	public void toFile(School s) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your current directory path again:");
		String filename = input.nextLine() + "FullCourses.txt";
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FullCourses.txt"), "utf-8"));
            for (int i = 0; i < s.courses.size(); i++) {
            	Course temp = s.courses.get(i);
            	if (temp.isFull()) {
            		writer.write(temp.toString());
            		//Writing a new line for .txt clarity
            		writer.write("\r\n");
            	}
            }
            writer.close();
            
        } catch (IOException e) {
            System.out.println("File not working.");
        }
		
		
		
	}

	@Override
	public void studentNames(School s) {
		//Checking for course ID and getting the list of names of the course
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course ID:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String courseID = input.nextLine();
		for (int i = 0; i < s.courses.size(); i++) {
			if (s.courses.get(i).getID().equals(courseID)) {
				s.courses.get(i).getStudents();
			}
		}
	}

	@Override
	public void studentCourses(School s) {
		//Checking for the student first and last name. If they match with a student in the student ArrayList, return his enrolledCourses
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the student's first name:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String first = input.nextLine();
		System.out.println("Please enter the student's last name:");
		while (input.hasNextInt()) {
			System.out.println("Please enter a String");
			input.next();
		}
		String last = input.nextLine();
		for (int i = 0; i < s.students.size(); i++) {
			Student temp = s.students.get(i);
			if (s.students.get(i).getFirst().equals(first) && (s.students.get(i).getLast().equals(last))) {
				for (int j = 0; j < temp.getCourses().size(); j++) {
					Course tempCourse = temp.getCourses().get(j);
					System.out.println(tempCourse.toString());
				}
			}
			else {
				System.out.println("I'm sorry, I don't think any student matches that first and last name.");
			}
		}
	}
	
	public void sort(School s) {
		//I overrided the compareTo method of a Course so that it can utilize the Collections.sort method
		Collections.sort(s.courses);
	}


}

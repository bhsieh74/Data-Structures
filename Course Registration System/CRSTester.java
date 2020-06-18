
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class CRSTester {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		School school = new School();
		Admin a = new Admin("Administrator", "Admin");
		Student s = new Student();
		//Getting their directory so I can open the csv file
		System.out.println("Please navigate to your current directory path:");
		System.out.println("For Example: C:/Users/Brian Hsieh/eclipse-workspace/Data Structures/src");
		String filename = input.nextLine();
		System.out.println("Is this your first time running this program? 'Y' or 'N'");
		String choice = input.nextLine();
		
		//If it is the first time running, we will load in the .csv file manually
		if (choice.equals("Y")) {
			//System.out.println("Which directory are you currently located in?");
			//String file1 = input.nextLine() + "/MyUniversityCourses.csv";
			
			String newline = "";
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(filename + "/MyUniversityCourses.csv"));
				br.readLine();
				
	            while ((newline = br.readLine()) != null) {

	                String[] courseParameters = newline.split(",");
	                
	                //I am reading the .csv file as Strings, but some parameters for courses are integers so I must convert them into Integers
	                Integer maxNum = Integer.parseInt(courseParameters[2]);
	                Integer currNum = 0;
	                Integer sectionID = Integer.parseInt(courseParameters[6]);
	                
	                //Adding courses into the entire School database of Courses
	                school.courses.add(new Course(courseParameters[0], courseParameters[1], maxNum, currNum, courseParameters[5], sectionID, courseParameters[7]));
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		//If it is not the first time running, we will deserialize the School object
		else {
			school = null;
			try {
				//Deserialization
				FileInputStream file = new FileInputStream(filename + "/hello.ser");
				ObjectInputStream in = new ObjectInputStream(file);
				
				school = (School)in.readObject();
				
				
				in.close();
				file.close();
			}
			
			catch (IOException e) {
				System.out.println("s");
			}
			catch (ClassNotFoundException e) {
				System.out.println("Exception Thrown");
			}
		}
		
		
		//Checking for Admin vs student
		System.out.println("Are you an admin or a student? Type '1' for admin or '2' for student");
		while (!input.hasNextInt()) {
			System.out.println("Please enter 1 or 2");
			input.next();
		}
		int user = input.nextInt();
		if (user == 1) {
			System.out.println("Please enter your username:");
			input.nextLine();
			String username = input.nextLine();
			if (username.equals(a.getUsername())) {
				System.out.println("Please enter your password:");
				String password = input.nextLine();
				if (password.equals(a.getPw())) {
					a.logIn(username, password);
				}
				else {
					System.out.println("Password is incorrect. Please restart the program to try again.");
				}
			}
			
			//Printing out the menu
			while (a.isLoggedIn()) {
				System.out.println("What would like to do? Type the number that corresponds:");
				System.out.println("\t1) Create a course");
				System.out.println("\t2) Delete a course");
				System.out.println("\t3) Edit a course");
				System.out.println("\t4) Display information for a course");
				System.out.println("\t5) Register a student");
				System.out.println("\t6) View all courses");
				System.out.println("\t7) View all full courses");
				System.out.println("\t8) Write to a file a list of courses that are full");
				System.out.println("\t9) View the names of the students that are registered in a given course");
				System.out.println("\t10) View the list of courses that a given student is registered in");
				System.out.println("\t11) Sort courses");
				System.out.println("\t12) Exit");
				while (!input.hasNextInt()) {
					System.out.println("Please enter an integer:");
					input.next();
				}
				int decision = input.nextInt();
				
				if (decision == 1) {
					school = a.createCourse(school);
					System.out.println("Course created. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				
				else if (decision == 2) { 
					school = a.deleteCourse(school);
					System.out.println("Course deleted. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				
				else if (decision == 3) {
					school = a.editCourse(school);
					System.out.println("Course edited. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				else if (decision == 4) {
					a.display(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				else if (decision == 5) {
					school = a.registerStudent(school);
					System.out.println("Student registered. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				else if (decision == 6) {
					a.viewAllCourses(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				else if (decision == 7) {
					a.viewFull(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				else if (decision == 8) {
					a.toFile(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				
				else if (decision == 9) {
					a.studentNames(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				
				else if (decision == 10) {
					a.studentCourses(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
	
				else if (decision == 11) {
					a.sort(school);
					System.out.println("Courses sorted. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						a.logOut();
					}
				}
				
				else if (decision == 12) {
					System.out.println("You have been logged out.");
					a.logOut();
				}
			}
		}
		//Checking for student info
		else if (user == 2) {
			if (school.students.isEmpty()) {
				System.out.println("There are no students registered. Please log in as an Admin to create a student.");
			}
			else {
				System.out.println("Please enter your username:");
				input.nextLine();
				String username = input.nextLine();
				for (int i = 0; i < school.students.size(); i++) {
					Student temp = school.students.get(i);
					if (username.equals(temp.getUser())) {
						System.out.println("Please enter your password:");
						String password = input.nextLine();
						if (password.equals(temp.getPW())) {
							s = school.students.get(i);
							s.logIn(username, password);
						}
						else {
							System.out.println("Password is incorrect. Please restart the program to try again.");
						}
					}
					else {
						System.out.println("Username is incorrect. Please restart the program to try again.");
					}
				}
			}
				
			
			while (s.isLoggedIn()) {
				System.out.println("What would like to do? Type the number that corresponds:");
				System.out.println("\t1) View all courses");
				System.out.println("\t2) View all available courses");
				System.out.println("\t3) Register in a course");
				System.out.println("\t4) Withdraw from a course");
				System.out.println("\t5) View all courses you are registered in");
				System.out.println("\t6) Exit");
				int decision = input.nextInt();

				if (decision == 1) {
					s.viewAllAvailable(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						s.logOut();
					}
				}
				
				else if (decision == 2) { 
					s.viewAllAvailable(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						s.logOut();
					}
				}
				
				else if (decision == 3) {
					s.enrollCourse(school);
					System.out.println("Course registered. Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						s.logOut();
					}
				}
				else if (decision == 4) {
					s.withdrawCourse(school);
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						s.logOut();
					}
				}
				else if (decision == 5) {
					s.viewStudentCourses();
					System.out.println("Would you like to exit the program or return to the menu? Type 1 to exit or any other integer to return to the menu:");
					int eor = input.nextInt();
					if (eor == 1) {
						System.out.println("You have been logged out.");
						s.logOut();
					}
				}
				else if (decision == 6) {
					System.out.println("You have been logged out.");
					s.logOut();
					}
			}
		}
			
		else {
			System.out.println("I'm sorry, that is an invalid input. Please restart the program.");
		}
		//Serialization of the School object
			
		try {
			FileOutputStream fos = new FileOutputStream(filename + "/hello.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(school);
			
			
			oos.close();
			fos.close();
		}
		
		catch (IOException e) {
			System.out.println("Exception Thrown");
		}
		
		school = null;
		
		
	}
	

}

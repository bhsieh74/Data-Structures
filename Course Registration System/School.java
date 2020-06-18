

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Creating a School class to hold the ArrayList of Students and Courses
public class School implements Serializable {
	
	public ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<Course> courses = new ArrayList<Course>();
	
	public School() {
		
	}
	public ArrayList<Course> getCourses() { 
		return courses;
	}
	


}



import java.util.ArrayList;

public interface StudentInterface {
	
	public ArrayList<Course> enrolledCourses = new ArrayList<Course>(3);

	public void viewAllCourses(School s);
	
	public void viewAllAvailable(School s);
	
	public void enrollCourse(School s);
	
	public void withdrawCourse(School s);
	
	public void viewStudentCourses();
}

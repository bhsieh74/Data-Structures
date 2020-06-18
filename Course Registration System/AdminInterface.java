

public interface AdminInterface {
	
	//Course Management Methods
	public School createCourse(School s);

	public School deleteCourse(School s);
	
	public School editCourse(School s);
	
	public void display(School s);
	
	public School registerStudent(School s);


	
	//Report Methods
	public void viewAllCourses(School s);
	
	public void viewFull(School s);
	
	public void toFile(School s);
	
	public void studentNames(School s);
	
	public void studentCourses(School s);
	
	
		
}
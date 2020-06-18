
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Comparable<Course>, Serializable {
	private String courseName;
	private String id;
	private int maxNum;
	private int currNum;
	private String courseInstructor;
	private int sectionID;
	private String courseLoc;
	public ArrayList<String> listNames = new ArrayList<String>();
	
	
	public Course (String courseName, String id, int maxNum, int currNum, String courseInstructor, int sectionID, String courseLoc) {
		//Constructor for a Course
		this.courseName = courseName;
		this.id = id;
		this.maxNum = maxNum;
		this.currNum = currNum;
		this.courseInstructor = courseInstructor;
		this.sectionID = sectionID;
		this.courseLoc = courseLoc;
	}
	
	public String getName() {
		return courseName;
	}
	
	public String getID() {
		return id;
	}
	
	public String getCourseInstructor() {
		return courseInstructor;
	}
	
	public int getSectionID() {
		return sectionID;
	}
	
	public String getCourseLoc() {
		return courseLoc;
	}
	
	public int getCurrNum() {
		return currNum;
	}
	
	public int getMaxNum() {
		return maxNum;
	}
	
	public void studentAdded() {
		//Incrementing current students by 1
		currNum++;
	}
	
	public void studentRemoved() {
		//Decreasing current students by 1
		currNum--;
	}
	
	public void getStudents() {
		System.out.print("Students Enrolled: ");
		for (int i = 0; i < listNames.size(); i++) {
			System.out.print(listNames.get(i) + "  ");
		}
	}
	
	public void adminView() {
		//Specifically for the Admin method 
		System.out.println(this.toString());
		System.out.print("Students Enrolled: ");
		for (int i = 0; i < listNames.size(); i++) {
			System.out.print(listNames.get(i) + "  ");
		}
		System.out.println("Current Students: " + currNum);
		System.out.println("Max Students: " + maxNum);
		System.out.println();

		
	}
	
	public String toString() {
		//Overring the toString method so that it only returns the ID, coursename, and Section
		return (id + ": " + courseName + "\tSection: " + sectionID + "\tCourse Instructor: " + this.getCourseInstructor() + "\tCourse Location: " + courseLoc);
	}
	
	//Getters and Setters
	public boolean isFull() {
		return currNum == maxNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public void setCurrNum(int currNum) {
		this.currNum = currNum;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}

	@Override
	public int compareTo(Course o) {
		//Sorting by current number of students
		if (this.getCurrNum() > (o.getCurrNum())) {
			return 1;
		}
		else if (this.getCurrNum() < (o.getCurrNum())) {
			return -1;
		}
		else {
			//if current # of students is equal, sort by max # of students
			if (this.getMaxNum() > (o.getMaxNum())) {
				return 1;
			}
			else if (this.getMaxNum() < (o.getMaxNum())) {
				return -1;
			}
			return 0;
		}
	}
}

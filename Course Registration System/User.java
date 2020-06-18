

//User abstract class. Admin and Student extend this class
public abstract class User {

	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private boolean loggedIn;
	
	public abstract void logIn(String username, String password);
	
	public abstract void logOut();
	
	public abstract boolean isLoggedIn();
}

package users;

public enum UserType {
	ADMIN(1), CUSTOMER(2), EMPLOYEE(3);
	
	public int index;
	
	UserType(int i) { index = i; }
} // UserType

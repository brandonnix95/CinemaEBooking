package booking;

public enum TicketType {
	CHILD(1, "Child"), ADULT(2, "Adult"), SENIOR(3, "Senior");
	
	public int index;
	public String name;
	
	public static TicketType fromString(String str) {
		for(TicketType type: TicketType.values()) if(type.name.equals(str)) return type;
		return ADULT;
	} // fromString
	
	TicketType(int i, String n) {
		index = i;
		name = n;
	} // constructor
} // TicketType

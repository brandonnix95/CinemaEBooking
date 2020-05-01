package movies;

public enum Category {
	ALL("", 0), ACTION("Action", 1), SCIFI("Sci-Fi", 2), HORROR("Horror", 3), COMEDY("Comedy", 4), DRAMA("Drama", 5);
	
	public String name;
	int index;
	
	public static Category fromNum(int i) {
		switch(i) {
			case 1: return ACTION;
			case 2: return SCIFI;
			case 3: return HORROR;
			case 4: return COMEDY;
			case 5: return DRAMA;
			default: return ALL;
		} // switch
	} // fromNum
	
	public static Category fromName(String n) {
		if(n == null) return ALL;
		switch(n) {
			case "Action": return ACTION;
			case "Sci-Fi": return SCIFI;
			case "Horror": return HORROR;
			case "Comedy": return COMEDY;
			case "Drama": return DRAMA;
			default: return ALL;
		} // switch
	} // fromName
	
	Category(String n, int i) {
		name = n;
		index = i;
	} // constructor
} // Category

package movies;

public enum Rating {
	G("G", 1), PG("PG", 2), PG13("PG-13", 3), R("R", 4), NC17("NC-17", 5), NR("Not Rated", 6), TBR("Not Yet Rated", 7);
	
	public String name;
	int index;
	
	public static Rating fromNum(int i) {
		switch(i) {
			case 1: return G;
			case 2: return PG;
			case 3: return PG13;
			case 4: return R;
			case 5: return NC17;
			case 6: return NR;
			case 7: return TBR;
			default: return TBR;
		} // switch
	} // fromNum
	
	Rating(String s, int i) {
		name = s;
		index = i;
	} // constructor
} // Rating

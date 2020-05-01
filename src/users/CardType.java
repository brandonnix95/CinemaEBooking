package users;

public enum CardType {
	NONE(-1, "None", '0'),
	VISA(1, "Visa", '4'), 
	MASTERCARD(2, "MasterCard", '5'), 
	AMEX(3, "American Express", '3'), 
	DISCOVER(4, "Discover", '6');
	
	int id; 
	char startNum;
	String name;
	
	public static CardType numToType(String num) {
		char first = '0';
		if(num.length() > 0) first = num.charAt(0);
		
		for(CardType card: CardType.values()) {
			if(card.startNum == first) {
				return card;
			} // if
		} // for
		
		return NONE;
	} // numToType
	
	CardType(int id, String name, char startNum) {
		this.id = id;
		this.name = name;
		this.startNum = startNum;
	} // constructor
} // CardType

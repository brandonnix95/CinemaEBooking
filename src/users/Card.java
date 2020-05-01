package users;

import java.sql.Date;

public class Card {
	private String name, number, zip;
	private String cvv;
	private Date exp;
	private CardType type;
	
	public Card(String name, String number, String cvv, Date exp, String zip) {
		this.name = name;
		this.number = number;
		this.cvv = cvv;
		this.exp = exp;
		this.zip = zip;
		type = CardType.numToType(number);
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getCvv() {
		return cvv;
	}

	public Date getExp() {
		return exp;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Card) {
			Card card = (Card) obj;
			if(card.getNumber().equals(number)) return true;
		}
		
		return false;
	}
} // Card
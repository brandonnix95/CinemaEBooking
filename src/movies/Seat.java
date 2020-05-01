package movies;

public class Seat {
	private int seatNumber;
	private boolean availible;
	
	public Seat(int number, boolean a) {
		seatNumber = number;
		availible = a;
	} // constructor

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isAvailible() {
		return availible;
	}

	public void setAvailible(boolean availible) {
		this.availible = availible;
	}
}

package users;

public class Address {
	private String address, suite, city, state, zip;
	
	public Address(String address, String suite, String city, String state, String zip) {
		this.address = address;
		this.suite = suite;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getSuite() {
		return suite;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setSuite(String suite) {
		this.suite = suite;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
}

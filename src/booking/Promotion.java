package booking;

import java.sql.Date;

public class Promotion {
	private String code;
	private Date exp;
	private double percent;
	private int id;
	
	public Promotion(String code, Date exp, double percent, int id) {
		this.code = code;
		this.exp = exp;
		this.percent = percent;
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public int getId() {
		return id;
	}
	
}

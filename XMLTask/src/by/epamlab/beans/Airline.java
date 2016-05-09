package by.epamlab.beans;

import java.sql.Date;

public class Airline {
	
	private String id;
	private Date date;
	private String gates;
	private int aircraftNumber;
	private String type;
	
	public Airline() {
		this("", null, "", 0, "");
	}
	
	public Airline(String id, Date date, String gates, int aircraftNumber, String type) {
		this.id = id;
		this.date = date;
		this.gates = gates;
		this.aircraftNumber = aircraftNumber;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGates() {
		return gates;
	}

	public void setGates(String gates) {
		this.gates = gates;
	}

	public int getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(int aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return id + ";" + date + ";" + gates + ";" + aircraftNumber + ";" + type;
	}

}

package com.ksh.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MonthWiseBirthDateCount {

	private int count;
	@JsonIgnore
	private int month;
	private String monthValue;
	
	public String getMonthValue() {
		if(this.month == 1) return "January";
		else if(this.month == 2) return "Febuary";
		else if(this.month == 3) return "March";
		else if(this.month == 4) return "April";
		else if(this.month == 5) return "May";
		else if(this.month == 6) return "June";
		else if(this.month == 7) return "July";
		else if(this.month == 8) return "August";
		else if(this.month == 9) return "Septmber";
		else if(this.month == 10) return "October";
		else if(this.month == 11) return "November";
		else if(this.month == 12) return "December";
		else return null;
	}
//	public void setMonthValue(String monthValue) {
//		this.monthValue = monthValue;
//	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
}

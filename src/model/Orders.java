package model;

import java.time.LocalDate;

public class Orders {
	private LocalDate date;
	private String countOfVarpulu;
	private String sapuriKgs;
	private String dupinKgs;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getCountOfVarpulu() {
		return countOfVarpulu;
	}
	public void setCountOfVarpulu(String countOfVarpulu) {
		this.countOfVarpulu = countOfVarpulu;
	}
	public String getSapuriKgs() {
		return sapuriKgs;
	}
	public void setSapuriKgs(String sapuriKgs) {
		this.sapuriKgs = sapuriKgs;
	}
	public String getDupinKgs() {
		return dupinKgs;
	}
	public void setDupinKgs(String dupinKgs) {
		this.dupinKgs = dupinKgs;
	}
	public Orders(LocalDate date,String countOfVarpulu,String sapuriKgs,String dupinKgs) {
		this.date=date;
		this.countOfVarpulu = countOfVarpulu;
		this.sapuriKgs = sapuriKgs;
		this.dupinKgs = dupinKgs; 
	}
}

package com.brs.projectx.stockparser.model;

public class StockData {

	String name;
	float prices;
	float change;
	float percentageChange;
	float marketCap;
	float averageVolume;
	float volume;
	String WeekRange;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrices() {
		return prices;
	}
	public void setPrices(float prices) {
		this.prices = prices;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public float getPercentageChange() {
		return percentageChange;
	}
	public void setPercentageChange(float percentageChange) {
		this.percentageChange = percentageChange;
	}
	public float getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(float marketCap) {
		this.marketCap = marketCap;
	}
	public float getAverageVolume() {
		return averageVolume;
	}
	public void setAverageVolume(float averageVolume) {
		this.averageVolume = averageVolume;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public String getWeekRange() {
		return WeekRange;
	}
	public void setWeekRange(String weekRange) {
		WeekRange = weekRange;
	}
	@Override
	public String toString() {
		return "StockData [name=" + name + ", prices=" + prices + ", change=" + change + ", percentageChange="
				+ percentageChange + ", marketCap=" + marketCap + ", averageVolume=" + averageVolume + ", volume="
				+ volume + ", WeekRange=" + WeekRange + "]";
	}
	
	
}

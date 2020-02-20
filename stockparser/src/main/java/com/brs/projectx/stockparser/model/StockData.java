package com.brs.projectx.stockparser.model;


public class StockData {

	String name;
	String prices;
	String change;
	String percentageChange;
	String marketCap;
	String averageVolume;
	String volume;
	String weekRange;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrices() {
		return prices;
	}
	public void setPrices(String fields) {
		this.prices = fields;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getPercentageChange() {
		return percentageChange;
	}
	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}
	public String getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	public String getAverageVolume() {
		return averageVolume;
	}
	public void setAverageVolume(String averageVolume) {
		this.averageVolume = averageVolume;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getWeekRange() {
		return weekRange;
	}
	public void setWeekRange(String weekRange) {
		this.weekRange = weekRange;
	}
	@Override
	public String toString() {
		return "StockData [name=" + name + ", prices=" + prices + ", change=" + change + ", percentageChange="
				+ percentageChange + ", marketCap=" + marketCap + ", averageVolume=" + averageVolume + ", volume="
				+ volume + ", WeekRange=" + weekRange + "]";
	}
	
	
}

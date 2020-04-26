package com.projectx.springRedis.model;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


@RedisHash("Stock")
public class StockModel implements Serializable {


    private String name;
    private String prices;
    private String change;
    @Indexed
    private String percentageChange;
    private String percentageChangeDirection;
    private String marketCap;
    private String averageVolume;
    private String volume;
    private String weekRange;

    public StockModel() {
    }

    public StockModel(String name, String prices, String change,
                      String percentageChange, String marketCap,
                      String averageVolume, String volume, String weekRange) {
        this.name = name;
        this.prices = prices;
        this.change = change;
        this.percentageChange = percentageChange;
        this.marketCap = marketCap;
        this.averageVolume = averageVolume;
        this.volume = volume;
        this.weekRange = weekRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getChange() {
        return change;
    }

    public String getPercentageChangeDirection() {
        return percentageChangeDirection;
    }

    public void setPercentageChangeDirection(String percentageChangeDirection) {
        this.percentageChangeDirection = percentageChangeDirection;
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
        return "StockModel{" +
                "name='" + name + '\'' +
                ", prices='" + prices + '\'' +
                ", change='" + change + '\'' +
                ", percentageChange='" + percentageChange + '\'' +
                ", percentageChangeDirection='" + percentageChangeDirection + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", averageVolume='" + averageVolume + '\'' +
                ", volume='" + volume + '\'' +
                ", weekRange='" + weekRange + '\'' +
                '}';
    }
}

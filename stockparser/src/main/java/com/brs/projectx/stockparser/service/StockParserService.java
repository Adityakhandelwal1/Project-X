package com.brs.projectx.stockparser.service;

import org.springframework.stereotype.Service;

import com.brs.projectx.stockparser.model.StockData;

@Service
public class StockParserService {

	public StockData convertStringTOModel(String str) {

		StockData stockData = new StockData();
		if (str.length() > 1) {
			str = str.replace("StockData", "");
			str = str.substring(1, str.length() - 1);
			String[] keyValuePairs = str.split(",");
			String name = keyValuePairs[0].split("=")[1].trim();
			String prices = keyValuePairs[1].split("=")[1].trim();
			String percentageChange = keyValuePairs[3].split("=")[1].trim();
			String marketCap = keyValuePairs[4].split("=")[1].trim();
			String avgVolume = keyValuePairs[5].split("=")[1].trim();
			String volume = keyValuePairs[6].split("=")[1].trim();
			String weekRange = keyValuePairs[7].split("=")[1].trim();
			String change = keyValuePairs[2].split("=")[1].trim();

			stockData.setName(name);
			stockData.setPrices(prices);
			stockData.setChange(change);
			stockData.setPercentageChange(percentageChange);
			stockData.setMarketCap(marketCap);
			stockData.setAverageVolume(avgVolume);
			stockData.setVolume(volume);
			stockData.setWeekRange(weekRange);
		}

		return stockData;

	}

}

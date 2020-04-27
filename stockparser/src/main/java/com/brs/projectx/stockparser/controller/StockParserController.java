package com.brs.projectx.stockparser.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brs.projectx.stockparser.model.StockData;
import com.brs.projectx.stockparser.service.StockParserService;

import redis.clients.jedis.Jedis;

@RestController
public class StockParserController {
	
	@Autowired
	StockParserService stockParserService;

	private Jedis jedis = new Jedis("localhost");;

	@RequestMapping(value = "/displayNames")
	public Set<String> displayAllData() {
		return jedis.keys("*");
	}

	@RequestMapping(value = "/displayData")
	public List<StockData> displayData() {
		Set<String> set = jedis.keys("*");
		List<StockData> listStockData = new ArrayList<>();
 		for (String temp : set) {
 			listStockData.add(stockParserService.convertStringTOModel(jedis.get(temp)));
		}
		return listStockData;
	}
	
	@RequestMapping(value = "/getStocksHitting52Low")
	public List<StockData> getStocksHitting52Low() {
		List<StockData> listStockData = displayData();
		List<StockData> listStockData52Low = new ArrayList<>();
		for(StockData stockData: listStockData) {
			String[] s = stockData.getWeekRange().split(" - ");
			System.out.println(stockData.getName());
			Float lowPrice = Float.parseFloat(s[0].trim());
			Float currPrice = Float.parseFloat(stockData.getPrices().trim());
			if(Math.ceil(currPrice) == Math.ceil(lowPrice)) {
				listStockData52Low.add(stockData);
			}
		}
		return listStockData52Low;
	}
	
	@RequestMapping(value = "/getStocksHitting52High")
	public List<StockData> getStocksHitting52High() {
		List<StockData> listStockData = displayData();
		List<StockData> listStockData52High = new ArrayList<>();
		for(StockData stockData: listStockData) {
			System.out.println(stockData.getName());
			String[] s = stockData.getWeekRange().split(" - ");
			Float highPrice = Float.parseFloat(s[1].trim());
			Float currPrice = Float.parseFloat(stockData.getPrices().trim());
			if(Math.ceil(currPrice) == Math.ceil(highPrice)) {
				listStockData52High.add(stockData);
			}
		}
		return listStockData52High;
	}
	
	@RequestMapping(value = "/displayFivePercentHigh")
	public List<StockData> displayFivePercentHigh() {
		List<StockData> listStockData = displayData();
		List<StockData> listStockDataFivePercentHigh = new ArrayList<>();
		for(StockData stockData: listStockData) {
			String s = stockData.getPercentageChange();
			if(s == "N/")
				s="0.00%";
			if(Math.abs(Float.parseFloat(s.substring(0,s.length()-1))) > 5  ) {
				listStockDataFivePercentHigh.add(stockData);
			}
		}
		return listStockDataFivePercentHigh;
	}
	
	@RequestMapping(value = "/displayFivePercentLow")
	public List<StockData> displayFivePercentLow() {
		List<StockData> listStockData = displayData();
		List<StockData> listStockDataFivePercentHigh = new ArrayList<>();
		for(StockData stockData: listStockData) {
			System.out.println(stockData);
			String s = stockData.getPercentageChange();
			if(s == "N/")
				s="0.00%";
			if(Math.abs(Float.parseFloat(s.substring(0,s.length()-1))) <= 5  ) {
				listStockDataFivePercentHigh.add(stockData);
			}
		}
		return listStockDataFivePercentHigh;
	}

	@RequestMapping("/addData")
	public List<StockData> readCSV() throws IOException {

		List<StockData> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("src/main/resources/output.csv"));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] fields = line.split(",");
			StockData stockData = new StockData();
			stockData.setName(fields[0]);
			stockData.setPrices(fields[1]);
			stockData.setChange(fields[2]);
			stockData.setPercentageChange(fields[3]);
			stockData.setMarketCap(fields[4]);
			stockData.setAverageVolume(fields[5]);
			stockData.setVolume(fields[6]);
			stockData.setWeekRange(fields[7]);
			jedis.set(stockData.getName(), stockData.toString());
			list.add(stockData);
		}
		br.close();
		return list;
	}

}

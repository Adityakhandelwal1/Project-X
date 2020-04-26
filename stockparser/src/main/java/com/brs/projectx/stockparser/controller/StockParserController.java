package com.brs.projectx.stockparser.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brs.projectx.stockparser.model.StockData;

import redis.clients.jedis.Jedis;

@RestController
public class StockParserController {

	private Jedis jedis = new Jedis("localhost");;

	@RequestMapping(value = "/displayNames")
	public Set<String> displayAllData() {
		return jedis.keys("*");
	}

	@RequestMapping(value = "/displayData")
	public List<String> displayData() {
		Set<String> set = jedis.keys("*");
		List<String> listOfValues = new ArrayList<>();
 		for (String temp : set) {
 			listOfValues.add(jedis.get(temp));
		}
		return listOfValues;
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

package com.brs.projectx.stockparser.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brs.projectx.stockparser.model.StockData;

@RestController
public class StockParserController {
	private static final String REDIS_INDEX_KEY = "STOCKDATA";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping(value ="/Stocks", method = RequestMethod.POST)
	public String createData(@RequestBody StockData stockData) {
		redisTemplate.opsForHash().putIfAbsent(REDIS_INDEX_KEY, stockData.getName(), stockData.toString());
		return "DATA SAVED";
	}
	@RequestMapping(value ="/Stocks", method = RequestMethod.GET)
	public ResponseEntity<Object> getData(){
		return new ResponseEntity<>(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY),HttpStatus.OK);
	}
	
	@RequestMapping("/addData")
	public List<StockData> readCSV() throws IOException{

		List<StockData> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("//Users//adityakhandelwal//Desktop//StockData.csv"));
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
			redisTemplate.opsForHash().putIfAbsent(REDIS_INDEX_KEY, stockData.getName(), stockData.toString());
			list.add(stockData);
		}

		br.close();
		return list;
	}
	
}

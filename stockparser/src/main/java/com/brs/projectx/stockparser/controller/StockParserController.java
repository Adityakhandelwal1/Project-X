package com.brs.projectx.stockparser.controller;

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
	RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping(value ="/Stocks", method = RequestMethod.POST)
	public String createData(@RequestBody StockData stockData) {
		redisTemplate.opsForHash().putIfAbsent(REDIS_INDEX_KEY, stockData.getName(), stockData.toString());
		return "DATA SAVED";
	}
	@RequestMapping(value ="/Stocks", method = RequestMethod.GET)
	public ResponseEntity<Object> getData(){
		return new ResponseEntity<>(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY),HttpStatus.OK);
	}
	
}

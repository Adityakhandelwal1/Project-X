package com.projectx.springRedis.controller;


import com.projectx.springRedis.model.StockModel;
import com.projectx.springRedis.repository.StockRepositoryImpl;
import com.projectx.springRedis.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StockParserController {
    private static final String REDIS_INDEX_KEY = "StockModel";


    private RedisTemplate<String, Object> redisTemplate;
    private static Jedis jedis = new Jedis();


    @Autowired
    private  StockRepositoryImpl stockRepositoryImpl;


    @Autowired
    private StockService stockService;


//


    @RequestMapping(value="/displayStocks", method = RequestMethod.GET)
    public List<StockModel> getAllStocksName(){

        return  stockRepositoryImpl.refactorDataToModel(jedis.hgetAll(REDIS_INDEX_KEY));
    }

    @RequestMapping(value="/get5percentLow", method = RequestMethod.GET)
    public List<StockModel> get5PercentLow(){
        List<StockModel> stockModelList = stockRepositoryImpl.refactorDataToModel(jedis.hgetAll(REDIS_INDEX_KEY));
        return stockService.get5percentLowStocks(stockModelList);
    }

    @RequestMapping(value="/get5percentHigh", method = RequestMethod.GET)
    public List<StockModel> get5PercentHigh(){
       List<StockModel> stockModelList = stockRepositoryImpl.refactorDataToModel(jedis.hgetAll(REDIS_INDEX_KEY));
       return stockService.get5percentUpStocks(stockModelList);
    }



    @RequestMapping("/addData")
    public List<StockModel> readCSV() throws IOException {

        
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/output.csv"));
        String line;
        List<String> positiveStocksList = new ArrayList<>();
        List<String> negativeStocksList = new ArrayList<>();
        List<StockModel> positiveStockModelList  = new ArrayList<>();
        List<StockModel> negativeStockModelList  = new ArrayList<>();
        List<StockModel> stockModelList  = new ArrayList<>();

        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");

            StockModel stockModel = new StockModel();
            stockModel.setName(fields[0]);
            stockModel.setPrices(fields[1]);
            stockModel.setChange(fields[2]);
            String percentageChange = fields[3];
            if(percentageChange.contains("-")){
                String[] splitPercent = percentageChange.split("%");
                String[] finalNumber = splitPercent[0].split("-");
                System.out.println(finalNumber[1]);
                stockModel.setPercentageChangeDirection("-");
                stockModel.setPercentageChange(finalNumber[1]);
                stockModel.setMarketCap(fields[4]);
                stockModel.setAverageVolume(fields[5]);
                stockModel.setVolume(fields[6]);
                stockModel.setWeekRange(fields[7]);
                negativeStocksList.add(fields[0]);
                negativeStockModelList.add(stockModel);
            }else if(percentageChange.contains("+")){
                String[] splitPercent = percentageChange.split("%");
                String[] finalNumber = splitPercent[0].split("\\+");
                stockModel.setPercentageChangeDirection("+");
                stockModel.setPercentageChange(finalNumber[1]);
                System.out.println(finalNumber[1]);
                stockModel.setMarketCap(fields[4]);
                stockModel.setAverageVolume(fields[5]);
                stockModel.setVolume(fields[6]);
                stockModel.setWeekRange(fields[7]);
                positiveStocksList.add(fields[0]);
                positiveStockModelList.add(stockModel);
            }else{
                stockModel.setPercentageChangeDirection("na");
                stockModel.setPercentageChange("0.00");
            }


            stockModelList.add(stockModel);
        }

        stockRepositoryImpl.addPositiveStock(positiveStocksList);
        stockRepositoryImpl.addSingleStockHash(positiveStockModelList);
        stockRepositoryImpl.addNegativeStock(negativeStocksList);
        stockRepositoryImpl.addSingleStockHash(negativeStockModelList);


        br.close();
        return stockModelList;
    }

}

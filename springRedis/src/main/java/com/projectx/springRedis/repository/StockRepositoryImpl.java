package com.projectx.springRedis.repository;

import com.projectx.springRedis.model.StockModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;


@Repository
public class StockRepositoryImpl implements StockRepository {

    private static Jedis jedis = new Jedis();
    private static final Logger logger = Logger.getLogger(StockRepositoryImpl.class);



    @Override
    public List<StockModel> get5PercentLow() {
        return null;
    }

    @Override
    public List<StockModel> refactorDataToModel(Map<String, String> redisData) {

        Iterator hmIterator = redisData.entrySet().iterator();
        List<StockModel> resultModel = new ArrayList<StockModel>();

        while(hmIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            logger.info("data");
            logger.info(mapElement.getValue());
            String data = ((String) mapElement.getValue());
            System.out.println(data);
            StockModel stock = convertStringTOModel(data);
            if(stock != null){
                resultModel.add(stock);
            }

        }

        return resultModel;

    }


    public StockModel convertStringTOModel(String str){


        StockModel stockModel = new StockModel();
        if(str.length()>1) {
            str = str.replace("StockModel", "");
            str = str.substring(1, str.length() - 1);
            System.out.println(str);
            String[] keyValuePairs = str.split(",");
            String name = keyValuePairs[0].split("=")[1].trim();
            String prices = keyValuePairs[1].split("=")[1].trim();
            String percentageChange = keyValuePairs[3].split("=")[1].trim();
            String marketCap = keyValuePairs[4].split("=")[1].trim();
            String avgVolume = keyValuePairs[5].split("=")[1].trim();
            String volume  =  keyValuePairs[6].split("=")[1].trim();
            String weekRange = keyValuePairs[7].split("=")[1].trim();
            String change = keyValuePairs[2].split("=")[1].trim();

            stockModel.setName(name.substring(1, name.length()-1));
            stockModel.setPrices(prices.substring(1, prices.length()-1));
            stockModel.setChange(change.substring(1, change.length()-1));
            stockModel.setPercentageChange(percentageChange.substring(1, percentageChange.length()-1));
            stockModel.setMarketCap(marketCap.substring(1, marketCap.length()-1));
            stockModel.setAverageVolume(avgVolume.substring(1, avgVolume.length()-1));
            stockModel.setVolume(volume.substring(1, volume.length()-1));
            stockModel.setWeekRange(weekRange.substring(1, weekRange.length()-1));

            System.out.println(stockModel.toString());
        }

        return stockModel;

    }


    public void addPositiveStock(List<String> stockNamesList){
        for(String name: stockNamesList){

            jedis.lpush("all:positiveStocks", name);
        }
    }
    public void addNegativeStock(List<String> stockNamesList){
        for(String name: stockNamesList){
            jedis.lpush("all:negativeStocks", name);
        }
    }



    public  void addSingleStockHash(List<StockModel> stockModelList){


        for(StockModel stockModel: stockModelList){

            System.out.println(stockModel.toString());
            Map<String, String> singleStock = new HashMap<>();
            singleStock.put("name", stockModel.getName());
            singleStock.put("price", stockModel.getPrices());
            singleStock.put("change", stockModel.getChange());
            singleStock.put("percentChangeDirection", stockModel.getPercentageChangeDirection());
            singleStock.put("percentageChange", stockModel.getPercentageChange());
            singleStock.put("marketCap", stockModel.getMarketCap());
            singleStock.put("avgVolume", stockModel.getAverageVolume());
            singleStock.put("volume", stockModel.getVolume());
            singleStock.put("52weekRange", stockModel.getWeekRange());

            jedis.hmset("stock:".concat(stockModel.getName()), singleStock);
            System.out.println(jedis.hgetAll("stock:".concat(stockModel.getName())));


        }
    }
}

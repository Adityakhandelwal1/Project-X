package com.projectx.springRedis.service;


import com.projectx.springRedis.model.StockModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {


    public List<StockModel> get5percentUpStocks(List<StockModel> stockModelList) {

        List<StockModel> resultList = new ArrayList<>();
        for(StockModel stock: stockModelList){
            if(stock.getPrices() != null){
                System.out.println(stock.toString());
                String priceChange = stock.getPercentageChange();
                if (!priceChange.contains("/")) {

                    Double priceDouble = Double.parseDouble(priceChange.substring(1, priceChange.length()-1));
                    System.out.println(priceDouble);
                    if(priceChange.contains("+") && priceDouble>=5.0){
                        resultList.add(stock);
                    }
                }

            }

        }
        return  resultList;

    }

    public List<StockModel> get5percentLowStocks(List<StockModel> stockModelList) {

        List<StockModel> resultList = new ArrayList<>();
        for(StockModel stock: stockModelList){
            if(stock.getPrices() != null){
                System.out.println(stock.toString());
                String priceChange = stock.getPercentageChange();
                if (!priceChange.contains("/")) {

                    Double priceDouble = Double.parseDouble(priceChange.substring(1, priceChange.length()-1));
                    System.out.println(priceDouble);
                    if(priceChange.contains("-") && priceDouble>=5.0){
                        resultList.add(stock);
                    }
                }

            }

        }
        return  resultList;

    }

}

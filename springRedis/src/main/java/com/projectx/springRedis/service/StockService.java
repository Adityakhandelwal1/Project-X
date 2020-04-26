package com.projectx.springRedis.service;

import com.projectx.springRedis.model.StockModel;

import java.util.List;

public interface StockService {

    public List<StockModel> get5percentUpStocks(List<StockModel> stockModelList);

    public List<StockModel> get5percentLowStocks(List<StockModel> stockModelList);


    }

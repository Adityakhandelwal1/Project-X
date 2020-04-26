package com.projectx.springRedis.repository;

import com.projectx.springRedis.model.StockModel;

import java.util.List;
import java.util.Map;

public interface StockRepository {


    public List<StockModel> get5PercentLow();
    public  List<StockModel> refactorDataToModel(Map<String, String> redisData);

}

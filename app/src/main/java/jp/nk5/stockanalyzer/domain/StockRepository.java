package jp.nk5.stockanalyzer.domain;

import java.util.List;

/* StockとRecordedStockの親和性が高いので，このrepositoryで包括管理する */
public interface StockRepository {

    //CRUD operation to Stock
    List<Stock> getAllStock() throws Exception;
    void setStock(int code, String name) throws  Exception;
    void updateStock(int code, String name) throws  Exception;
    void removeStock(int code) throws Exception;

    //CRUD operation to DailyData
    List<DailyData> getDailyDataByCode(int code) throws Exception;
    void setDailyData(int code, int price, int year, int month, int day) throws Exception;
    void updateDailyData(int code, int price, int year, int month, int day) throws Exception;
    void removeDailyData(int code, int year, int month, int day) throws Exception;

    //util
    boolean hasSameCode(int code) throws  Exception;
    boolean hasSameDailyData(int code, int year, int month, int price) throws  Exception;

}

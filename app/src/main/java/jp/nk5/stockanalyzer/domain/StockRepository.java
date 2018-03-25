package jp.nk5.stockanalyzer.domain;

import java.util.List;

/* StockとRecordedStockの親和性が高いので，このrepositoryで包括管理する */
public interface StockRepository {

    List<Stock> getAllStock() throws Exception;
    void setStock(int code, String name) throws  Exception;
    void setDailyData(int code, int price, int year, int month, int day) throws Exception;
    void updateStock(int code, String name) throws  Exception;
    boolean hasSameCode(int code) throws  Exception;
    void removeStock(int code) throws Exception;

}

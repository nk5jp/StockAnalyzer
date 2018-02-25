package jp.nk5.stockanalyzer.domain;

import java.util.List;

public interface StockRepository {

    List<Stock> getAllStock() throws Exception;
    void setStock(int code, String name) throws  Exception;
    void updateStock(int code, String name) throws  Exception;
    boolean hasSameCode(int code) throws  Exception;

}

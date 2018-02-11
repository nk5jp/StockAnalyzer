package jp.nk5.stockanalyzer.domain;

import java.util.List;

public interface StockRepository {

    Stock getStockByCode(int code);
    List<Stock> getDisplayedStocks();

}

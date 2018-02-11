package jp.nk5.stockanalyzer.domain;

import java.util.List;

public interface StockDetailSearcher {
    void getStockByCodes(List<Stock> stocks);
}

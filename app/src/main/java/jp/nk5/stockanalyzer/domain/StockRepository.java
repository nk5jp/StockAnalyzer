package jp.nk5.stockanalyzer.domain;

public interface StockRepository {

    public Stock getStockByCode(int code);

}

package jp.nk5.stockanalyzer.domain;

public interface ClosingPriceRepository {

    RecordedStock getStockByCode(int code);

}

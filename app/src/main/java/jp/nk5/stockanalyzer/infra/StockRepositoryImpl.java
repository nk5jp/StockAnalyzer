package jp.nk5.stockanalyzer.infra;

import java.util.ArrayList;

import jp.nk5.stockanalyzer.domain.StockDetail;
import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.domain.Stock;

public class StockRepositoryImpl implements StockRepository {

    public Stock getStockByCode(int code)
    {
        return new Stock(1, "aa", new ArrayList<StockDetail>());
    }
}

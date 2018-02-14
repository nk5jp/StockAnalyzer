package jp.nk5.stockanalyzer.infra;

import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;

public class StockRepositoryDB implements StockRepository {

    public List<Stock> getAllStock() {
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(new Stock(8410, "セブン銀行"));
        stocks.add(new Stock(9501, "東京電力"));
        stocks.add(new Stock(9697, "カプコン"));
        return stocks;
    }
}

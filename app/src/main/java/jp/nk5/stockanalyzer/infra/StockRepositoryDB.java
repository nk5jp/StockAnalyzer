package jp.nk5.stockanalyzer.infra;

import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.StockDetail;
import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.domain.Stock;

public class StockRepositoryDB implements StockRepository {

    public Stock getStockByCode(int code)
    {
        //Provisional implementation
        return new Stock(8410, "セブン銀行", true, new ArrayList<StockDetail>());
    }

    public List<Stock> getDisplayedStocks()
    {
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(new Stock(8410, "セブン銀行", true, new ArrayList<StockDetail>()));
        stocks.add(new Stock(9501, "東京電力", true, new ArrayList<StockDetail>()));
        stocks.add(new Stock(9697, "カプコン", true, new ArrayList<StockDetail>()));
        return stocks;
    }

}

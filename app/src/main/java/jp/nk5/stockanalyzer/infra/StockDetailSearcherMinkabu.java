package jp.nk5.stockanalyzer.infra;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockDetailSearcher;

/**/
public class StockDetailSearcherMinkabu implements StockDetailSearcher {

    private SearchMinkabuListener listener;

    public StockDetailSearcherMinkabu(SearchMinkabuListener listener)
    {
        this.listener = listener;
    }

    public void getStockByCodes(List<Stock> stocks)
    {
        new SearchMinkabuAsyncTask(listener).execute(stocks);
    }

}

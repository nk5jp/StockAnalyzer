package jp.nk5.stockanalyzer.infra;

import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.CurrentPriceRepository;

/**/
public class CurrentPriceRepositoryMinkabu implements CurrentPriceRepository {

    private SearchMinkabuListener listener;
    private final int UNSEARCHED_PRICE = -1;
    private final String UNSEARCHED_REMARKS = "";

    public CurrentPriceRepositoryMinkabu(SearchMinkabuListener listener)
    {
        this.listener = listener;
    }

    public List<CurrentStock> getAllCurrentStock()
    {

        List<CurrentStock> stocks = new ArrayList<CurrentStock>();
        stocks.add(new CurrentStock(7203, "トヨタ", UNSEARCHED_PRICE, UNSEARCHED_REMARKS));
        stocks.add(new CurrentStock(7974, "任天堂", UNSEARCHED_PRICE, UNSEARCHED_REMARKS));
        stocks.add(new CurrentStock(6758, "ソニー", UNSEARCHED_PRICE, UNSEARCHED_REMARKS));
        return stocks;
    }

    public void getAllDisplayedStocks()
    {
        List<CurrentStock> stocks = this.getAllCurrentStock();
        new SearchMinkabuAsyncTask(listener).execute(stocks);
    }

}

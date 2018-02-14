package jp.nk5.stockanalyzer.infra;

import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentPriceRepository;
import jp.nk5.stockanalyzer.domain.Stock;

/**/
public class CurrentPriceRepositoryMinkabu implements CurrentPriceRepository {

    private SearchMinkabuListener listener;

    public CurrentPriceRepositoryMinkabu(SearchMinkabuListener listener)
    {
        this.listener = listener;
    }

    public void getAllCurrentPrices(List<Stock> stocks)
    {
         new SearchMinkabuAsyncTask(listener).execute(stocks.toArray(new Stock[0]));
    }

}

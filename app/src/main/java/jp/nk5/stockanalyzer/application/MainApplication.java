package jp.nk5.stockanalyzer.application;

import android.content.Context;

import java.util.List;

import jp.nk5.stockanalyzer.ViewModel.CurrentPrice;
import jp.nk5.stockanalyzer.ViewModel.MainViewModel;
import jp.nk5.stockanalyzer.ViewModel.UpdateViewListener;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockDetailSearcher;
import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.infra.SearchMinkabuListener;
import jp.nk5.stockanalyzer.infra.StockDetailSearcherMinkabu;
import jp.nk5.stockanalyzer.infra.StockRepositoryDB;

public class MainApplication implements SearchMinkabuListener {

    private Context context;
    private MainViewModel viewModel;
    private StockDetailSearcher searcher;
    private StockRepository repository;
    private UpdateViewListener listener;

    public MainApplication(UpdateViewListener listener, MainViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        searcher = new StockDetailSearcherMinkabu(this);
        repository = new StockRepositoryDB();
    }

    public void getCurrentPrices()
    {
        List<Stock> stocks = repository.getDisplayedStocks();
        searcher.getStockByCodes(stocks);
    }

    @Override
    public void updateUI(List<String> strings) {
        for(String string : strings)
        {
            String[] array = string.split(":");
            viewModel.getCurrentPrices().add(
                    new CurrentPrice(
                            Integer.parseInt(array[0]),
                            array[1],
                            array[2],
                            Integer.parseInt(array[3])
                    )
            );
        }
        listener.updateView();
    }

    @Override
    public void lockUI() {

    }

    @Override
    public void unlockUI() {

    }
}

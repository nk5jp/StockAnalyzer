package jp.nk5.stockanalyzer.application;

import java.util.List;

import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.infra.StockRepositoryDB;
import jp.nk5.stockanalyzer.viewmodel.MainViewModel;
import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;
import jp.nk5.stockanalyzer.domain.CurrentStock;
import jp.nk5.stockanalyzer.domain.CurrentPriceRepository;
import jp.nk5.stockanalyzer.infra.SearchMinkabuListener;
import jp.nk5.stockanalyzer.infra.CurrentPriceRepositoryMinkabu;

public class MainApplication implements SearchMinkabuListener {

    private MainViewModel viewModel;
    private CurrentPriceRepository currentPriceRepository;
    private StockRepository stockRepository;
    private UpdateViewListener listener;

    public MainApplication(UpdateViewListener listener, MainViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        currentPriceRepository = new CurrentPriceRepositoryMinkabu(this);
        stockRepository = new StockRepositoryDB();
    }

    public void getCurrentPrices()
    {
        currentPriceRepository.getAllCurrentPrices(stockRepository.getAllStock());
    }

    @Override
    public void updateUI(List<CurrentStock> stocks) {
        if (stocks == null)
        {
            listener.showError();
        } else {
            viewModel.setCurrentStocks(stocks);
            listener.updateView();
        }
    }

    @Override
    public void lockUI() {

    }

    @Override
    public void unlockUI() {

    }
}

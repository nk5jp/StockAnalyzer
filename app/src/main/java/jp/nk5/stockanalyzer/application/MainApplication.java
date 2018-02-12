package jp.nk5.stockanalyzer.application;

import java.util.List;

import jp.nk5.stockanalyzer.ViewModel.MainViewModel;
import jp.nk5.stockanalyzer.ViewModel.UpdateViewListener;
import jp.nk5.stockanalyzer.domain.CurrentStock;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.CurrentPriceRepository;
import jp.nk5.stockanalyzer.domain.ClosingPriceRepository;
import jp.nk5.stockanalyzer.infra.SearchMinkabuListener;
import jp.nk5.stockanalyzer.infra.CurrentPriceRepositoryMinkabu;
import jp.nk5.stockanalyzer.infra.ClosingPriceRepositoryDB;

public class MainApplication implements SearchMinkabuListener {

    private MainViewModel viewModel;
    private CurrentPriceRepository repository;
    private UpdateViewListener listener;

    public MainApplication(UpdateViewListener listener, MainViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        repository = new CurrentPriceRepositoryMinkabu(this);
    }

    public void getCurrentPrices()
    {
        repository.getAllDisplayedStocks();
    }

    @Override
    public void updateUI(List<CurrentStock> stocks) {
        viewModel.setCurrentStocks(stocks);
        listener.updateView();
    }

    @Override
    public void lockUI() {

    }

    @Override
    public void unlockUI() {

    }
}

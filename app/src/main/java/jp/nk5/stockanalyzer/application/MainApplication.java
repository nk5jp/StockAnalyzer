package jp.nk5.stockanalyzer.application;

import android.content.Context;

import java.util.List;
import java.util.Locale;

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

    public MainApplication(Context context, UpdateViewListener listener, MainViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        currentPriceRepository = new CurrentPriceRepositoryMinkabu(this);
        stockRepository = StockRepositoryDB.getInstance(context);
    }

    public void getCurrentPrices()
    {
        try {
            currentPriceRepository.getAllCurrentPrices(stockRepository.getAllStock());
        } catch (Exception e) {
            listener.showError("cannot get stock data from DB");
        }
    }

    @Override
    public void updateUI(List<CurrentStock> stocks) {
        if (stocks == null)
        {
            listener.showError("scraping error");
        } else {
            viewModel.setCurrentStocks(stocks);
            listener.updateView();
        }
    }

    public void saveCurrentPrice(int year, int month, int day) {
        for (CurrentStock currentStock : viewModel.getCurrentStocks()) {
            int code = currentStock.getCode();
            int price = currentStock.getPrice();
            try {
                stockRepository.setDailyData(code, price, year, month, day);
            } catch (Exception e) {
                listener.showError(String.format(Locale.JAPAN, "failed to save %d", code));
                break;
            }
        }
    }

    @Override
    public void lockUI() {

    }

    @Override
    public void unlockUI() {

    }
}

package jp.nk5.stockanalyzer.application;

import android.content.Context;

import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.infra.StockRepositoryDB;
import jp.nk5.stockanalyzer.viewmodel.StockViewModel;
import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class StockApplication {

    private UpdateViewListener listener;
    private StockViewModel viewModel;
    private StockRepository stockRepository;

    public StockApplication (Context context, UpdateViewListener listener, StockViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        this.stockRepository = StockRepositoryDB.getInstance(context);
    }

    public void addEditStock(int code, String name)
    {
        try {
            if (name.equals(""))
            {
                listener.showError("name is empty");
                return;
            }
            if (stockRepository.hasSameCode(code))
            {
                stockRepository.updateStock(code, name);
            } else {
                stockRepository.setStock(code, name);
            }
            getStock();
        } catch (Exception e) {
            listener.showError("cannot add stock");
        }
    }

    public void deleteStock(int code)
    {
        try {
            if (stockRepository.hasSameCode(code)) {
                stockRepository.removeStock(code);
            } else {
                listener.showError("the code does not exist");
            }
        } catch (Exception e) {
            listener.showError("cannot delete stock");
        }
    }

    public void selectStock(int code, String name)
    {
        viewModel.setCode(code);
        viewModel.setName(name);
        listener.updateView();
    }

    public void getStock()
    {
        try {
            viewModel.setStocks(stockRepository.getAllStock());
        } catch (Exception e) {
            listener.showError("cannot get stocks");
        }
    }

}

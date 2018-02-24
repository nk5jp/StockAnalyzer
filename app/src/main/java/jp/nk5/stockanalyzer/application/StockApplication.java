package jp.nk5.stockanalyzer.application;

import android.content.Context;

import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.infra.StockRepositoryDB;
import jp.nk5.stockanalyzer.viewmodel.StockViewModel;
import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class StockApplication {

    private UpdateViewListener listener;
    private StockViewModel viewModel;
    private StockRepository repository;

    public StockApplication (Context context, UpdateViewListener listener, StockViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        this.repository = StockRepositoryDB.getInstance(context);
    }

    public void addStock(int code, String name)
    {
        try {
            if (repository.hasSameCode(code))
            {
                listener.showError("the code is already used");
                return;
            }
            if (name.equals(""))
            {
                listener.showError("name is empty");
                return;
            }
            repository.setStock(new Stock(code, name));
        } catch (Exception e) {
            listener.showError("cannot add stock");
        }
    }

}

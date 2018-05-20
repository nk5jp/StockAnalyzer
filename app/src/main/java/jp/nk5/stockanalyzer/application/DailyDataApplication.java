package jp.nk5.stockanalyzer.application;

import android.content.Context;

import java.util.List;

import jp.nk5.stockanalyzer.domain.DailyData;
import jp.nk5.stockanalyzer.domain.StockRepository;
import jp.nk5.stockanalyzer.infra.StockRepositoryDB;
import jp.nk5.stockanalyzer.viewmodel.DailyDataViewModel;
import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class DailyDataApplication {

    private UpdateViewListener listener;
    private DailyDataViewModel viewModel;
    private StockRepository repository;

    public DailyDataApplication (Context context, UpdateViewListener listener, DailyDataViewModel viewModel)
    {
        this.listener = listener;
        this.viewModel = viewModel;
        this.repository = StockRepositoryDB.getInstance(context);
    }

    public void getDailyData(){
        try {
            viewModel.setDailyData(repository.getDailyDataByCode(viewModel.getCode()));
        } catch (Exception e)
        {
            listener.showError("cannot get Daily Data");
        }
    }

    public void savePrice(int year, int month, int day, int price)
    {
        int code = viewModel.getCode();
        selectDailyData(year, month, day, price);
        try {
            if (isDeleteCase(price)) {
                repository.removeDailyData(code, year, month, day);
                return;
            }
            if (isUpdateCase(code, year, month, day)) {
                repository.updateDailyData(code, price, year, month ,day);
                return;
            }
            repository.setDailyData(code, price, year, month, day);
        } catch (Exception e) {
            listener.showError("cannot save price");
        }
    }

    public void selectDailyData(int year, int month, int day, int price)
    {
        viewModel.setYear(year);
        viewModel.setMonth(month);
        viewModel.setDay(day);
        viewModel.setPrice(price);
    }

    private boolean isDeleteCase(int price)
    {
        return price == 0;
    }

    private boolean isUpdateCase(int code, int year, int month, int day) throws  Exception
    {
        return repository.hasSameDailyData(code, year, month, day);
    }

}

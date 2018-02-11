package jp.nk5.stockanalyzer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import jp.nk5.stockanalyzer.ViewModel.CurrentPrice;
import jp.nk5.stockanalyzer.ViewModel.MainViewModel;
import jp.nk5.stockanalyzer.ViewModel.UpdateViewListener;
import jp.nk5.stockanalyzer.adapter.CurrentPriceAdapter;
import jp.nk5.stockanalyzer.application.MainApplication;

public class MainActivity extends Activity implements UpdateViewListener {

    private MainApplication application;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(new ArrayList<CurrentPrice>());
        application = new MainApplication(this, this.viewModel);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        application.getCurrentPrices();
    }

    public void updateView()
    {
        CurrentPriceAdapter adapter = new CurrentPriceAdapter(this, android.R.layout.simple_list_item_1, viewModel.getCurrentPrices());
        ListView listView = (ListView)this.findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

    public void showError()
    {

    }

}

package jp.nk5.stockanalyzer;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

import jp.nk5.stockanalyzer.ViewModel.CurrentPrice;
import jp.nk5.stockanalyzer.ViewModel.MainViewModel;
import jp.nk5.stockanalyzer.application.MainApplication;

public class MainActivity extends Activity {

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

    public void lockUI()
    {

    }

    public void unlockUI()
    {

    }

}

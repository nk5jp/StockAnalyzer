package jp.nk5.stockanalyzer;

import android.os.Bundle;
import android.view.View;

import jp.nk5.stockanalyzer.application.StockApplication;
import jp.nk5.stockanalyzer.viewmodel.StockViewModel;

public class StockActivity extends BaseActivity {

    private StockViewModel viewModel;
    private StockApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        viewModel = new StockViewModel(-1, "", false);
        application = new StockApplication(this, this, viewModel);
    }

    public void onClickAddButton (View view)
    {
        try {
            int code = getIntegerFromEditText(R.id.editText1);
            String name = getStringFromEditText(R.id.editText2);
            application.addStock(code, name);
            this.finish();
        } catch (Exception e) {
            showError("cannot get value from EditText");
        }

    }

    @Override
    public void updateView() {

    }

}

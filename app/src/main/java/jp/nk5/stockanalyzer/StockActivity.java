package jp.nk5.stockanalyzer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jp.nk5.stockanalyzer.application.StockApplication;
import jp.nk5.stockanalyzer.viewmodel.StockViewModel;
import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class StockActivity extends Activity implements UpdateViewListener {

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
        EditText editText = findViewById(R.id.editText1);
        int code = Integer.parseInt(editText.getText().toString());
        editText = findViewById(R.id.editText2);
        String name = editText.getText().toString();

        application.addStock(code, name);
        this.finish();

    }

    @Override
    public void updateView() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

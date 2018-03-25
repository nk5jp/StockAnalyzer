package jp.nk5.stockanalyzer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import jp.nk5.stockanalyzer.adapter.StockAdapter;
import jp.nk5.stockanalyzer.application.StockApplication;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.viewmodel.StockViewModel;

public class StockActivity extends BaseActivity implements ListView.OnItemClickListener {

    private StockViewModel viewModel;
    private StockApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        viewModel = new StockViewModel(0, "", new ArrayList<Stock>());
        application = new StockApplication(this, this, viewModel);
        ListView listView = findViewById(R.id.listView1);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        application.getStock();
        updateView();
    }

    public void onClickAddEditButton (View view)
    {
        try {
            int code = getIntegerFromEditText(R.id.editText1);
            String name = getStringFromEditText(R.id.editText2);
            application.addEditStock(code, name);
            this.finish();
        } catch (Exception e) {
            showError("cannot get value from EditText");
        }
    }

    public void onClickDeleteButton(View view)
    {
        int code = getIntegerFromEditText(R.id.editText1);
        application.deleteStock(code);
        this.finish();
    }

    @Override
    public void updateView() {
        setIntegerToEditText(R.id.editText1, viewModel.getCode());
        setStringToEditText(R.id.editText2, viewModel.getName());
        StockAdapter adapter = new StockAdapter(this, android.R.layout.simple_list_item_1, viewModel.getStocks());
        ListView listView = this.findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Stock stock = ((Stock) parent.getItemAtPosition(position));
        int code = stock.getCode();
        String name = stock.getName();
        application.selectStock(code, name);
    }

}

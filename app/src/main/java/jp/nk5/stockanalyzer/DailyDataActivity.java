package jp.nk5.stockanalyzer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import jp.nk5.stockanalyzer.adapter.DailyDataAdapter;
import jp.nk5.stockanalyzer.application.DailyDataApplication;
import jp.nk5.stockanalyzer.domain.DailyData;
import jp.nk5.stockanalyzer.viewmodel.DailyDataViewModel;

public class DailyDataActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, ListView.OnItemClickListener {

    private DailyDataViewModel viewModel;
    private DailyDataApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);
        ListView listView = findViewById(R.id.listView1);
        listView.setOnItemClickListener(this);

        Intent intent = this.getIntent();
        int code = intent.getIntExtra("CODE", 0);
        String name = intent.getStringExtra("NAME");
        setStringToTextView(R.id.textView1, String.valueOf(code));
        setStringToTextView(R.id.textView3, name);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        viewModel = new DailyDataViewModel(code, name, year, month, day, 0, new ArrayList<DailyData>());
        application = new DailyDataApplication(this, this, viewModel);

    }

    @Override
    protected void onStart() {
        super.onStart();
        application.getDailyData();
        updateView();
    }

    public void onClickAddEditButton(View view)
    {
        int year = viewModel.getYear();
        int month = viewModel.getMonth() -1;
        int day = viewModel.getDay();
        new DatePickerDialog(this, this, year, month, day).show();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DailyData dailyData = (DailyData) parent.getItemAtPosition(position);
        int year = dailyData.getYear();
        int month = dailyData.getMonth();
        int day = dailyData.getDay();
        int price = dailyData.getPrice();
        application.selectDailyData(year, month, day, price);
        updateView();
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        int price = getIntegerFromEditText(R.id.editText1);
        application.savePrice(year, month + 1, day, price);
    }

    @Override
    public void updateView() {
        setIntegerToEditText(R.id.editText1, viewModel.getPrice());
        DailyDataAdapter adapter = new DailyDataAdapter(this, android.R.layout.simple_list_item_1, viewModel.getDailyData());
        ListView listView = this.findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

}

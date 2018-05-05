package jp.nk5.stockanalyzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import jp.nk5.stockanalyzer.domain.Stock;

public class DailyDataActivity extends BaseActivity implements ListView.OnItemClickListener {

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

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Stock stock = ((Stock) parent.getItemAtPosition(position));
        //int code = stock.getCode();
        //String name = stock.getName();
    }
}

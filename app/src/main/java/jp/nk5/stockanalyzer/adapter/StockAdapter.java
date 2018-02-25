package jp.nk5.stockanalyzer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import jp.nk5.stockanalyzer.domain.Stock;
import android.support.annotation.NonNull;

public class StockAdapter extends ArrayAdapter<Stock> {

    private LayoutInflater layoutInflater;

    public StockAdapter(Context context, int id, List<Stock> stocks) {
        super(context, id, stocks);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent)
    {
        if (view == null)
        {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }

        Stock stock = getItem(position);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(String.format(Locale.JAPAN, "%d : %s", stock.getCode(), stock.getName()));
        return view;
    }
}

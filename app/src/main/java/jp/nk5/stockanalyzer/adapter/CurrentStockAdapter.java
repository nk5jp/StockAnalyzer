package jp.nk5.stockanalyzer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;

public class CurrentStockAdapter extends ArrayAdapter<CurrentStock> {

    private LayoutInflater layoutInflater;

    public CurrentStockAdapter(Context context, int id, List<CurrentStock> stocks) {
        super(context, id, stocks);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }

        CurrentStock stock = (CurrentStock) getItem(position);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(stock.getCode() + " : " + stock.getName() + " : " + stock.getRemarks() +" : "+ stock.getPrice());
        return view;
    }
}

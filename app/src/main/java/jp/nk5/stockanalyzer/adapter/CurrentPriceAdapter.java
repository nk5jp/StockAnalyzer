package jp.nk5.stockanalyzer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.nk5.stockanalyzer.ViewModel.CurrentPrice;

public class CurrentPriceAdapter extends ArrayAdapter<CurrentPrice> {

    private LayoutInflater layoutInflater;

    public CurrentPriceAdapter(Context context, int id, List<CurrentPrice> prices) {
        super(context, id, prices);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }

        CurrentPrice price = (CurrentPrice) getItem(position);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(price.getCode() + " : " + price.getName() + " : " + price.getDateString() +" : "+ price.getPrice());
        return view;
    }
}

package jp.nk5.stockanalyzer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import jp.nk5.stockanalyzer.domain.DailyData;

public class DailyDataAdapter extends ArrayAdapter <DailyData> {

    private LayoutInflater layoutInflater;

    public DailyDataAdapter(Context context, int id, List<DailyData> dailyData) {
        super(context, id, dailyData);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }

        DailyData dailyData = getItem(position);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(String.format(Locale.JAPAN, "%4d/%2d/%2d : %d",
                dailyData.getYear(),
                dailyData.getMonth(),
                dailyData.getDay(),
                dailyData.getPrice())
        );
        return view;
    }
}

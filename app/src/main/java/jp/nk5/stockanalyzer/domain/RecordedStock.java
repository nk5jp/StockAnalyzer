package jp.nk5.stockanalyzer.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class RecordedStock extends Stock {

    @Getter
    private List<DailyData> dailyData;

    public RecordedStock (int code, String name)
    {
        super(code, name);
        dailyData = new ArrayList<DailyData>();
    }

}

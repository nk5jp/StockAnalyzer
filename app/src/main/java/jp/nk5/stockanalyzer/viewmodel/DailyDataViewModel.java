package jp.nk5.stockanalyzer.viewmodel;

import java.util.List;

import jp.nk5.stockanalyzer.domain.DailyData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DailyDataViewModel {
    @Getter
    int code;
    @Getter
    String name;
    @Getter @Setter
    private int year;
    @Getter @Setter
    private int month;
    @Getter @Setter
    private int day;
    @Getter @Setter
    private int price;
    @Getter @Setter
    private List<DailyData> dailyData;
}

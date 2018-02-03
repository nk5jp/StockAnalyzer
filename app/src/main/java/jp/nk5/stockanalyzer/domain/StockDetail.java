package jp.nk5.stockanalyzer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockDetail {

    @Getter
    private int year;
    @Getter
    private int month;
    @Getter
    private int day;
    @Getter
    private int price;

}
package jp.nk5.stockanalyzer.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Stock {

    @Getter
    private int code;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private boolean isDisplayed;
    @Getter
    private List<StockDetail> stockDetails;

}

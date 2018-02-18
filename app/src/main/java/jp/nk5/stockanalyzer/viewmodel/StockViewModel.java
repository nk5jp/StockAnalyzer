package jp.nk5.stockanalyzer.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class StockViewModel {

    @Getter @Setter
    int code;
    @Getter @Setter
    String Name;
    @Getter @Setter
    boolean isSelected;
}

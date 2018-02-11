package jp.nk5.stockanalyzer.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CurrentPrice {

    @Getter
    int code;
    @Getter
    String name;
    @Getter
    String dateString;
    @Getter
    int price;

}

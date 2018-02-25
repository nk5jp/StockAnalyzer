package jp.nk5.stockanalyzer.viewmodel;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
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
    List<Stock> stocks;

}

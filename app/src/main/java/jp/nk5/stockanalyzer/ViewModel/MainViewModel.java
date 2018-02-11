package jp.nk5.stockanalyzer.ViewModel;

import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MainViewModel {

    @Getter
    private List<CurrentPrice> currentPrices;

}

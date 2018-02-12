package jp.nk5.stockanalyzer.viewmodel;

import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class MainViewModel {

    @Getter @Setter
    private List<CurrentStock> currentStocks;

}

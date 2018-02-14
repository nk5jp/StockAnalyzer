package jp.nk5.stockanalyzer.domain;

import java.util.List;

public interface CurrentPriceRepository {

    void getAllCurrentPrices(List<Stock> stocks);

}

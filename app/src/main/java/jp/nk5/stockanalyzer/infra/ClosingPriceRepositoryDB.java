package jp.nk5.stockanalyzer.infra;

import jp.nk5.stockanalyzer.domain.ClosingPriceRepository;
import jp.nk5.stockanalyzer.domain.RecordedStock;

public class ClosingPriceRepositoryDB implements ClosingPriceRepository {

    public RecordedStock getStockByCode(int code)
    {
        //Provisional implementation
        return new RecordedStock(8410, "セブン銀行");
    }

}

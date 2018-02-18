package jp.nk5.stockanalyzer.infra;

import android.content.Context;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;

public class StockRepositoryDB implements StockRepository {

    private Context context;
    private List<Stock> stocks;
    private StockDAO dao;
    private static StockRepositoryDB instance;

    public static StockRepositoryDB getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new StockRepositoryDB(context);
        }
        return instance;
    }

    private StockRepositoryDB(Context context) {
        this.context = context;
        this.dao = new StockDAO(context);
    }

    public void setStock(Stock stock) throws Exception {
        dao.setStock(stock);
        if (this.stocks == null)
        {
            this.stocks = dao.readAllStock();
        }
        stocks.add(stock);
    }

    public List<Stock> getAllStock() throws Exception {
        if (this.stocks == null)
        {
            this.stocks = dao.readAllStock();
        }
        return this.stocks;
    }
}

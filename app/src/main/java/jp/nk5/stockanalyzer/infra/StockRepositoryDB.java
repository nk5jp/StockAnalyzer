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
        initializeCollection();
        dao.setStock(stock);
        stocks.add(stock);
    }

    public boolean hasSameCode(int code) throws Exception
    {
        initializeCollection();
        for (Stock stock : stocks)
        {
            if (stock.getCode() == code)
            {
                return true;
            }
        }
        return false;
    }

    public List<Stock> getAllStock() throws Exception {
        initializeCollection();
        return this.stocks;
    }

    private void initializeCollection() throws Exception
    {
        if (this.stocks == null)
        {
            this.stocks = dao.readAllStock();
        }
    }

}

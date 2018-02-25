package jp.nk5.stockanalyzer.infra;

import android.content.Context;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;

public class StockRepositoryDB implements StockRepository {

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
        this.dao = new StockDAO(context);
    }

    public void setStock(int code, String name) throws Exception {
        initializeCollection();
        Stock stock = new Stock(code, name);
        dao.createStock(stock);
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

    public void updateStock(int code, String name) throws Exception {
        Stock tmpStock = new Stock(code, name);
        dao.updateStock(tmpStock);
        for (Stock stock : stocks) {
            if (stock.getCode() == code)
            {
                stock.setName(name);
            }
        }
    }

    private void initializeCollection() throws Exception
    {
        if (this.stocks == null)
        {
            this.stocks = dao.readAllStock();
        }
    }

}

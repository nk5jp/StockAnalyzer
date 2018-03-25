package jp.nk5.stockanalyzer.infra;

import android.content.Context;
import android.util.SparseArray;

import java.util.List;

import jp.nk5.stockanalyzer.domain.DailyData;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;

public class StockRepositoryDB implements StockRepository {

    private List<Stock> stocks;
    private SparseArray<List<DailyData>> dailyDataArray;
    private StockDAO stockDao;
    private DailyDataDAO dailyDataDAO;
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
        this.stockDao = new StockDAO(context);
        this.dailyDataDAO = new DailyDataDAO(context);
    }

    public void setStock(int code, String name) throws Exception {
        initializeCollection();
        Stock stock = new Stock(code, name);
        stockDao.createStock(stock);
        stocks.add(stock);
        List<DailyData> dailyDataList = dailyDataDAO.readDailyDataByCode(code);
        dailyDataArray.put(code, dailyDataList);
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
        return stocks;
    }

    public void updateStock(int code, String name) throws Exception {
        initializeCollection();
        Stock tmpStock = new Stock(code, name);
        stockDao.updateStock(tmpStock);
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
            stocks = stockDao.readAllStock();
            dailyDataArray = new SparseArray<>();
            for (Stock stock : stocks)
            {
                int code = stock.getCode();
                List<DailyData> dailyDataList = dailyDataDAO.readDailyDataByCode(code);
                dailyDataArray.put(code, dailyDataList);
            }
        }
    }

    public void removeStock(int code) throws Exception
    {

    }

    public void setDailyData(int code, int price, int year, int month, int day) throws Exception
    {
        initializeCollection();
        DailyData dailyData = new DailyData(code, year, month ,day, price);
        dailyDataDAO.create(dailyData, "DailyData");
        dailyDataArray.get(code).add(dailyData);
    }

}

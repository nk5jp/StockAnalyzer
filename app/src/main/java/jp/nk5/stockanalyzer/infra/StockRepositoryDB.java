package jp.nk5.stockanalyzer.infra;

import android.content.Context;
import android.util.SparseArray;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.nk5.stockanalyzer.domain.DailyData;
import jp.nk5.stockanalyzer.domain.Stock;
import jp.nk5.stockanalyzer.domain.StockRepository;

public class StockRepositoryDB implements StockRepository {

    private List<Stock> stocks;
    private SparseArray<List<DailyData>> dailyDataArray;
    private StockDAO stockDAO;
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
        this.stockDAO = new StockDAO(context);
        this.dailyDataDAO = new DailyDataDAO(context);
    }

    private void initializeCollection() throws Exception
    {
        if (this.stocks == null)
        {
            stocks = stockDAO.readAllStock();
            dailyDataArray = new SparseArray<>();
            for (Stock stock : stocks)
            {
                int code = stock.getCode();
                List<DailyData> dailyDataList = dailyDataDAO.readDailyDataByCode(code);
                dailyDataArray.put(code, dailyDataList);
            }
        }
    }

    public void setStock(int code, String name) throws Exception {
        initializeCollection();
        Stock stock = new Stock(code, name);
        stockDAO.createStock(stock);
        stocks.add(stock);
        List<DailyData> dailyDataList = dailyDataDAO.readDailyDataByCode(code);
        dailyDataArray.put(code, dailyDataList);
    }

    public List<Stock> getAllStock() throws Exception {
        initializeCollection();
        return stocks;
    }

    public void updateStock(int code, String name) throws Exception {
        initializeCollection();
        Stock tmpStock = new Stock(code, name);
        stockDAO.updateStock(tmpStock);
        Stock stock = getStockFromList(code);
        if (stock != null)
        {
            stock.setName(name);
        } else {
            throw new Exception();
        }
    }

    public void removeStock(int code) throws Exception
    {
        initializeCollection();
        dailyDataDAO.deleteAll(code);
        dailyDataArray.delete(code);
        Stock stock = getStockFromList(code);
        stockDAO.deleteStock(stock);
        stocks.remove(stock);
    }

    public List<DailyData> getDailyDataByCode(int code) throws Exception
    {
        initializeCollection();
        return dailyDataArray.get(code);
    }

    public void setDailyData(int code, int price, int year, int month, int day) throws Exception
    {
        initializeCollection();
        DailyData dailyData = new DailyData(code, year, month ,day, price);
        dailyDataDAO.create(dailyData, "DailyData");
        List<DailyData> dailyDataList = dailyDataArray.get(code);
        dailyDataList.add(dailyData);
        Collections.sort(dailyDataList, new DailyDataComparator());
    }

    public void updateDailyData(int code, int price, int year, int month, int day) throws Exception
    {
        initializeCollection();
        DailyData tmpDailyData = new DailyData(code, year, month, day, price);
        dailyDataDAO.updateDailyData(tmpDailyData);
        DailyData dailyData = getDailyDataFromArray(code, year, month, day);
        if (dailyData != null)
        {
            dailyData.setPrice(price);
        } else {
            throw new Exception();
        }
    }

    public void removeDailyData(int code, int year, int month, int day) throws Exception
    {
        initializeCollection();
        DailyData dailyData = getDailyDataFromArray(code, year, month, day);
        dailyDataDAO.deleteDailyData(dailyData);
        dailyDataArray.get(code).remove(dailyData);
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

    public boolean hasSameDailyData(int code, int year, int month, int day) throws  Exception
    {
        initializeCollection();
        if (getDailyDataFromArray(code, year, month, day) != null) return true;
        return false;
    }

    private DailyData getDailyDataFromArray (int code, int year, int month, int day)
    {
        for (DailyData dailyData : dailyDataArray.get(code))
        {
            if (dailyData.getYear() == year && dailyData.getMonth() == month && dailyData.getDay() == day)
                return dailyData;
        }
        return null;
    }

    private Stock getStockFromList (int code)
    {
        for (Stock stock : stocks)
        {
            if (stock.getCode() == code) return stock;
        }
        return null;
    }

    private class DailyDataComparator implements Comparator<DailyData> {
        @Override
        public int compare(DailyData d1, DailyData d2) {
            if (d1.getYear() != d2.getYear()) return d1.getYear() < d2.getYear() ? -1 : 1;
            if (d1.getMonth() != d2.getMonth()) return d1.getMonth() < d2.getMonth() ? -1 : 1;
            return d1.getDay() < d2.getDay() ? -1 : 1;
        }
    }

}

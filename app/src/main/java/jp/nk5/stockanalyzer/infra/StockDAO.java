package jp.nk5.stockanalyzer.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;

public class StockDAO extends DAO <Stock> {

    StockDAO(Context context)
    {
        super(context);
    }

    List<Stock> readAllStock() throws Exception
    {
        return read("select * from STOCK;", null);
    }

    void createStock(Stock stock) throws Exception
    {
        create(stock, "STOCK");
    }

    void updateStock(Stock stock) throws Exception
    {
        update(stock, "STOCK", "code = ?", getArgs(stock));
    }

    void deleteStock(Stock stock) throws Exception
    {
        delete("STOCK", "code = ?", getArgs(stock));
    }

    @Override
    protected Stock transformCursorToEntity(Cursor cursor) throws Exception {
        return new Stock(
                cursor.getInt(cursor.getColumnIndex("code")),
                cursor.getString(cursor.getColumnIndex("name"))
        );
    }

    @Override
    protected ContentValues transformEntityToValues(Stock stock) throws Exception {
        ContentValues values = new ContentValues();
        values.put("code", stock.getCode());
        values.put("name", stock.getName());
        return values;
    }

    @Override
    protected void updateEntityById(Stock stock, long rowId) throws Exception {
        //nothing to do because the primary key is not autoincrement.
    }

    protected String[] getArgs(Stock stock)
    {
        return new String[]{Integer.toString(stock.getCode())};
    }

}

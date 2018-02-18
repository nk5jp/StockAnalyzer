package jp.nk5.stockanalyzer.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import jp.nk5.stockanalyzer.domain.Stock;

public class StockDAO extends DAO <Stock> {

    public StockDAO (Context context)
    {
        super(context);
    }

    List<Stock> readAllStock() throws Exception
    {
        return read("select * from STOCK;", null);
    }

    public void setStock(Stock stock) throws Exception
    {
        create(stock, "STOCK");
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

}

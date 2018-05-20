package jp.nk5.stockanalyzer.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Locale;

import jp.nk5.stockanalyzer.domain.DailyData;
import jp.nk5.stockanalyzer.viewmodel.DailyDataViewModel;

public class DailyDataDAO extends DAO <DailyData> {

    DailyDataDAO(Context context)
    {
        super(context);
    }

    public List<DailyData> readDailyDataByCode(int code) throws Exception
    {
        return read("select * from DailyData where code = ? order by year, month, day;",
                new String[]{String.format(Locale.JAPAN, "%d", code)});
    }

    public void updateDailyData(DailyData dailyData) throws Exception
    {
        update(dailyData, "DailyData", "code = ? and year = ? and month = ? and day = ?", getArgs(dailyData));
    }

    public void deleteDailyData(DailyData dailyData) throws Exception
    {
        delete( "DailyData", "code = ? and year = ? and month = ? and day = ?", getArgs(dailyData));
    }

    public void deleteAll(int code) throws Exception
    {
        delete( "DailyData", "code = ?", new String[]{Integer.toString(code)});
    }

    @Override
    protected DailyData transformCursorToEntity(Cursor cursor) throws Exception {
        return new DailyData(
                cursor.getInt(cursor.getColumnIndex("code")),
                cursor.getInt(cursor.getColumnIndex("year")),
                cursor.getInt(cursor.getColumnIndex("month")),
                cursor.getInt(cursor.getColumnIndex("day")),
                cursor.getInt(cursor.getColumnIndex("price"))
        );
    }

    @Override
    protected ContentValues transformEntityToValues(DailyData dailyData) throws Exception {
        ContentValues values = new ContentValues();
        values.put("code", dailyData.getCode());
        values.put("year", dailyData.getYear());
        values.put("month", dailyData.getMonth());
        values.put("day", dailyData.getDay());
        values.put("price", dailyData.getPrice());
        return values;
    }

    @Override
    protected void updateEntityById(DailyData entity, long rowId) throws Exception {

    }

    private String[] getArgs(DailyData dailyData)
    {
        return new String[]{
                Integer.toString(dailyData.getCode()),
                Integer.toString(dailyData.getYear()),
                Integer.toString(dailyData.getMonth()),
                Integer.toString(dailyData.getDay())
        };
    }
}

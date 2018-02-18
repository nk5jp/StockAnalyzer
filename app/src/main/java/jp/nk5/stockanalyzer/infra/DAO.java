package jp.nk5.stockanalyzer.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class DAO <T> {

    private Context context;

    public DAO (Context context)
    {
        this.context = context;
    }

    public void create (T entity, String tableName) throws Exception
    {
        ContentValues contentValues = transformEntityToValues(entity);

        try (SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase()) {
            db.beginTransaction();
            long rowId = db.insert(tableName, null, contentValues);
            if (rowId == -1) {
                throw new Exception();
            } else {
                updateEntityById(entity, rowId);
                db.setTransactionSuccessful();
            }
            db.endTransaction();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public List<T> read(String selectQuery, String[] args) throws Exception {
        List<T> list = new ArrayList<>();

        try (
                SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, args);
        ) {
            db.beginTransaction();
            if (cursor.moveToFirst()) {
                do {
                    T entity = transformCursorToEntity(cursor);
                    list.add(entity);
                } while (cursor.moveToNext());
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            return list;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    protected abstract T transformCursorToEntity(Cursor cursor) throws Exception;
    protected abstract ContentValues transformEntityToValues(T entity) throws Exception;
    protected abstract void updateEntityById(T entity, long rowId) throws Exception;

}

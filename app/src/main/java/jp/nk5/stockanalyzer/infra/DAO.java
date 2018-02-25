package jp.nk5.stockanalyzer.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class DAO <T> {

    private Context context;

    DAO(Context context)
    {
        this.context = context;
    }

    void create(T entity, String tableName) throws Exception
    {
        ContentValues contentValues = transformEntityToValues(entity);

        try (SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase()) {
            long rowId = db.insert(tableName, null, contentValues);
            if (rowId == -1) {
                throw new Exception();
            } else {
                updateEntityById(entity, rowId);
            }
        }
    }

    List<T> read(String selectQuery, String[] args) throws Exception {
        List<T> list = new ArrayList<>();

        try (
                SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, args)
        ) {
            if (cursor.moveToFirst()) {
                do {
                    T entity = transformCursorToEntity(cursor);
                    list.add(entity);
                } while (cursor.moveToNext());
            }
            return list;
        }
    }

    void update(T entity, String tableName, String condition) throws Exception {
        ContentValues contentValues = transformEntityToValues(entity);
        try (SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase()) {
            db.beginTransaction();
            long updateRaw = db.update(tableName, contentValues, condition, getArgs(entity));
            if (updateRaw == -1) {
                throw new Exception();
            } else {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
        }
    }

    protected abstract T transformCursorToEntity(Cursor cursor) throws Exception;
    protected abstract ContentValues transformEntityToValues(T entity) throws Exception;
    protected abstract void updateEntityById(T entity, long rowId) throws Exception;
    protected abstract String[] getArgs(T entity);

}

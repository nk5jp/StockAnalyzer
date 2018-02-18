package jp.nk5.stockanalyzer.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance = null;
    static final String DB_NAME = "nk5_stockanalyzer.db";
    static final int DB_VERSION = 1;

    static final String CREATE_STOCK_TABLE = "create table stock ( " +
            "code integer primary key not null, " +
            "name text not null );";

    static final String DROP_STOCK_TABLE = "drop table stock;";

    public static DBHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STOCK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_STOCK_TABLE);
        sqLiteDatabase.execSQL(CREATE_STOCK_TABLE);
    }
}

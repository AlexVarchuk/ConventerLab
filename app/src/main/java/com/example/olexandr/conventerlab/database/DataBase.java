package com.example.olexandr.conventerlab.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBase extends SQLiteOpenHelper implements BaseColumns {
    public static final String DATABASE_NAME = "mydata.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE_ORGANIZATION = "Organizations";
    public static final String ORGANIZATION_COLUMN = "name";
    public static final String ADDRESS_COLUMN = "address";
    public static final String REGION_COLUMN = "region";
    public static final String PHONE_COLUMN = "number";
    public static final String LINK_COLUMN = "url";
    public static final String CITY_COLUMN = "city";


    public static final String DATABASE_CREATE_SCRIPT_ORGANIZATION = "CREATE TABLE "
            + DATABASE_TABLE_ORGANIZATION + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORGANIZATION_COLUMN
            + " TEXT, " + REGION_COLUMN + " TEXT, " + CITY_COLUMN
            + " TEXT, " + ADDRESS_COLUMN + " TEXT, " + PHONE_COLUMN
            + " INT, " + LINK_COLUMN + " TEXT); ";


    public static final String DATABASE_TABLE_DATA = "Data";

    public static final String DATA_COLUMN = "data";

    public static final String DATABASE_CREATE_SCRIPT_DATA = "CREATE TABLE "
            + DATABASE_TABLE_DATA + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATA_COLUMN
            + " TEXT); ";


    public static final String DATABASE_TABLE_CURRENCIES = "Currencies";

    public static final String ORGANIZATION_NAME_COLUMN = "organization";
    public static final String CURRENCY_NAME_COLUMN = "currency";
    public static final String BUY_COLUMN = "buy";
    public static final String SELL_COLUMN = "sell";
    public static final String CURRENCY_FULL_NAME_COLUM = "fullCurrencyName";


    public static final String DATABASE_CREATE_SCRIPT_CURRENCIES = "CREATE TABLE "
            + DATABASE_TABLE_CURRENCIES + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORGANIZATION_NAME_COLUMN
            + " TEXT, " + BUY_COLUMN + " INT, " + CURRENCY_FULL_NAME_COLUM + " INT, "
            + CURRENCY_NAME_COLUMN + " INT, " + SELL_COLUMN + " INT);";


    public static final String DATABASE_TABLE_CURRENCIES_OLD = "oldCurrencies";

    public static final String ORGANIZATION_NAME_OLD_COLUMN = "oldOrganization";
    public static final String CURRENCY_NAME_OLD_COLUMN = "oldCurrency";
    public static final String BUY_OLD_COLUMN = "oldBuy";
    public static final String SELL_OLD_COLUMN = "oldSell";
    public static final String CURRENCY_FULL_OLD_NAME_COLUM = "oldFullCurrencyName";


    public static final String DATABASE_CREATE_SCRIPT_CURRENCIES_OLD = "CREATE TABLE "
            + DATABASE_TABLE_CURRENCIES_OLD + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORGANIZATION_NAME_OLD_COLUMN
            + " TEXT, " + BUY_OLD_COLUMN + " INT, " + CURRENCY_FULL_OLD_NAME_COLUM + " INT, "
            + CURRENCY_NAME_OLD_COLUMN + " INT, " + SELL_OLD_COLUMN + " INT);";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT_ORGANIZATION);
        db.execSQL(DATABASE_CREATE_SCRIPT_DATA);
        db.execSQL(DATABASE_CREATE_SCRIPT_CURRENCIES);
        db.execSQL(DATABASE_CREATE_SCRIPT_CURRENCIES_OLD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CURRENCIES);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ORGANIZATION);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_SCRIPT_CURRENCIES_OLD);

        onCreate(db);

    }
}

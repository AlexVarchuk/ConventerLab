package com.example.olexandr.conventerlab.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.olexandr.conventerlab.database.DataBase;
import com.example.olexandr.conventerlab.model.JsonModel;
import com.example.olexandr.conventerlab.model.JsonModelData;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class BackgroundService extends Service {
    private JsonModel mJsonModel;
    private String mUrl = "http://resources.finance.ua/ru/public/currency-cash.json";
    private Gson mGson;
    private AsyncHttpClient mClient;
    private SQLiteDatabase mSQLiteDatabase;
    private DataBase mBase;
    private Cursor mCursor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBase = new DataBase(BackgroundService.this, "mydata.db", null, 1);
        startServiceForeground();
        Log.i("kuku", "startServise");
        return super.onStartCommand(intent, flags, startId);
    }


    private void startServiceForeground() {


        mClient = new AsyncHttpClient();
        mClient.get(BackgroundService.this, mUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resr = new String(responseBody);
                mSQLiteDatabase = mBase.getReadableDatabase();

                Gson mGsonData = new Gson();
                JsonModelData modelData = mGsonData.fromJson(resr, JsonModelData.class);
                String date = modelData.getDate();

                Cursor cursor = mSQLiteDatabase.query(DataBase.DATABASE_TABLE_DATA, new String[]{
                        DataBase.DATA_COLUMN,}, null, null, null, null, null);
                cursor.moveToNext();



                if (cursor.getString(cursor.getColumnIndex(DataBase.DATA_COLUMN)).equals(date)) {
                    Log.i("dateee", String.valueOf(true)+date);
                } else {

                    copyCurrencyColumn();

                    parseJsonDataAndWriteTODataBase(resr, date);

                    parseJsonCurrencyAndWriteToDataBase(resr);


                    parseJsonOrganizationsAndWriteToDataBase(resr);
                    Log.i("dateee", "date!=date");

                }



                Log.d("kuku", "vsjo");

            }

            public void copyCurrencyColumn(){
                mSQLiteDatabase = mBase.getReadableDatabase();
                mSQLiteDatabase = mBase.getWritableDatabase();
                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_CURRENCIES_OLD, null, null);

                mCursor = mSQLiteDatabase.query(DataBase.DATABASE_TABLE_CURRENCIES, new String[]{
                                DataBase.CURRENCY_FULL_NAME_COLUM, DataBase.BUY_COLUMN,
                                DataBase.SELL_COLUMN, DataBase.ORGANIZATION_NAME_COLUMN},
                        null, null, null, null, null);
                while (mCursor.moveToNext()) {

                    String organization = mCursor.getString(mCursor.getColumnIndex(DataBase.ORGANIZATION_NAME_COLUMN));
                    String currency = mCursor.getString(mCursor.getColumnIndex(DataBase.CURRENCY_FULL_NAME_COLUM));
                    String buy = mCursor.getString(mCursor.getColumnIndex(DataBase.BUY_COLUMN));
                    String sell = mCursor.getString(mCursor.getColumnIndex(DataBase.SELL_COLUMN));

                    ContentValues contentCurrencyOld = new ContentValues();
                    contentCurrencyOld.put(DataBase.CURRENCY_NAME_OLD_COLUMN, currency);
                    contentCurrencyOld.put(DataBase.BUY_OLD_COLUMN, buy);
                    contentCurrencyOld.put(DataBase.SELL_OLD_COLUMN, sell);
                    contentCurrencyOld.put(DataBase.ORGANIZATION_NAME_OLD_COLUMN, organization);
                    mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_CURRENCIES_OLD, null, contentCurrencyOld);

                }
            }

            private void parseJsonCurrencyAndWriteToDataBase(String resr) {

                mSQLiteDatabase = mBase.getWritableDatabase();
                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_CURRENCIES, null, null);

                try {
                    JSONObject json = new JSONObject(resr);

                    // process organizations
                    if (json.has("organizations")) {
                        if (!json.isNull("organizations")) {
                            JSONArray orgs = (JSONArray) json.get("organizations");
                            int len = orgs.length();
                            for (int i = 0; i < len; i++) {
                                String organization_name = "";
                                String curr_name = "";
                                String curr_ask = "";
                                String curr_bid = "";

                                JSONObject org = orgs.getJSONObject(i);

                                // process organization name
                                if (org.has("title") && !org.isNull(("title"))) {
                                    organization_name = org.getString("title");
                                }
                                // process currencies
                                if (org.has("currencies") && !org.isNull("currencies")) {
                                    JSONObject curr = (JSONObject) org.getJSONObject("currencies");
                                    // JSONArray curr_names = curr.names();
                                    Iterator iter = curr.keys();
                                    while (iter.hasNext()) {
                                        curr_name = (String) iter.next();
                                        JSONObject askbid = (JSONObject) curr.get(curr_name);
                                        if (askbid.has("ask") && !askbid.isNull(("ask"))) {
                                            curr_ask = askbid.getString("ask");
                                        }
                                        if (askbid.has("bid") && !askbid.isNull(("bid"))) {
                                            curr_bid = askbid.getString("bid");
                                        }
                                        Log.i("kuku", i + ": " + organization_name + ": " + curr_name + "= " + curr_ask + " | " + curr_bid);

                                        Gson mGsonCurrency = new Gson();

                                        mJsonModel = mGsonCurrency.fromJson(resr, JsonModel.class);




                                        ContentValues contentCurrency = new ContentValues();
                                        contentCurrency.put(DataBase.ORGANIZATION_NAME_COLUMN, organization_name);
                                        contentCurrency.put(DataBase.BUY_COLUMN, curr_bid);
                                        contentCurrency.put(DataBase.SELL_COLUMN, curr_ask);
                                        contentCurrency.put(DataBase.CURRENCY_NAME_COLUMN, curr_name);

                                        String value = "";
                                        for (String currencies : mJsonModel.getCurrencies().keySet()) {
                                            if (curr_name.equals(currencies)) {
                                                value = (String) mJsonModel.getCurrencies().get(currencies);
                                                contentCurrency.put(DataBase.CURRENCY_FULL_NAME_COLUM, value);

                                            }
                                        }
                                        Log.i("tutu", value);
                                        mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_CURRENCIES, null, contentCurrency);


                                    }
                                }
                            }
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            private void parseJsonDataAndWriteTODataBase(String date, String resr){

               mSQLiteDatabase = mBase.getWritableDatabase();
                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_DATA, null, null);
                ContentValues contentData = new ContentValues();
                contentData.put(DataBase.DATA_COLUMN, date);
                mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_DATA,null, contentData);


            }

            private void parseJsonOrganizationsAndWriteToDataBase(String resr) {

                mSQLiteDatabase = mBase.getWritableDatabase();


                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_ORGANIZATION, null, null);
                mGson = new Gson();

                mJsonModel = mGson.fromJson(resr, JsonModel.class);

                for (int i = 0; i < mJsonModel.getOrganizations().size(); i++) {
                    String mOrganizationName = mJsonModel.getOrganizations().get(i).getTitle();
                    String mAddressOrganization = mJsonModel.getOrganizations().get(i).getAddress();
                    String mRegion = mJsonModel.getOrganizations().get(i).getRegionId();
                    String mPhone = mJsonModel.getOrganizations().get(i).getPhone();
                    String mLink = mJsonModel.getOrganizations().get(i).getLink();
                    String mCity = mJsonModel.getOrganizations().get(i).getCityId();


                    ContentValues contentOrganization = new ContentValues();
                    contentOrganization.put(DataBase.ORGANIZATION_COLUMN, mOrganizationName);
                    contentOrganization.put(DataBase.ADDRESS_COLUMN, mAddressOrganization);
                    contentOrganization.put(DataBase.PHONE_COLUMN, mPhone);
                    contentOrganization.put(DataBase.LINK_COLUMN, mLink);

                    String r = "";
                    String c = "";
                    for (String regions : mJsonModel.getRegions().keySet()) {
                        if (mRegion.equals(regions)) {
                            r = (String) mJsonModel.getRegions().get(regions);
                            contentOrganization.put(DataBase.REGION_COLUMN, r);
                        }
                    }
                    Log.i("RURU", r);

                    for (String cities : mJsonModel.getCities().keySet()) {


                        if (mCity.equals(cities)) {
                            c = (String) mJsonModel.getCities().get(cities);
                            contentOrganization.put(DataBase.CITY_COLUMN, c);

                        }
                        Log.i("RURU", c);


//                    Log.i("ruru", "key :"+cities+ "value :"+ mJsonModel.getCities().get(cities));
                    }
                    mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_ORGANIZATION, null, contentOrganization);
                }


            }


            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("kuku", "hujna");
            }


        });


    }
}

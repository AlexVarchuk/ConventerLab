package com.example.olexandr.conventerlab.activity;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.database.DataBase;
import com.example.olexandr.conventerlab.fragment.FragmentHome;
import com.example.olexandr.conventerlab.model.JsonModel;
import com.example.olexandr.conventerlab.model.JsonModelData;
import com.example.olexandr.conventerlab.service.BackgroundService;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements android.support.v7.widget.SearchView.OnQueryTextListener {


    private JsonModel mJsonModel;
    private String mUrl = "http://resources.finance.ua/ru/public/currency-cash.json";
    private Gson mGson;
    private AsyncHttpClient mClient;

    private DataBase mBase;
    private SQLiteDatabase mSQLiteDatabase;
    private FragmentHome mFragmentHome;
    private Cursor mCursor;
    private android.support.v4.app.NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 1234;


    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentHome = new FragmentHome();
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_AM);
        setSupportActionBar(mToolbar);

        mBase = new DataBase(MainActivity.this, "mydata.db", null, 1);

        startWriteToBase();


    }

    private void startWriteToBase() {
        if (checkInternetConnection()) {

            parseJsonAndWriteToDataBase();

        } else {

            setFragment();

        }
    }

    private void parseJsonAndWriteToDataBase() {

        mClient = new AsyncHttpClient();
        mClient.get(MainActivity.this, mUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respBody = new String(responseBody);

                startNotification();

                copyCurrencyColumn();

                parseJsonDataAndWriteToDataBase(respBody);

                parseJsonCurrencyAndWriteToDataBase(respBody);

                parseJsonOrganizationsAndWriteToDataBase(respBody);
                setFragment();
                Log.d("kuku", "vsjo");

            }

            public void startNotification() {

                mBuilder = new android.support.v4.app.NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_upload))
                        .setContentTitle("Update Database")
                        .setContentText("0%")
                        .setAutoCancel(true)
                        .setProgress(100, 0, false)
                        .setSound(Uri.parse("android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.data));

                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

            }

            public void copyCurrencyColumn() {

                mSQLiteDatabase = mBase.getReadableDatabase();
                mSQLiteDatabase = mBase.getWritableDatabase();

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
                    contentCurrencyOld.put(DataBase.CURRENCY_FULL_OLD_NAME_COLUM, currency);
                    contentCurrencyOld.put(DataBase.BUY_OLD_COLUMN, buy);
                    contentCurrencyOld.put(DataBase.SELL_OLD_COLUMN, sell);
                    contentCurrencyOld.put(DataBase.ORGANIZATION_NAME_OLD_COLUMN, organization);
                    mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_CURRENCIES_OLD, null, contentCurrencyOld);


                    mBuilder.setProgress(100, 5, false);
                    mBuilder.setContentText("5%");
                    Notification notification = mBuilder.build();
                    mNotificationManager.notify(NOTIFICATION_ID, notification);
                }
            }

            private void parseJsonDataAndWriteToDataBase(String respBody) {
                Gson mGsonData = new Gson();
                JsonModelData modelData = mGsonData.fromJson(respBody, JsonModelData.class);
                String date = modelData.getDate();

                mSQLiteDatabase = mBase.getWritableDatabase();
                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_DATA, null, null);

                ContentValues contentData = new ContentValues();
                contentData.put(DataBase.DATA_COLUMN, date);
                mSQLiteDatabase.insert(DataBase.DATABASE_TABLE_DATA, null, contentData);

                mBuilder.setProgress(100, 20, false);
                mBuilder.setContentText("20%");
                Notification notification = mBuilder.build();
                mNotificationManager.notify(NOTIFICATION_ID, notification);

            }

            private void parseJsonCurrencyAndWriteToDataBase(String respBody) {

                mSQLiteDatabase = mBase.getWritableDatabase();
                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_CURRENCIES, null, null);

                try {
                    JSONObject json = new JSONObject(respBody);

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

                                        mJsonModel = mGsonCurrency.fromJson(respBody, JsonModel.class);


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
                mBuilder.setProgress(100, 40, false);
                mBuilder.setContentText("40%");
                Notification notification = mBuilder.build();
                mNotificationManager.notify(NOTIFICATION_ID, notification);

            }

            private void parseJsonOrganizationsAndWriteToDataBase(String respBody) {

                mSQLiteDatabase = mBase.getWritableDatabase();


                mSQLiteDatabase.delete(DataBase.DATABASE_TABLE_ORGANIZATION, null, null);
                mGson = new Gson();

                mJsonModel = mGson.fromJson(respBody, JsonModel.class);

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

                    mBuilder.setProgress(100, 60, false);
                    mBuilder.setContentText("60%");
                    Notification notification = mBuilder.build();
                    mNotificationManager.notify(NOTIFICATION_ID, notification);

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
                mBuilder.setProgress(100, 100, false);
                mBuilder.setContentText("100%");
                Notification notification = mBuilder.build();
                mNotificationManager.notify(NOTIFICATION_ID, notification);

            }


            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("kuku", "stop");
            }


        });

    }


    public boolean checkInternetConnection() {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        NetworkInfo networkInfoWifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfoMobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isConnected() || networkInfoWifi != null && networkInfoWifi.isConnected() || networkInfoMobile != null && networkInfoMobile.isConnected()) {
//            Log.i("internet", "Connected: " + networkInfo.getTypeName() + '\n' + networkInfoWifi.isConnected() + '\n' + networkInfoMobile.isConnected());
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BackgroundService.class);
        stopService(intent);
        Log.i("kuku", "stopServise");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Intent intent = new Intent(MainActivity.this, BackgroundService.class);
        PendingIntent pendintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1800 * 1000, pendintent);

    }

    private void setFragment() {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.ll_forFragment, mFragmentHome, FragmentHome.TAG);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}

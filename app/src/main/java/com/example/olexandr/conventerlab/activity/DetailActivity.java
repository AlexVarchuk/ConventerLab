package com.example.olexandr.conventerlab.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.adapter.CurrencyAdapter;
import com.example.olexandr.conventerlab.database.DataBase;
import com.example.olexandr.conventerlab.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView mOrganization;
    private TextView mLink;
    private TextView mAddress;
    private TextView mNumber;
    private TextView mRegion;
    private TextView mCity;
    private ListView mCurrencies;


    private String mNameOrganization;
    private String mCityOrganization;
    private String mRegionOrganization;
    private String mPhoneOrganization;
    private String mAddressOrganization;
    private String mLinkOrganization;

    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private Toolbar mToolbar;


    private List<Currency> mData = new ArrayList<>();
    private CurrencyAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_DI);
        setSupportActionBar(mToolbar);


        getOrganizationParameter();
        setTextOrganizationParameter();

        DataBase mBase = new DataBase(DetailActivity.this, "mydata.db", null, 1);
        mSQLiteDatabase = mBase.getReadableDatabase();

        mAdapter = new CurrencyAdapter(this, mData);
        mCurrencies.setAdapter(mAdapter);

        loadListView();


    }


    private void loadListView() {
        mData.clear();
        mData.addAll(readDataBaseAndSetList());
        mAdapter.notifyDataSetChanged();
    }

    private List<Currency> readDataBaseAndSetList() {

        List<Currency> listView = new ArrayList<>();
        mCursor = mSQLiteDatabase.query(DataBase.DATABASE_TABLE_CURRENCIES, new String[]{
                        DataBase.CURRENCY_FULL_NAME_COLUM, DataBase.BUY_COLUMN,
                        DataBase.SELL_COLUMN, DataBase.ORGANIZATION_NAME_COLUMN},
                null, null, null, null, null);


        while (mCursor.moveToNext()) {

            if (mCursor.getString(mCursor.getColumnIndex(DataBase.ORGANIZATION_NAME_COLUMN)).equals(mNameOrganization)) {

                String currency = mCursor.getString(mCursor.getColumnIndex(DataBase.CURRENCY_FULL_NAME_COLUM));
                String buy = mCursor.getString(mCursor.getColumnIndex(DataBase.BUY_COLUMN));
                String sell = mCursor.getString(mCursor.getColumnIndex(DataBase.SELL_COLUMN));
                listView.add(new Currency(currency, buy, sell, mNameOrganization));

            }

        }
        return listView;
    }

    private void getOrganizationParameter() {
        Intent intent = getIntent();
        mNameOrganization = intent.getStringExtra("name");
        mCityOrganization = intent.getStringExtra("city");
        mRegionOrganization = intent.getStringExtra("region");
        mPhoneOrganization = intent.getStringExtra("phone");
        mAddressOrganization = intent.getStringExtra("address");
        mLinkOrganization = intent.getStringExtra("link");

        Log.i("llll", mNameOrganization);
    }

    private void setTextOrganizationParameter() {
        mOrganization.setText(mNameOrganization);
        mLink.setText("Страничка огранизации: " + mLinkOrganization);
        mAddress.setText("Адреса: " + mAddressOrganization);
        mNumber.setText("Телефон: " + "+30" + mPhoneOrganization);
        mCity.setText("Город: " + mCityOrganization);
        mRegion.setText(mRegionOrganization);
    }

    private void findViews() {
        mOrganization = (TextView) findViewById(R.id.tv_name_bank_AL);
        mLink = (TextView) findViewById(R.id.tv_site_bank_AL);
        mAddress = (TextView) findViewById(R.id.tv_address_bank_AL);
        mNumber = (TextView) findViewById(R.id.tv_phone_first_AL);
        mCurrencies = (ListView) findViewById(R.id.lv_value_AL);
        mCity = (TextView) findViewById(R.id.tv_city_bank_AL);
        mRegion = (TextView) findViewById(R.id.tv_region_bank_AL);


    }


    public void onFabMapClick(View v) {
        Intent myIntent = new Intent(v.getContext(), MapActivity.class);
        String address = mCityOrganization + ", " + mAddressOrganization;
        myIntent.putExtra("addressOrganization", address);
        myIntent.putExtra("nameOrganization", mNameOrganization);
        v.getContext().startActivity(myIntent);
    }

    public void onFabLinkClick(View v) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mLinkOrganization));
        v.getContext().startActivity(i);
    }

    public void onFabCallClick(View v) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+30" + mPhoneOrganization));
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
//            Bundle bundle = new Bundle();
//            bundle.putString("bankShare", mNameOrganization);
//            bundle.putString("cityShare", mCityOrganization);
//            bundle.putString("regionShare", mRegionOrganization);
//            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//            sharingIntent.setType("text/html");
//            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Курс валют");
//            startActivity(Intent.createChooser(sharingIntent,"Заголовок окна"));

            getWholeListViewItemsToBitmapAndShowDialog();
//            setDialog(bigbitmap);

        }

        return super.onOptionsItemSelected(item);
    }

//    private void setDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialoglayout);
//        ImageView imgView=(ImageView)dialog.findViewById(R.id.iv_DL);
//        imgView.setImageBitmap(bigbitmap);
//        dialog.show();
//    }

    public Bitmap getWholeListViewItemsToBitmapAndShowDialog() {

        ListView listview    = mCurrencies;
        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();

        for (int i = 0; i < itemscount; i++) {

            View childView = adapter.getView(i, null, listview);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight+=childView.getMeasuredHeight();
        }

        final Bitmap bigbitmap    = Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas    = new Canvas(bigbitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight+=bmp.getHeight();

            bmp.recycle();
            bmp=null;
        }

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setTitle("Рассказать");
        ImageView imgView=(ImageView)dialog.findViewById(R.id.iv_DL);
        TextView tvBank = (TextView) dialog.findViewById(R.id.tv_bank_DL);
        TextView tvCity = (TextView) dialog.findViewById(R.id.tv_city_DL);
        TextView tvRegion = (TextView) dialog.findViewById(R.id.tv_region_DL);
        imgView.setImageBitmap(bigbitmap);
        tvBank.setText(mNameOrganization);
        tvCity.setText(mCityOrganization);
        tvRegion.setText(mRegionOrganization);

        Button share = (Button) dialog.findViewById(R.id.btn_share_DL);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("img/png");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, bigbitmap);
            startActivity(Intent.createChooser(sharingIntent,"Курс Валют"));
        }
        });
        dialog.show();
        return bigbitmap;
    }

}

package com.example.olexandr.conventerlab.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.adapter.OrganizationRecyclerAdapter;
import com.example.olexandr.conventerlab.database.DataBase;
import com.example.olexandr.conventerlab.model.ItemBankModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements android.support.v7.widget.SearchView.OnQueryTextListener {

    private RecyclerView mBankList;
    private List<ItemBankModel> mListBank;
    private OrganizationRecyclerAdapter mAdapterBankList;
    RecyclerView.LayoutManager mLayoutManager;
    Cursor mCursor;
    SQLiteDatabase mSQLiteDatabase;
    DataBase mBase;


    public static String TAG = "FragmentHome";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mListBank = new ArrayList<>();
        mBase = new DataBase(getActivity(), "mydata.db", null, 1);
        mSQLiteDatabase = mBase.getReadableDatabase();

        mBankList = (RecyclerView) view.findViewById(R.id.rv_FH);
        mBankList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mBankList.setLayoutManager(mLayoutManager);


        mAdapterBankList = new OrganizationRecyclerAdapter(getActivity(), mListBank);
        mBankList.setAdapter(mAdapterBankList);

        loadList();

        return view;
    }

    private void loadList() {
        mListBank.clear();
        mListBank.addAll(setList());
        mAdapterBankList.notifyDataSetChanged();

    }

    private List<ItemBankModel> setList() {

        List<ItemBankModel> list = new ArrayList<>();
        mCursor = mSQLiteDatabase.query(DataBase.DATABASE_TABLE_ORGANIZATION, new String[]{
                DataBase.CITY_COLUMN, DataBase.ADDRESS_COLUMN, DataBase.PHONE_COLUMN, DataBase.ORGANIZATION_COLUMN,
                DataBase.REGION_COLUMN, DataBase.LINK_COLUMN,
        }, null, null, null, null, null);
        while (mCursor.moveToNext()) {
            String city = mCursor.getString(mCursor.getColumnIndex(DataBase.CITY_COLUMN));
            String address = mCursor.getString(mCursor.getColumnIndex(DataBase.ADDRESS_COLUMN));
            String phone = mCursor.getString(mCursor.getColumnIndex(DataBase.PHONE_COLUMN));
            String name = mCursor.getString(mCursor.getColumnIndex(DataBase.ORGANIZATION_COLUMN));
            String region = mCursor.getString(mCursor.getColumnIndex(DataBase.REGION_COLUMN));
            String link = mCursor.getString(mCursor.getColumnIndex(DataBase.LINK_COLUMN));

            list.add(new ItemBankModel(city, address, phone, name, region, link));
        }
        return list;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    String temp = "";

    @Override
    public boolean onQueryTextChange(String query) {

        if (query.length() > temp.length()) {
            final List<ItemBankModel> filteredModelList = filter(mListBank, query);
            mAdapterBankList.animateTo(filteredModelList);
            mBankList.scrollToPosition(0);
            temp = query;

        } else if (query.length() <= 0) {
            mListBank.addAll(setList());
            mAdapterBankList.notifyDataSetChanged();
        } else {
            final List<ItemBankModel> filteredModelList = filter(mListBank, query);
            mAdapterBankList.animateTo(filteredModelList);
            mBankList.scrollToPosition(0);
        }

        return true;


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<ItemBankModel> filter(List<ItemBankModel> models, String query) {
        query = query.toLowerCase();


        List<ItemBankModel> filteredModelList = new ArrayList<>();
        for (ItemBankModel model : models) {
            String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        List<ItemBankModel> filteredModelList1 = new ArrayList<>();
        for (ItemBankModel model : models) {
            String text = model.getAddress().toLowerCase();
            if (text.contains(query)) {
                filteredModelList1.add(model);
            }
        }
        List<ItemBankModel> filteredModelList2 = new ArrayList<>();
        for (ItemBankModel model : models) {
            String text = model.getRegion().toLowerCase();
            if (text.contains(query)) {
                filteredModelList2.add(model);
            }
        }

        return filteredModelList;
    }

}

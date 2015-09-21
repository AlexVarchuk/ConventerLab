package com.example.olexandr.conventerlab.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.database.DataBase;
import com.example.olexandr.conventerlab.model.Currency;

import java.util.List;

public class CurrencyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Currency> mData;

    public CurrencyAdapter(Context _context, List<Currency> _values) {

        this.mContext = _context;
        this.mData = _values;

    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_value, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Currency item = mData.get(position);
        holder.mCurrency.setText(item.getCurrency());
        holder.mBuy.setText(item.getBuy());
        holder.mSell.setText(item.getSell());
        setImage(position, holder);


        return convertView;
    }

    private void setImage(int i, ViewHolder holder) {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase;
        DataBase mBase = new DataBase(mContext, "mydata.db", null, 1);
        sqLiteDatabase = mBase.getReadableDatabase();

        cursor = sqLiteDatabase.query(DataBase.DATABASE_TABLE_CURRENCIES_OLD, new String[]{
                        DataBase.CURRENCY_FULL_OLD_NAME_COLUM, DataBase.BUY_OLD_COLUMN,
                        DataBase.SELL_OLD_COLUMN, DataBase.ORGANIZATION_NAME_OLD_COLUMN},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            String organization = cursor.getString(cursor.getColumnIndex(DataBase.ORGANIZATION_NAME_OLD_COLUMN));
            String currency = cursor.getString(cursor.getColumnIndex(DataBase.CURRENCY_FULL_OLD_NAME_COLUM));
            String allOld = organization + currency;
            String organizationNow = mData.get(i).getOrganization();
            String currencyNow = mData.get(i).getCurrency();
            String allNow = organizationNow + currencyNow;
            if (allOld.equals(allNow)) {
                Double buyNow = Double.valueOf(mData.get(i).getBuy());
                Double sellNow = Double.valueOf(mData.get(i).getSell());
                Double buyOld = Double.valueOf(cursor.getString(cursor.getColumnIndex(DataBase.BUY_OLD_COLUMN)));
                Double sellOld = Double.valueOf(cursor.getString(cursor.getColumnIndex(DataBase.SELL_OLD_COLUMN)));
                if (buyNow < buyOld) {
                    holder.mImageBuy.setImageResource(R.drawable.ic_red_arrow_down);
                } else {
                    holder.mImageBuy.setImageResource(R.drawable.ic_green_arrow_up);
                }
                if (sellNow < sellOld) {
                    holder.mImageSell.setImageResource(R.drawable.ic_red_arrow_down);
                } else {
                    holder.mImageSell.setImageResource(R.drawable.ic_green_arrow_up);
                }


            }
        }

        sqLiteDatabase.delete(DataBase.DATABASE_TABLE_CURRENCIES_OLD, null, null);


    }

    private class ViewHolder {
        TextView mCurrency;
        TextView mBuy;
        TextView mSell;
        ImageView mImageBuy;
        ImageView mImageSell;

        public ViewHolder(View v) {
            mCurrency = (TextView) v.findViewById(R.id.tv_name_currency_LIV);
            mBuy = (TextView) v.findViewById(R.id.tv_buy_LIV);
            mSell = (TextView) v.findViewById(R.id.tv_sell_LIV);
            mImageBuy = (ImageView) v.findViewById(R.id.iv_buy_LIV);
            mImageSell = (ImageView) v.findViewById(R.id.iv_sell_LIV);
            return;
        }
    }
}
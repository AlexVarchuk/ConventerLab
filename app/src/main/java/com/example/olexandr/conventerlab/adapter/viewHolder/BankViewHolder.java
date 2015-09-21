package com.example.olexandr.conventerlab.adapter.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.activity.DetailActivity;
import com.example.olexandr.conventerlab.activity.MapActivity;
import com.example.olexandr.conventerlab.model.ItemBankModel;

public class BankViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    TextView tvBank;
    TextView tvRegion;
    TextView tvCity;
    TextView tvPhone;
    TextView tvAddress;
    ImageButton ibMap;
    ImageButton ibPhone;
    ImageButton ibNext;
    ImageButton ibLink;

    public BankViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cv);
        tvBank = (TextView) itemView.findViewById(R.id.tv_bank_LI);
        tvRegion = (TextView) itemView.findViewById(R.id.tv_region_LI);
        tvCity = (TextView) itemView.findViewById(R.id.tv_city_LI);
        tvPhone = (TextView) itemView.findViewById(R.id.tv_phone_LI);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_address_LI);
        ibPhone = (ImageButton) itemView.findViewById(R.id.ib_phone_LI);
        ibLink = (ImageButton) itemView.findViewById(R.id.ib_link_LI);
        ibMap = (ImageButton) itemView.findViewById(R.id.ib_map_LI);
        ibNext = (ImageButton) itemView.findViewById(R.id.ib_next_LI);
    }

    public void onBind(final ItemBankModel mBanks, final int i, final Context mContext) {
        tvBank.setText(mBanks.getName());
        tvAddress.setText(mBanks.getAddress());
        tvCity.setText(mBanks.getCity());
        tvRegion.setText(mBanks.getRegion());
        tvPhone.setText("+30" + mBanks.getPhone());
        ibMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MapActivity.class);
                String address = mBanks.getCity() + ", " + mBanks.getAddress();
                myIntent.putExtra("addressOrganization", address);
                myIntent.putExtra("nameOrganization", mBanks.getName());
                v.getContext().startActivity(myIntent);

            }
        });

        ibPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+30" + mBanks.getPhone()));
                mContext.startActivity(intent);
            }
        });
        ibLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mBanks.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);


            }
        });
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailActivity.class);
                myIntent.putExtra("name", mBanks.getName());
                myIntent.putExtra("city", mBanks.getCity());
                myIntent.putExtra("region", mBanks.getRegion());
                myIntent.putExtra("address", mBanks.getAddress());
                myIntent.putExtra("phone", mBanks.getPhone());
                myIntent.putExtra("link", mBanks.getLink());

                v.getContext().startActivity(myIntent);
            }
        });
    }
}
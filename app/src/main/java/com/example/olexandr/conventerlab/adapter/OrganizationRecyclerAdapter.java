package com.example.olexandr.conventerlab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olexandr.conventerlab.R;
import com.example.olexandr.conventerlab.adapter.viewHolder.BankViewHolder;
import com.example.olexandr.conventerlab.model.ItemBankModel;

import java.util.List;

public class OrganizationRecyclerAdapter extends RecyclerView.Adapter<BankViewHolder> {

    List<ItemBankModel> mBanks;
    Context mContext;


    public OrganizationRecyclerAdapter(Context context, List<ItemBankModel> mListBank) {
        mContext = context;
        mBanks = mListBank;
    }

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_bank, viewGroup, false);
        BankViewHolder bank = new BankViewHolder(v);
        return bank;
    }

    @Override
    public void onBindViewHolder(com.example.olexandr.conventerlab.adapter.viewHolder.BankViewHolder holder, int position) {

        ItemBankModel model = mBanks.get(position);
        holder.onBind(model, position, mContext);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mBanks.size();
    }

    public void animateTo(List<ItemBankModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ItemBankModel> newModels) {
        for (int i = mBanks.size() - 1; i >= 0; i--) {
            ItemBankModel model = mBanks.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ItemBankModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ItemBankModel model = newModels.get(i);
            if (!mBanks.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ItemBankModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ItemBankModel model = newModels.get(toPosition);
            final int fromPosition = mBanks.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ItemBankModel removeItem(int position) {
        final ItemBankModel model = mBanks.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ItemBankModel model) {
        mBanks.add(position, model);
        notifyItemInserted(position);
    }


    public void moveItem(int fromPosition, int toPosition) {
        final ItemBankModel model = mBanks.remove(fromPosition);
        mBanks.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


}

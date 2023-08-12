package org.ashcode.mandatahack23;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRVExistTables extends RecyclerView.Adapter<AdapterRVExistTables.ViewHolder> {
    private ExistingTableNamesResponseModel tableNames;
    private OnItemClickListener listener;

    public AdapterRVExistTables(ExistingTableNamesResponseModel tableNames, OnItemClickListener listener) {
        this.tableNames = tableNames;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tablename, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tName = tableNames.getTable_names().get(position);
        holder.bind(tName, listener);
    }

    @Override
    public int getItemCount() {
        if (tableNames == null)  {
            return 0;
        }
        return tableNames.getTable_names().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView existTableNameTVItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            existTableNameTVItem = itemView.findViewById(R.id.existTableNameTVItem);
        }

        public void bind(final String tName, final OnItemClickListener listener) {
            existTableNameTVItem.setText(tName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tName);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String tName);
    }
}
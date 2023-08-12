package org.ashcode.mandatahack23;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRVColumnsTDDL extends RecyclerView.Adapter<AdapterRVColumnsTDDL.ViewHolder> {
    private ColumnsDD columns;
    private OnItemClickListener listener;

    public AdapterRVColumnsTDDL(ColumnsDD columns, OnItemClickListener listener) {
        this.columns = columns;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_column_tddl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SingleColumnD column = columns.getColumns().get(position);
        holder.bind(column, listener);
    }

    @Override
    public int getItemCount() {
        return columns.getColumns().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView datatypeTVTDDLItem,colNmTVTDDLItem,constraintsTVTDDLItem;
        private Button delBtnColTDDLItem,updateBtnColTDDLItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            datatypeTVTDDLItem = itemView.findViewById(R.id.datatypeTVTDDLItem);
            colNmTVTDDLItem = itemView.findViewById(R.id.colNmTVTDDLItem);
            constraintsTVTDDLItem = itemView.findViewById(R.id.constraintsTVTDDLItem);
            delBtnColTDDLItem = itemView.findViewById(R.id.delBtnColTDDLItem);
            updateBtnColTDDLItem = itemView.findViewById(R.id.updateBtnColTDDLItem);
        }

        public void bind(final SingleColumnD column, final OnItemClickListener listener) {
            datatypeTVTDDLItem.setText(column.getDataType());
            colNmTVTDDLItem.setText(column.getClmnName());
            constraintsTVTDDLItem.setText(column.getConstraint());
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(data);
                }
            });*/
            delBtnColTDDLItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(column);
                }
            });
            updateBtnColTDDLItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpdateClick(column);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onDeleteClick(SingleColumnD column);
        void onUpdateClick(SingleColumnD column);
    }
}

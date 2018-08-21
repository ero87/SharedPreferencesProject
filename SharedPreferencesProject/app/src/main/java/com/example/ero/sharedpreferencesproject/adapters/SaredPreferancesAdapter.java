package com.example.ero.sharedpreferencesproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ero.sharedpreferencesproject.R;
import com.example.ero.sharedpreferencesproject.models.Model;

import java.util.List;

public class SaredPreferancesAdapter extends RecyclerView.Adapter<SaredPreferancesAdapter.ViewHolder> {

    private Context context;
    private List<Model> list;

    public SaredPreferancesAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SaredPreferancesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SaredPreferancesAdapter.ViewHolder holder, int position) {
        holder.key.setText(list.get(position).getKey());
        holder.value.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView key;
        private final TextView value;

        public ViewHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
        }
    }
}

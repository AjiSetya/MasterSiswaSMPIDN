package com.blogspot.blogsetyaaji.mastersiswa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HashMap<String, String>> list_data;

    SiswaAdapter(FragmentActivity activity, ArrayList<HashMap<String, String>> listDataSiswa) {
        context = activity;
        list_data = listDataSiswa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.siswa_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.showDataAtRow(list_data.get(i));
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_namasiswa;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_namasiswa = itemView.findViewById(R.id.item_namasiswa);
        }

        void showDataAtRow(HashMap<String, String> stringStringHashMap) {
            item_namasiswa.setText(stringStringHashMap.get("nama"));
        }
    }
}

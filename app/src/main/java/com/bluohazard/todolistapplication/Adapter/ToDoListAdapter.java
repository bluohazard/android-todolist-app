package com.bluohazard.todolistapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.todolistapplication.Model.ToDoList;
import com.bluohazard.todolistapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends
        RecyclerView.Adapter<ToDoListAdapter.DaftarViewHolder> {
    private List<ToDoList> listToDo = new ArrayList<>();

    public ToDoListAdapter(List<ToDoList> listToDo) {
        this.listToDo = listToDo;
    }

    private OnDaftarClickListener listener;

    public interface OnDaftarClickListener {
        void onClick(View view, int position);
    }

    public void setListener(OnDaftarClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DaftarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_list, viewGroup, false);
        DaftarViewHolder viewHolder = new DaftarViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarViewHolder listViewHolder, int i) {

        ToDoList item = listToDo.get(i);
        listViewHolder.txtNamaKegiatan.setText(item.getNamaKegiatan());
        listViewHolder.txtKeterangan.setText(item.getKeterangan());
        listViewHolder.txtWaktu.setText(item.getWaktu());
    }

    @Override
    public int getItemCount() {
        return listToDo.size();
    }

    public class DaftarViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaKegiatan, txtKeterangan, txtWaktu;

        public DaftarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaKegiatan = itemView.findViewById(R.id.txtNamaKegiatan);
            txtKeterangan = itemView.findViewById(R.id.txtKeteranganSingkat);
            txtWaktu = itemView.findViewById(R.id.txtWaktuKegiatan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}

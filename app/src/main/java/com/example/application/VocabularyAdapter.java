package com.example.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder> {
    List<Vocabulary> vocabularies;
    Context context;
    private OnItemClicked onClick;

    public VocabularyAdapter(Runnable mainActivity, List<Vocabulary> Vocabularies) {
        vocabularies = Vocabularies;
    }

    public  List<Vocabulary>getVocabularies(){return vocabularies;}

    public  void setVocabularies(List<Vocabulary> vocabularies){
        this.vocabularies = vocabularies;
    }

    @NonNull
    @Override
    public VocabularyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new VocabularyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, final int position) {
        holder.textViewVocabulary.setText(vocabularies.get(position).getVocabulary());
        holder.textViewMean.setText(vocabularies.get(position).getMean());

        //Sau khi add va show thanh cong, Delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemDeleteClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (vocabularies == null) {
            return 0;
        }
        return vocabularies.size();
    }

    class VocabularyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewVocabulary, textViewMean;
        LinearLayout item;

        Button btnDelete;
       // Button btnUpdate;


        public VocabularyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewVocabulary = itemView.findViewById(R.id.tvVocabulary);
            textViewMean = itemView.findViewById(R.id.tvMean);


            btnDelete = itemView.findViewById(R.id.btnDelete);
            //btnUpdate = itemView.findViewById(R.id.btnUpdate);
            item = itemView.findViewById(R.id.item);

        }
    }

    public interface OnItemClicked {
        void onItemDeleteClick(int position);
        //void onItemUpdateClick(int position);
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
    // ĐẾN ĐÂY LÀ ĐÃ ADD VÀ SHOW ĐƯỢC DATA

}

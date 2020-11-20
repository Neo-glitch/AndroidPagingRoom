package com.neo.mypagingwork.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.neo.mypagingwork.R;
import com.neo.mypagingwork.model.Word;

import java.util.List;
import java.util.Objects;

public class WordListAdapter extends PagedListAdapter<Word, WordListAdapter.WordViewHolder> {

    public WordListAdapter() {
        super(sItemCallback);
    }

    private static DiffUtil.ItemCallback<Word> sItemCallback = new DiffUtil.ItemCallback<Word>() {
        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
          Word currentWord = getItem(position);
          if(currentWord != null){
              holder.wordItemView.setText(currentWord.getWord());
          }
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}


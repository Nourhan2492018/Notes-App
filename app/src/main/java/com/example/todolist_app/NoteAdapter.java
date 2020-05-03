package com.example.todolist_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter <Note,NoteAdapter.NoteHolder> {
    //private List<Note> notes = new ArrayList<>();
    private onItemClicklistener listener;
    private static final DiffUtil.ItemCallback<Note>DIFF_CALLBACK=new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNoteID()==newItem.getNoteID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNoteTitle().equals(newItem.getNoteTitle())&&
                    oldItem.getNoteDescription().equals(newItem.getNoteDescription())&&
                    oldItem.getNotPriority()==newItem.getNotPriority();
        }
    };
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getNoteTitle());
        holder.textViewDesciption.setText(currentNote.getNoteDescription());
        holder.textViewpriority.setText(String.valueOf(currentNote.getNotPriority()));

    }

   /* @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }*/

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDesciption;
        private TextView textViewpriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDesciption = itemView.findViewById(R.id.text_view_description);
            textViewpriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if (listener!=null&&position!= RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(getItem(position));//notes.get(position)
                    }
                }
            });
        }

    }

    public interface onItemClicklistener {
        public void onItemClick(Note note);
    }

    public void setonItemClicklistener(onItemClicklistener listener)
    {
        this.listener=listener;

    }

}

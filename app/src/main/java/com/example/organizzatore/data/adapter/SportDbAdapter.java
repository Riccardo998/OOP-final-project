package com.example.organizzatore.data.adapter;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.organizzatore.R;
import com.example.organizzatore.data.contract.SportContract.SportEntry;


public class SportDbAdapter extends RecyclerView.Adapter<SportDbAdapter.SportDbViewHolder> {
    private final Context context;
    private Cursor mCursor;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        //quando clicco item oppure clicco delete
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public SportDbAdapter( Context context, Cursor cursor) {
        mCursor = cursor;
        this.context = context;
    }

    public static class SportDbViewHolder extends RecyclerView.ViewHolder {
        //nome, posizione e immagine delete
        public TextView mTextView1;
        public ImageView mDeleteImage;


        public SportDbViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView); //nom
            mDeleteImage = itemView.findViewById(R.id.delete);

            //quando clicco sull'item
             itemView.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            //quando clicco su elimina
            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }



    @Override
    public SportDbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        SportDbViewHolder evh = new SportDbViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(SportDbViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }
        //PRENDO ATTIVITA SQLITE E LA MOSTRO A DISPLAY
        String name = mCursor.getString(mCursor.getColumnIndex(SportEntry.COLUMN_ATTIVITA));
        holder.mTextView1.setText(name);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();

    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
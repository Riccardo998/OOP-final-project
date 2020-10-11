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
import com.example.organizzatore.data.contract.FreeTimeContract.FreeTimeEntry;



public class FreeTimeDbAdapter extends RecyclerView.Adapter<FreeTimeDbAdapter.FreeTimeDbViewHolder> {
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

    public FreeTimeDbAdapter( Context context, Cursor cursor) {
        mCursor = cursor;
        this.context = context;
    }

    public static class FreeTimeDbViewHolder extends RecyclerView.ViewHolder {
        //nome, posizione e immagine delete
        public TextView mTextView1;
        public ImageView mDeleteImage;


        public FreeTimeDbViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView); //nome
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
    public FreeTimeDbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        FreeTimeDbViewHolder evh = new FreeTimeDbViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(FreeTimeDbViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }
        //PRENDO ATTIVITA SQLITE E LA MOSTRO A DISPLAY
        String name = mCursor.getString(mCursor.getColumnIndex(FreeTimeEntry.COLUMN_ATTIVITA));
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
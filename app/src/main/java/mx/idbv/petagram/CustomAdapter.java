package mx.idbv.petagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<Mascota> mDataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewMainListItem;
        private final TextView textViewName;
        private final TextView textViewRateInt;
        private final ImageView imageViewRateUp;
        private final ImageView imageViewRateDown;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewMainListItem = itemView.findViewById(R.id.image_main_list_item);
            textViewName = itemView.findViewById(R.id.text_name);
            textViewRateInt = itemView.findViewById(R.id.text_rate_int);
            imageViewRateUp = itemView.findViewById(R.id.image_bone_rate_up);
            imageViewRateDown = itemView.findViewById(R.id.image_bone_rate_down);
//            imageViewRateUp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mDataSet.get(getAdapterPosition()).rateUp();
//                }
//            });
//            imageViewRateDown.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mDataSet.get(getAdapterPosition()).rateDown();
//                }
//            });
        }
    }

    public CustomAdapter(ArrayList<Mascota> dataSet) {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View element = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list_item, parent, false);
        return new MyViewHolder(element);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        final Mascota mascota = mDataSet.get(position);
        final TextView textRateInt = holder.textViewRateInt;
        holder.imageViewMainListItem.setImageResource(mascota.getFoto());
        holder.textViewName.setText(mascota.getNombre());
        textRateInt.setText(String.valueOf(mascota.getCalificacion()));
        holder.imageViewRateUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.rateUp();
                textRateInt.setText(String.valueOf(mascota.getCalificacion()));
            }
        });
        holder.imageViewRateDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.rateDown();
                textRateInt.setText(String.valueOf(mascota.getCalificacion()));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

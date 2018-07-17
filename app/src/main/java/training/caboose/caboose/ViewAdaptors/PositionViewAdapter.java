package training.caboose.caboose.ViewAdaptors;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import training.caboose.caboose.Models.Position;
import training.caboose.caboose.R;
import training.caboose.caboose.ViewModules;
import training.caboose.caboose.ViewPositionDetails;


//https://guides.codepath.com/android/using-the-recyclerview
public class PositionViewAdapter extends RecyclerView.Adapter<PositionViewAdapter.ViewHolder> {

    private static List<Position> mPositions;
    private Context mContext;


    // Provide a suitable constructor (depends on the kind of dataset)
    public PositionViewAdapter(List<Position> positions, Context context) {
        mPositions = positions;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PositionViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // create a new view
        View positionView = inflater.inflate(R.layout.position_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(mContext, positionView);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PositionViewAdapter.ViewHolder viewHolder, final int positionIndex) {
        Position position = mPositions.get(positionIndex);
        TextView textView = viewHolder.mTextView;
        textView.setText(position.getName());
        ImageButton imageButton = viewHolder.mImageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moduleIntent = new Intent(mContext, ViewPositionDetails.class);
                moduleIntent.putExtra("PositionId", mPositions.get(positionIndex).getUid());
                moduleIntent.putExtra("assignedDate", mPositions.get(positionIndex).getAssignedData());
                mContext.startActivity(moduleIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPositions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public ImageButton mImageButton;
        private Context context;
        private Context activityContext;


        public ViewHolder(Context activityContext, View itemView)  {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.position_list_name);
            mImageButton = (ImageButton) itemView.findViewById(R.id.position_select_button);
            this.activityContext = activityContext;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) {
                Intent moduleIntent = new Intent(activityContext, ViewModules.class);

                Log.d("*************onCLick", mPositions.get(position).getUid());

                moduleIntent.putExtra("positionId", mPositions.get(position).getUid());
                activityContext.startActivity(moduleIntent);
            }
        }
    }
}

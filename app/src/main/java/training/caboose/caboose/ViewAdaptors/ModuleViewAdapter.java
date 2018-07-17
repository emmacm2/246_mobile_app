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

import training.caboose.caboose.Models.Module;
import training.caboose.caboose.R;
import training.caboose.caboose.ViewHtmlModule;
import training.caboose.caboose.ViewModuleDetails;
import training.caboose.caboose.ViewYoutubeModule;


//https://guides.codepath.com/android/using-the-recyclerview
public class ModuleViewAdapter extends RecyclerView.Adapter<ModuleViewAdapter.ViewHolder> {
    private static List<Module> mModules;
    private Context mContext;
    private static String positionId;


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

                Intent moduleIntent;
                Log.d("^^^^^^^^^^", mModules.get(position).getName());
                Log.d("^^^^^^^^^^TYPE", mModules.get(position).getType());
                switch(mModules.get(position).getType()){
                    case "youtube" :
                        moduleIntent = new Intent(activityContext, ViewYoutubeModule.class);
                        moduleIntent.putExtra("positionId", positionId);
                        moduleIntent.putExtra(activityContext.getString(R.string.youtubeModuleIntentYoutubeData), mModules.get(position).getYoutubeData());
                        moduleIntent.putExtra("positionId", positionId);
                        break;
                    case "html" :
                        moduleIntent = new Intent(activityContext, ViewHtmlModule.class);
                        moduleIntent.putExtra(activityContext.getString(R.string.htmlModuleIntentHtmlData), mModules.get(position).getHtmlData());
                        moduleIntent.putExtra("positionId", positionId);
                        break;
                     default:
                         Log.e("ModuleViewAdapter","Unknown Module Type");
                         throw new Error("Invalid Module type");
                }
                
                moduleIntent.putExtra(activityContext.getString(R.string.moduleIntent_ModuleId), mModules.get(position).getUid());
                activityContext.startActivity(moduleIntent);
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ModuleViewAdapter(List<Module> modules, Context context, String positionId) {
        mModules = modules;
        mContext = context;
        this.positionId = positionId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ModuleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
    public void onBindViewHolder(ModuleViewAdapter.ViewHolder viewHolder, final int positionIndex) {
        Module module = mModules.get(positionIndex);
        TextView textView = viewHolder.mTextView;
        textView.setText(module.getName());
        ImageButton imageButton = viewHolder.mImageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moduleIntent = new Intent(mContext, ViewModuleDetails.class);
                moduleIntent.putExtra("moduleId", mModules.get(positionIndex).getUid());
                mContext.startActivity(moduleIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mModules.size();
    }
}

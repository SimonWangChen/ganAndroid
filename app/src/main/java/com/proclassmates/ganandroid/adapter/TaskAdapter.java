package com.proclassmates.ganandroid.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.proclassmates.ganandroid.R;
import com.proclassmates.ganandroid.database.TaskEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    ItemClickListener mItemClickListener;

    private List<TaskEntry> mTaskEntries;

    private Context mContext;

    private final static String DATA_FORMAT = "dd/MM/yyy";

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_FORMAT, Locale.getDefault());

    public TaskAdapter(Context context, ItemClickListener itemClickListener){
        this.mContext = context;
        this.mItemClickListener = itemClickListener;

    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskHolder taskHolder;
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_layout,parent, false);

        taskHolder = new TaskHolder(view);

        return taskHolder;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        // Determine the values of the wanted data
        TaskEntry taskEntry = mTaskEntries.get(position);
        String description = taskEntry.getDescription();
        int priority = taskEntry.getPriority();
        String updatedAt = simpleDateFormat.format(taskEntry.getUpdatedAt());

        //Set values
        holder.taskDescription.setText(description);
        holder.taskUpdatedAt.setText(updatedAt);

        // Programmatically set the text and color for the priority TextView
        String priorityString = "" + priority; // converts int to String
        holder.priorityTextView.setText(priorityString);

        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityTextView.getBackground();
        // Get the appropriate background color based on the priority
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }

    /*
    Helper method for selecting the correct priority circle color.
    P1 = red, P2 = orange, P3 = yellow
    */
    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }


    public List<TaskEntry> getTasks() {
        return mTaskEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<TaskEntry> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }

    public interface ItemClickListener{
        void onItemClickListener(int itemId);
    }

    public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView taskDescription;
        TextView taskUpdatedAt;
        TextView priorityTextView;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskDescription = itemView.findViewById(R.id.taskDescription);
            taskUpdatedAt = itemView.findViewById(R.id.taskUpdatedAt);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mTaskEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}

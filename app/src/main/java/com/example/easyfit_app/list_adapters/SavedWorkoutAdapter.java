package com.example.easyfit_app.list_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.model.Workout;

import java.util.List;

/**
 * Custom adapter that allows workouts to be displayed as ListView elements.
 */

public class SavedWorkoutAdapter extends BaseAdapter {

    private static final String TAG = "ExerciseAdapter";

    Context context;
    int textViewResourceId;
    List<Workout> workoutList;

    public SavedWorkoutAdapter(Context context, int textViewResourceId, List<Workout> workoutList) {
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.workoutList = workoutList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SavedWorkoutHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_saved_workout, null);

            holder = new SavedWorkoutHolder();
            holder.tvWorkoutName = (TextView) convertView.findViewById(R.id.tv_workout_name);

            convertView.setTag(holder);
        } else {
            holder = (SavedWorkoutHolder) convertView.getTag();
        }

        Workout workout = workoutList.get(position);
        holder.tvWorkoutName.setText(workout.getWorkoutName());

        return convertView;

    }

    @Override
    public int getCount() {
        return this.workoutList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.workoutList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class SavedWorkoutHolder {
        public TextView tvWorkoutName;
    }

}

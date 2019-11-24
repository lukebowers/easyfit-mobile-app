package com.example.easyfit_app.list_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.model.UserExercise;

import java.util.List;

/**
 * Custom adapter that allows UserExercise attributes to be displayed together as a ListView
 * element.
 */

public class UserExerciseAdapter extends BaseAdapter {

    private static final String TAG = "ExerciseAdapter";

    Context context;
    int textViewResourceId;
    List<UserExercise> exerciseList;

    public UserExerciseAdapter(Context context, int textViewResourceId, List<UserExercise> exerciseList) {
        // super(context, textViewResourceId, exerciseList);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.exerciseList = exerciseList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UserExerciseHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_user_exercise, null);

            holder = new UserExerciseHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_exercise_name);
            holder.tvSetsReps = (TextView) convertView.findViewById(R.id.tv_sets_reps);
            holder.tvGroup = (TextView) convertView.findViewById(R.id.tv_exercise_group);
            holder.tvEquipment = (TextView) convertView.findViewById(R.id.tv_exercise_equipment);
            holder.tvUrl = (TextView) convertView.findViewById(R.id.tv_exercise_url);

            convertView.setTag(holder);
        } else {
            holder = (UserExerciseHolder) convertView.getTag();
        }

        UserExercise exercise = exerciseList.get(position);
        holder.tvName.setText(exercise.getExerciseName());
        holder.tvSetsReps.setText(context.getResources().getString(R.string.placeholder_sets_reps, exercise.getSets(), exercise.getReps()));
        holder.tvGroup.setText(exercise.getMuscleGroup());
        holder.tvEquipment.setText(exercise.getEquipment());
        holder.tvUrl.setText(exercise.getUrl());

        return convertView;
    }

    @Override
    public int getCount() {
        return this.exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class UserExerciseHolder {
        public TextView tvName;
        public TextView tvSetsReps;
        public TextView tvGroup;
        public TextView tvEquipment;
        public TextView tvUrl;
    }
}
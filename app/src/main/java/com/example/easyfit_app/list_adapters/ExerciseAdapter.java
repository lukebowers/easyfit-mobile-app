package com.example.easyfit_app.list_adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easyfit_app.R;
import com.example.easyfit_app.model.Exercise;

import java.util.List;

/**
 * Custom adapter that allows Exercise attributes to be displayed together as a ListView element.
 */

public class ExerciseAdapter extends BaseAdapter {

    private static final String TAG = "ExerciseAdapter";

    Context context;
    int textViewResourceId;
    List<Exercise> exerciseList;

    public ExerciseAdapter(Context context, int textViewResourceId, List<Exercise> exerciseList) {
        // super(context, textViewResourceId, exerciseList);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.exerciseList = exerciseList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ExerciseHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_exercise, null);

            holder = new ExerciseHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_exercise_name);
            holder.tvGroup = (TextView) convertView.findViewById(R.id.tv_exercise_group);
            holder.tvUtility = (TextView) convertView.findViewById(R.id.tv_exercise_utility);
            holder.tvEquipment = (TextView) convertView.findViewById(R.id.tv_exercise_equipment);
            holder.tvUrl = (TextView) convertView.findViewById(R.id.tv_exercise_url);

            convertView.setTag(holder);
        } else {
            holder = (ExerciseHolder) convertView.getTag();
        }

        Exercise exercise = exerciseList.get(position);
        holder.tvName.setText(exercise.getExerciseName());
        holder.tvGroup.setText(exercise.getMuscleGroup());
        holder.tvUtility.setText(exercise.getUtility());
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

    public static class ExerciseHolder {
        public TextView tvName;
        public TextView tvGroup;
        public TextView tvUtility;
        public TextView tvEquipment;
        public TextView tvUrl;
    }
}

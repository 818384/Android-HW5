package edu.hcmus.hw05;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private List<User> users;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_row_custom, null);
        }
        //View row = inflater.inflate(R.layout.layout_row_custom, parent, false);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
        TextView idUser = (TextView) convertView.findViewById(R.id.tvid);
        avatar.setImageResource(users.get(position).getNameAvatar());
        idUser.setText(users.get(position).getUserID());

        if(position == MainActivity.getCurrentPos()){
            convertView.setBackgroundColor(Color.YELLOW);
        }
        else
            convertView.setBackgroundColor(Color.parseColor("#ffccddff"));
        return convertView;
    }
}

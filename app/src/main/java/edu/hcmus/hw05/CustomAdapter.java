package edu.hcmus.hw05;

import android.app.Activity;
import android.content.Context;
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
            convertView = inflater.inflate(R.layout.layout_blue, null);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
        TextView classname = (TextView) convertView.findViewById(R.id.tvClassName);
        avatar.setImageResource(users.get(position).getNameAvatar());
        classname.setText(users.get(position).getUserID());
        return convertView;
    }
}

package edu.hcmus.hw05;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentBlue extends Fragment implements FragmentCallbacks{


    private TextView tvSelect;
    private ListView listView;
    private List<User> users = new ArrayList<>();

    // this fragment shows a ListView
    CustomAdapter adapter;
    MainActivity main;
    Context context = null;
    String message = "";
    // data to fill-up the ListView
    private String items[] = {"Text-on-Line-00", "Text-on-Line-01", "Text-on-Line-10"};

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static FragmentBlue newInstance(String strArg) {
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();

            String[] username = {"Nguyen Van C", "Tran Van B", "Phan Van A", "Nguyen Thi D", "Lam Van E"};
            String[] userID = {"A1_9829", "A1_9999", "A2_9154", "A3_0265", "A3_1452"};
            String[] classname = {"A1", "A1", "A2", "A3", "A3"};
            Integer[] avatars = {R.drawable.avatar0, R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4};
            Double[] pointAVG = {7.0, 5.1, 8.0, 9.2, 6.8};

            for (int i = 0; i < username.length; i++) {
                users.add(new User(userID[i], username[i], avatars[i], classname[i], pointAVG[i]));
            }

        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);

        tvSelect = (TextView) layout_blue.findViewById(R.id.tvSelect);
        listView = (ListView) layout_blue.findViewById(R.id.myList);
        listView.setBackgroundColor(Color.parseColor("#ffccddff"));
        adapter = new CustomAdapter(context, R.layout.layout_blue, users);
        listView.setAdapter(adapter);
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String usr = users.get(position).getUserID() + "," +
                             users.get(position).getUserName() + "," +
                             users.get(position).getClassName() + "," +
                             users.get(position).getPointAVG().toString();
                main.onMsgFromFragToMain("BLUE-FRAG", usr);
                tvSelect.setText("Mã số: " + users.get(position).getUserID());
            }
        });

        return layout_blue;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        int position = Integer.parseInt(strValue);
        tvSelect.setText("Mã số: " + users.get(position).getUserID());
        adapter.getView(position, listView, listView);
        String usr = users.get(position).getUserID() + "," +
                users.get(position).getUserName() + "," +
                users.get(position).getClassName() + "," +
                users.get(position).getPointAVG().toString();
        main.onMsgFromFragToMain("BLUE-FRAG", usr);
    }
}
